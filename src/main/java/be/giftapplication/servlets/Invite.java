package be.giftapplication.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.giftapplication.javabeans.Customer;
import be.giftapplication.javabeans.ListGift;

/**
 * Servlet implementation class Invite
 */
public class Invite extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Invite() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		Customer customer = (Customer) session.getAttribute("customer");
		int idListGift = Integer.parseInt(request.getParameter("idListGift"));
		ListGift listGift = customer.getMyListGifts().stream().filter(l -> l.getIdListGift() == idListGift).findFirst().orElse(null);
		
		ArrayList<Customer> customers = Customer.getCustomers();
		
		for(int i=0; i<customers.size(); i++) {
			if(customers.get(i).getIdCustomer() == customer.getIdCustomer()) {
				customers.remove(i);
			}
		}	
		
		session.setAttribute("invites", customers);
		request.setAttribute("customers", customers);
		request.setAttribute("idListGift", idListGift);
		
		getServletContext().getRequestDispatcher("/WEB-INF/Invite.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		ArrayList<Customer> invites = (ArrayList<Customer>) session.getAttribute("invites");
		int idInvite = Integer.parseInt(request.getParameter("idCustomer"));
		int idListGift = Integer.parseInt(request.getParameter("idListGift"));
		boolean verification = false;
		
		Customer customer = (Customer) session.getAttribute("customer");
		ListGift listGift = customer.getMyListGifts().stream().filter(l -> l.getIdListGift() == idListGift).findFirst().orElse(null);
		ListGift listGiftWithoutOwner = new ListGift(idListGift,listGift.getName(),listGift.getDeadline(),listGift.isStatus(),listGift.getTheme(),null);
		
		for(int i=0; i<listGift.getInvitations().size(); i++) {
			if(listGift.getInvitations().get(i).getIdCustomer() == idInvite ) {
				verification = true;
			}
		}
		Customer invite = invites.stream().filter(i -> i.getIdCustomer() == idInvite).findFirst().orElse(null);
		
		if(verification == false) {
			boolean result = listGift.inviteUpdate(listGiftWithoutOwner, invite);
			if(result) {
				request.setAttribute("customers", invites);
				request.setAttribute("success", "L'invité a été ajouté !");
				request.setAttribute("idListGift", idListGift);
				getServletContext().getRequestDispatcher("/WEB-INF/Invite.jsp").forward(request, response);
			}
			else
			{
				request.setAttribute("customers", invites);
				request.setAttribute("Error2", "Un problème s'est produit lors de l'invitation.");
				request.setAttribute("idListGift", idListGift);
				getServletContext().getRequestDispatcher("/WEB-INF/Invite.jsp").forward(request, response);
			}
		}
		else {
			request.setAttribute("customers", invites);
			request.setAttribute("Error", "Déjà présent dans la liste");
			request.setAttribute("idListGift", idListGift);
			getServletContext().getRequestDispatcher("/WEB-INF/Invite.jsp").forward(request, response);
		}
		
	}

}
