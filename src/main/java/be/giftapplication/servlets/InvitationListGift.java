package be.giftapplication.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.giftapplication.javabeans.ListGift;

/**
 * Servlet implementation class InvitationListGift
 */

public class InvitationListGift extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InvitationListGift() {
        super();
       
    }

/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int idListGift = Integer.parseInt(request.getParameter("idListGift"));
			ListGift listgift = new ListGift();
			listgift.setIdListGift(idListGift);
			listgift = listgift.findListGiftById();
			
			listgift.getListGiftGifts();
			HttpSession session = request.getSession();
			session.setAttribute("listgift", listgift);
			
			
			getServletContext().getRequestDispatcher("/WEB-INF/InvitationListGift.jsp").forward(request, response);
			
		} catch(Exception e) {
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
