package be.giftapplication.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.giftapplication.javabeans.Gift;
import be.giftapplication.javabeans.ListGift;

/**
 * Servlet implementation class InvitationConsultGift
 */

public class InvitationConsultGift extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InvitationConsultGift() {
        super();
       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			ListGift listgift = (ListGift) session.getAttribute("listgift");
			int idGift = Integer.parseInt(request.getParameter("idGift"));
			Gift gift = listgift.getGifts().stream().filter(l -> l.getIdGift() == idGift).findFirst().orElse(null);
			
			request.setAttribute("gift", gift);
			
			getServletContext().getRequestDispatcher("/WEB-INF/InvitationConsultGift.jsp").forward(request, response);
			
		} catch(Exception e) {
			System.out.println(e.getMessage());
			getServletContext().getRequestDispatcher("/WEB-INF/ErrorPage.jsp").forward(request, response);
		}
 		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
