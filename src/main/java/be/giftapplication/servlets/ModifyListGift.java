package be.giftapplication.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
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
 * Servlet implementation class ModifyListGift
 */
public class ModifyListGift extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int idListGift ;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyListGift() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<String> errors = new ArrayList<>();
        for(int i=0; i<1; i++)
        {
        	errors.add("");
        }
        request.setAttribute("errors", errors);
		HttpSession session = request.getSession(false);
		Customer customer = (Customer) session.getAttribute("customer");
		idListGift = (int) session.getAttribute("idListGift");
		ListGift listgift = customer.getMyListGifts().stream().filter(l -> l.getIdListGift() == idListGift).findFirst().orElse(null);
		request.setAttribute("listgift", listgift);
		
		getServletContext().getRequestDispatcher("/WEB-INF/ModifyListGift.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<String> errors = new ArrayList<>();
		for(int i=0; i<1; i++)
        {
        	errors.add("");
        }
		String newDeadlineParam = request.getParameter("newDeadline");
		if (request.getParameter("submit") != null) {
			HttpSession session = request.getSession(false);
			Customer customer = (Customer) session.getAttribute("customer");
			ListGift listgift = customer.getMyListGifts().stream().filter(l -> l.getIdListGift() == idListGift).findFirst().orElse(null);
			errors = checkingParameter(newDeadlineParam, listgift, errors);
			if (!checkErrors(errors)) {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate deadlineParam = LocalDate.parse(newDeadlineParam, formatter);
				ListGift listGiftWithoutOwner = new ListGift();
				listGiftWithoutOwner.setIdListGift(idListGift);
				listGiftWithoutOwner.setStatus(listgift.isStatus());
				listGiftWithoutOwner.setDeadline(deadlineParam);
				boolean result = listGiftWithoutOwner.update();
				if(result) {
					response.sendRedirect("mygiftlists");
				}

			}else
			{
				request.setAttribute("listgift", listgift);
				request.setAttribute("modifyListGiftError", "Erreur dans la modification.");
            	request.setAttribute("errors", errors);
        		getServletContext().getRequestDispatcher("/WEB-INF/ModifyListGift.jsp").forward(request, response);
			}
			
		}
	}
	
	public ArrayList<String> checkingParameter(String newDeadlineParam, ListGift listgift,ArrayList<String> errors) {
		
		if(newDeadlineParam == null || newDeadlineParam.isEmpty()) {
			errors.add(0,"Le champ [Date limite] est vide.");
		}
		else {
			try {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate deadlineParam = LocalDate.parse(newDeadlineParam, formatter);
				
				if (deadlineParam.isBefore(listgift.getDeadline()) || deadlineParam.isEqual(listgift.getDeadline())) {
					errors.add(0,"La nouvelle date ne peut être inférieur ou égale à la précédente date ");
				}
				if(deadlineParam.isBefore(LocalDate.now()) ||deadlineParam.isEqual(LocalDate.now())){
					errors.add(0,"La nouvelle date ne peut être inférieur ou égale à la date d'aujourd'hui ");
				}
				
			}catch(Exception e) {
				errors.add(0,"Le champ [Date limite] est incorrecte.");
			}
			
		}
		
		return errors;
	}
	
	public boolean checkErrors(ArrayList<String> errors) {
		boolean error = false;
	
	    for(int i=0; i < errors.size() && error != true; i++){
			if(!errors.get(i).isEmpty()) {
				error = true;
			}
		}
		
		return error;
	}

}
