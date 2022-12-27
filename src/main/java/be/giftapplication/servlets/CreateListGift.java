package be.giftapplication.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.giftapplication.javabeans.Customer;
import be.giftapplication.javabeans.ListGift;

/**
 * Servlet implementation class CreateListGift
 */

public class CreateListGift extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateListGift() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<String> errors = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			errors.add("");
		}
		request.setAttribute("errors", errors);
		ServletContext context = getServletContext();
		RequestDispatcher dispatcher = context.getRequestDispatcher("/WEB-INF/CreateListGift.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<String> errors = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			errors.add("");
		}
		String nameParam = request.getParameter("name");
		String deadlineParam = request.getParameter("deadline");
		String theme = request.getParameter("theme");

		if (request.getParameter("submit") != null) {
			errors = checkingParameters(nameParam, deadlineParam, theme, errors);
			if (!checkErrors(errors)) {
				LocalDate newDeadlineParam;
				if(deadlineParam == null || deadlineParam.isEmpty())
				{
					newDeadlineParam = LocalDate.of(1000, 1, 1);
				}
				else {
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					newDeadlineParam = LocalDate.parse(deadlineParam, formatter);
				}	
				HttpSession session = request.getSession();
				Customer customer = (Customer) session.getAttribute("customer");
				Customer customerWithoutList = new Customer();
				customerWithoutList.setIdCustomer(customer.getIdCustomer());
				ListGift listGift = new ListGift(0,nameParam,newDeadlineParam,true,theme,customerWithoutList);
				if(listGift.insert())
				{
					request.setAttribute("createListGiftSuccess", "création de la liste réussite");
					request.setAttribute("errors", errors);
					getServletContext().getRequestDispatcher("/WEB-INF/CreateListGift.jsp").forward(request, response);
				}
				else
				{
					request.setAttribute("createListGiftError", "Erreur dans la création de la liste.");
                	request.setAttribute("errors", errors);
                	getServletContext().getRequestDispatcher("/WEB-INF/CreateListGift.jsp").forward(request, response);
				}

			} else {
				request.setAttribute("errors", errors);
				getServletContext().getRequestDispatcher("/WEB-INF/CreateListGift.jsp").forward(request, response);
			}
		}
	}

	public ArrayList<String> checkingParameters(String nameParam, String deadlineParam, String theme,
			ArrayList<String> errors) {

		if (nameParam == null || nameParam.equals("")) {
			errors.add(0, "Le champ [nom] est vide.");

		}

		errors = checkDeadline(deadlineParam, errors);

		if (theme == null || theme.equals("")) {
			errors.add(2, "Le champ [thème] est vide.");
		}

		return errors;
	}

	public ArrayList<String> checkDeadline(String deadlineParam, ArrayList<String> errors) {
		LocalDate today = LocalDate.now();

		if (deadlineParam == null || deadlineParam.isEmpty()) {
			return errors;
		} else {
			try {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate newDeadlineParam = LocalDate.parse(deadlineParam, formatter);

				if (newDeadlineParam.isBefore(today) || newDeadlineParam.isEqual(today)) {
					errors.add(1, "La date limite ne peut être inférieure ou égale à aujourd'hui.");
				}

			} catch (Exception e) {
				errors.add(1, "Le champ [date limite] est incorrecte.");
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
