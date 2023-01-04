package be.giftapplication.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import be.giftapplication.javabeans.Customer;
import be.giftapplication.javabeans.ListGift;

public class InvitationFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpSession session = ((HttpServletRequest) request).getSession(false);

		if (session != null) {
			Customer customer = (Customer) session.getAttribute("customer");
			if (customer.getIdCustomer() != 0) {

				int idListGift = Integer.parseInt(request.getParameter("idListGift"));
				boolean found = false;
				// Check if customer is owner
				for (int i = 0; i < customer.getMyListGifts().size() && found == false; i++) {
					if (customer.getMyListGifts().get(i).getIdListGift() == idListGift) {
						found = true;
					}
				}
				if (found) {
					request.setAttribute("idListGift", idListGift);
					request.getServletContext().getRequestDispatcher("/consultlistgift").forward(request, response);
				} else {

					// Check if customer is invited
					ListGift listgift = new ListGift();
					listgift.setIdListGift(idListGift);
					boolean receipt = listgift.getListGiftInvitations();
					if (receipt) {
						boolean invited = false;
						for (int i = 0; i < listgift.getInvitations().size() && invited == false; i++) {
							if (customer.getIdCustomer() == listgift.getInvitations().get(i).getIdCustomer()) {
								invited = true;

							}

						}
						if (!invited) {
							request.getServletContext().getRequestDispatcher("/mygiftlists").forward(request, response);
						} else {
							chain.doFilter(request, response);
						}

					}

				}

			} else {
				request.getServletContext().getRequestDispatcher("/register").forward(request, response);
			}
		} else {
			request.getServletContext().getRequestDispatcher("/register").forward(request, response);
		}

	}

}
