package be.giftapplication.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.giftapplication.javabeans.Customer;
import be.giftapplication.javabeans.ListGift;

/**
 * Servlet implementation class ConsultInvitations
 */

public class ConsultInvitations extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConsultInvitations() {
        super();
       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idListGift = Integer.parseInt(request.getParameter("idListGift"));
		HttpSession session = request.getSession(false);
		Customer customer = (Customer) session.getAttribute("customer");
		boolean receipt = false;
		for(int i=0; i < customer.getMyListGifts().size(); i++) {
			if(customer.getMyListGifts().get(i).getIdListGift() == idListGift) {
				receipt = customer.getMyListGifts().get(i).getListGiftInvitations();
			}
		}
		if(receipt) {
			//set customer with updated listgift to the session
			session.setAttribute("customer", customer);
		}
		ListGift listgift = customer.getMyListGifts().stream().filter(l -> l.getIdListGift() == idListGift).findFirst().orElse(null);
		
		request.setAttribute("listgift", listgift);
		getServletContext().getRequestDispatcher("/WEB-INF/ConsultInvitations.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
