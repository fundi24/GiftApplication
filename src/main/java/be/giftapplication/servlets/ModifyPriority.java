package be.giftapplication.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.giftapplication.javabeans.Customer;
import be.giftapplication.javabeans.Gift;
import be.giftapplication.javabeans.ListGift;

/**
 * Servlet implementation class ModifyPriority
 */

public class ModifyPriority extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyPriority() {
        super();
       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		Customer customer = (Customer) session.getAttribute("customer");
		int idListGift = (int) session.getAttribute("idListGift");
		ListGift listgift = customer.getMyListGifts().stream().filter(l -> l.getIdListGift() == idListGift).findFirst().orElse(null);
		request.setAttribute("listgift", listgift);
		
		
		getServletContext().getRequestDispatcher("/WEB-INF/ModifyPriority.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		Customer customer = (Customer) session.getAttribute("customer");
		int idListGift = (int) session.getAttribute("idListGift");
		ListGift listgift = customer.getMyListGifts().stream().filter(l -> l.getIdListGift() == idListGift).findFirst().orElse(null);
		
		ArrayList<String> errors = new ArrayList<>();
		//Get the selected value for all the gift
		int[] selectedPriority = new int[listgift.getGifts().size()];
		for(int i=0; i < selectedPriority.length; i++) {
			selectedPriority[i] = Integer.parseInt(request.getParameter("priority-select-" + i));
			
		}
		
		errors = checkSelectedPriority(selectedPriority, listgift.getGifts());
		if(errors.size() == 0) {
			boolean receipt = listgift.updateGiftsPriority(selectedPriority);
			
			if(receipt) {
				request.setAttribute("success", "Modification réussite");
				request.setAttribute("listgift", listgift);
				getServletContext().getRequestDispatcher("/WEB-INF/ModifyPriority.jsp").forward(request, response);
			}
			else {
				request.setAttribute("error", "Une erreur s'est produite lors de la modification.");
				request.setAttribute("listgift", listgift);
				getServletContext().getRequestDispatcher("/WEB-INF/ModifyPriority.jsp").forward(request, response);
			}
			
			
			
		}
		else {
			request.setAttribute("errors", errors);
			request.setAttribute("listgift", listgift);
			getServletContext().getRequestDispatcher("/WEB-INF/ModifyPriority.jsp").forward(request, response);
		}
		
		
	}
	
	public ArrayList<String> checkSelectedPriority(int[] selectedPriority, ArrayList<Gift> gifts) {		
		
		ArrayList<String> errors = new ArrayList<>();
		//Check if selected priority is the same as the current priority for the list
		boolean samePriorityAsCurrentList = true;
		for(int i = 0; i < gifts.size(); i++) {		
			if(gifts.get(i).getPriority() != selectedPriority[i]) {
				//If at least one is different => ok.
				samePriorityAsCurrentList = false;
			}
		}
		if(samePriorityAsCurrentList) {
			errors.add("Les priorités sont les mêmes que dans la liste courante");
		}
		
		//Check if there isn't more than the same priority once
		 List<Integer> list = Arrays.stream(selectedPriority).boxed().collect(Collectors.toList());
		 Set<Integer> set = new HashSet<>(list);
		if(selectedPriority.length != set.size()) {
			errors.add("Chaque cadeau doit possèder une priorité différente");
		}
		
		return errors;
	}

}
