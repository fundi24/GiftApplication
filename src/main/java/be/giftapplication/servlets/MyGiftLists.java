package be.giftapplication.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.giftapplication.javabeans.Customer;
import be.giftapplication.javabeans.ListGift;

/**
 * Servlet implementation class MyGiftLists
 */

public class MyGiftLists extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyGiftLists() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		if(session != null) {
			Customer customer = (Customer) session.getAttribute("customer");
			if(customer != null) {
				boolean receipt = customer.getCustomerListGifts();
				if(receipt) {
					session.setAttribute("customer", customer);
				}
				for (ListGift l : customer.getMyListGifts()) {
					ListGift listGiftWithoutOwner = new ListGift(l.getIdListGift(), l.getName(), l.getDeadline(), l.isStatus(), l.getTheme(), null);
					l.dailyUpdate(listGiftWithoutOwner);
				}
				request.setAttribute("giftLists", customer.getMyListGifts());
				getServletContext().getRequestDispatcher("/WEB-INF/MyGiftLists.jsp").forward(request, response);
			}
			
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
