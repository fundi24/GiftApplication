package be.giftapplication.servlets;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.giftapplication.javabeans.Customer;
import be.giftapplication.javabeans.Gift;
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
		try {
			int idListGift = Integer.parseInt(request.getParameter("idListGift"));
			HttpSession session = request.getSession(false);
			Customer customer = (Customer) session.getAttribute("customer");
			session.setAttribute("idListGift", idListGift);

			ListGift listgift = customer.getMyListGifts().stream().filter(l -> l.getIdListGift() == idListGift).findFirst().orElse(null);

			boolean receipt = listgift.getListGiftGifts();
			if(receipt) {
				session.setAttribute("customer", customer);
			}
			request.setAttribute("listgift", listgift);
			
			request.setAttribute("link", "http://" + request.getServerName()+ ":" + request.getServerPort() + request.getContextPath() + "/invitationlistgift?" + request.getQueryString())	;		
			boolean canModifyPriority = true;
			for(int i=0; i < listgift.getGifts().size() && canModifyPriority == true; i++) {
				Gift g = listgift.getGifts().get(i);
				if(g.isBooked()) {
					canModifyPriority = false;
					request.setAttribute("canModifyPriorityError", "Un ou plusieurs cadeaux de votre liste est déjà réservé.");
					
				}
			}
			
		
			
			getServletContext().getRequestDispatcher("/WEB-INF/ConsultListGift.jsp").forward(request, response);
		} catch(Exception e) {
			getServletContext().getRequestDispatcher("/WEB-INF/ErrorPage.jsp").forward(request, response);

		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		Customer customer = (Customer) session.getAttribute("customer");
		int idListGift = (int) session.getAttribute("idListGift");
		ListGift listgift = customer.getMyListGifts().stream().filter(l -> l.getIdListGift() == idListGift).findFirst().orElse(null);
		ListGift listGiftWithoutOwner = new ListGift();
		listGiftWithoutOwner.setIdListGift(idListGift);
		listGiftWithoutOwner.setStatus(listgift.isStatus());
		listGiftWithoutOwner.setDeadline(LocalDate.of(1000, 1, 1));
		listGiftWithoutOwner.setInvitations(listgift.getInvitations());
		boolean result = listGiftWithoutOwner.update();
		if(result) {
			response.sendRedirect("mygiftlists");
		}
	}

}
