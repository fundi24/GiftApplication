package be.giftapplication.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.giftapplication.javabeans.Customer;
import be.giftapplication.javabeans.ListGift;

/**
 * Servlet implementation class ConsultListGift
 */

public class ConsultListGift extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConsultListGift() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idListGift = Integer.parseInt(request.getParameter("idListGift"));
		HttpSession session = request.getSession(false);
		Customer customer = (Customer) session.getAttribute("customer");
		ListGift listGift = customer.getMyListGifts().stream().filter(l -> l.getIdListGift() == idListGift).findFirst().orElse(null);
		//! A RELIRE !
		boolean receipt = listGift.getListGiftGifts();
		if(receipt) {
			session.setAttribute("customer", customer);
		}
		request.setAttribute("listgift", listGift);
		getServletContext().getRequestDispatcher("/WEB-INF/ConsultListGift.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
