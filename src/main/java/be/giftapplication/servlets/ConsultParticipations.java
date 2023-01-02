package be.giftapplication.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.giftapplication.javabeans.Customer;
import be.giftapplication.javabeans.Gift;
import be.giftapplication.javabeans.ListGift;

/**
 * Servlet implementation class ConsultParticipations
 */

public class ConsultParticipations extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConsultParticipations() {
        super();
       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession(false);
			Customer customer = (Customer) session.getAttribute("customer");
			int idListGift = (int) session.getAttribute("idListGift");
			int idGift = Integer.parseInt(request.getParameter("idGift"));
			ListGift listGift = customer.getMyListGifts().stream().filter(l -> l.getIdListGift() == idListGift).findFirst().orElse(null);
			Gift gift = listGift.getGifts().stream().filter(l -> l.getIdGift() == idGift).findFirst().orElse(null);
			gift.getGiftParticipations();
			
			request.setAttribute("gift", gift);
			
			getServletContext().getRequestDispatcher("/WEB-INF/ConsultParticipations.jsp").forward(request, response);
			
		}catch (Exception e){
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
