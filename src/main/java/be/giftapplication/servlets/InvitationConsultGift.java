package be.giftapplication.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.giftapplication.javabeans.Customer;
import be.giftapplication.javabeans.Gift;
import be.giftapplication.javabeans.ListGift;
import be.giftapplication.javabeans.Participation;

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
			gift.getGiftParticipations();
			
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
		HttpSession session = request.getSession(false);
		Customer customer = (Customer) session.getAttribute("customer");
		ListGift listgift = (ListGift) session.getAttribute("listgift");
		int idGift = Integer.parseInt(request.getParameter("idGift"));
		Gift gift = listgift.getGifts().stream().filter(g -> g.getIdGift() == idGift).findFirst().orElse(null);
		
		
		if(gift.isMultiplePayment()) {
			//Redirect to multiple payment form
			getServletContext().getRequestDispatcher("/WEB-INF/MultiplePaymentOffer.jsp").forward(request, response);
		}
		
		if(gift.isBooked()) {
			request.setAttribute("idListGift", listgift.getIdListGift());
			request.setAttribute("gift", gift);
			request.setAttribute("error", "Le cadeau est déjà réservé");
			getServletContext().getRequestDispatcher("/WEB-INF/InvitationConsultGift.jsp").forward(request, response);
		}
		else {
			Gift giftWithoutList = new Gift(gift.getIdGift(), gift.getName(), gift.getDescription(), gift.getPrice(), gift.getPriority(), gift.getPicture(), true, false, gift.getLinkToWebsite(), null);
			boolean receipt = gift.update(giftWithoutList);
			
			Customer customerWithoutList = new Customer();
			customerWithoutList.setIdCustomer(customer.getIdCustomer());
			if(receipt) {
				Participation participation = new Participation(0, gift.getPrice(), customerWithoutList, giftWithoutList);
				boolean success = participation.create();
				if(success) {
					gift.addParticipation(participation);
					request.setAttribute("gift", gift);
					request.setAttribute("success", "Le cadeau a été réservé avec succès");
					getServletContext().getRequestDispatcher("/WEB-INF/InvitationConsultGift.jsp").forward(request, response);
				}
				else {
					request.setAttribute("gift", gift);
					request.setAttribute("error", "Le cadeau n'a pas pu être réservé");
					getServletContext().getRequestDispatcher("/WEB-INF/InvitationConsultGift.jsp").forward(request, response);
				}
			}else {
				request.setAttribute("gift", gift);
				request.setAttribute("error", "Le cadeau n'a pas pu être réservé");
				getServletContext().getRequestDispatcher("/WEB-INF/InvitationConsultGift.jsp").forward(request, response);
			}
			
			
		}
		
	}

}
