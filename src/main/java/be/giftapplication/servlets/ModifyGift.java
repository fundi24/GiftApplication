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
 * Servlet implementation class ModifyGift
 */

public class ModifyGift extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyGift() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		Customer customer = (Customer) session.getAttribute("customer");
		int idListGift = Integer.parseInt((String) session.getAttribute("idListGift"));
		int idGift = Integer.parseInt((String) request.getAttribute("idGift"));
		
		ArrayList<String> errors = new ArrayList<>();
		for (int i = 0; i < 4; i++) {
			errors.add("");
		}
		request.setAttribute("errors", errors);
		request.setAttribute("idGift", idGift);
		getServletContext().getRequestDispatcher("/WEB-INF/ModifyGift.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
