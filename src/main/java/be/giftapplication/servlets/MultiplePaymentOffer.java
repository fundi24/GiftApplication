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
import be.giftapplication.javabeans.Notification;
import be.giftapplication.javabeans.Participation;

/**
 * Servlet implementation class MultiplePaymentOffer
 */

public class MultiplePaymentOffer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MultiplePaymentOffer() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			HttpSession session = request.getSession();
			ListGift listgift = (ListGift) session.getAttribute("listgift");
			int idGift = Integer.parseInt(request.getParameter("idGift"));

			Gift gift = listgift.getGifts().stream().filter(l -> l.getIdGift() == idGift).findFirst().orElse(null);

			request.setAttribute("gift", gift);

			getServletContext().getRequestDispatcher("/WEB-INF/MultiplePaymentOffer.jsp").forward(request, response);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			getServletContext().getRequestDispatcher("/WEB-INF/ErrorPage.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		Customer customer = (Customer) session.getAttribute("customer");
		ListGift listgift = (ListGift) session.getAttribute("listgift");
		int idGift = Integer.parseInt(request.getParameter("idGift"));
		double price = Double.parseDouble(request.getParameter("price"));
		Gift gift = listgift.getGifts().stream().filter(g -> g.getIdGift() == idGift).findFirst().orElse(null);
		double sumParticipation = 0;
		double remainsToPay = 0;

		listgift.getListGiftInvitations();
		gift.getGiftParticipations();

		sumParticipation = gift.calculTotal();

		if (price == gift.getPrice()) {
			request.setAttribute("gift", gift);
			request.setAttribute("error", "Pour payer le cadeau en une fois, veuillez l'offrir directement.");
			getServletContext().getRequestDispatcher("/WEB-INF/MultiplePaymentOffer.jsp").forward(request, response);
		} else {
			if (price > gift.getPrice()) {
				request.setAttribute("gift", gift);
				request.setAttribute("error", "Le montant saisi dépasse le prix du cadeau.");
				getServletContext().getRequestDispatcher("/WEB-INF/MultiplePaymentOffer.jsp").forward(request,
						response);
			} else {
				remainsToPay = gift.getPrice() - sumParticipation;
				if (price > remainsToPay) {
					request.setAttribute("gift", gift);
					request.setAttribute("error", "Il ne reste que " + remainsToPay + "€ à payer.");
					getServletContext().getRequestDispatcher("/WEB-INF/MultiplePaymentOffer.jsp").forward(request,
							response);
				} else {

					Gift giftWithoutList = new Gift(gift.getIdGift(), gift.getName(), gift.getDescription(),
							gift.getPrice(), gift.getPriority(), gift.getPicture(), true, true, gift.getLinkToWebsite(),
							null);
					//Update gift with multiplePayment and Booked to true
					boolean receipt = gift.update(giftWithoutList);

					Customer customerWithoutList = new Customer();
					customerWithoutList.setIdCustomer(customer.getIdCustomer());
					if (receipt) {
						Participation participation = new Participation(0, price, customerWithoutList, giftWithoutList);
						boolean success = participation.create();
						if (success) {
							gift.addParticipation(participation);
							// Send notification to every customers in the invitations
							for (int i = 0; i < listgift.getInvitations().size(); i++) {
								if(customer.getIdCustomer() != listgift.getInvitations().get(i).getIdCustomer()) {
									Notification notification = new Notification(0, customer.getUsername() + " a payé "+ price +"€ pour le cadeau [" + gift.getName() + "] de la liste ["+ listgift.getName() +"] appartenant à " + listgift.getOwner().getUsername(), false, listgift.getInvitations().get(i));
									notification.create();
								}
								
							}
							request.setAttribute("gift", gift);
							request.setAttribute("success", "Le cadeau a été réservé avec succès");
							getServletContext().getRequestDispatcher("/WEB-INF/MultiplePaymentOffer.jsp")
									.forward(request, response);
						} else {
							request.setAttribute("gift", gift);
							request.setAttribute("error", "Le cadeau n'a pas pu être réservé");
							getServletContext().getRequestDispatcher("/WEB-INF/MultiplePaymentOffer.jsp")
									.forward(request, response);
						}
					} else {
						request.setAttribute("gift", gift);
						request.setAttribute("error", "Le cadeau n'a pas pu être réservé");
						getServletContext().getRequestDispatcher("/WEB-INF/MultiplePaymentOffer.jsp").forward(request,
								response);
					}
				}
			}

		}

	}

}
