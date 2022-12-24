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

import be.giftapplication.javabeans.Customer;

/**
 * Servlet implementation class Register
 */
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Register() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<String> errors = new ArrayList<>();
        for(int i=0; i<7; i++)
        {
        	errors.add("");
        }
        request.setAttribute("errors", errors);
		ServletContext context = getServletContext();
		RequestDispatcher dispatcher = context.getRequestDispatcher("/WEB-INF/Register.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<String> errors = new ArrayList<>();
		 for(int i=0; i<7; i++)
	     {
	        	errors.add("");
	     }
		String lastNameParam = request.getParameter("lastName");
		String firstNameParam = request.getParameter("firstName");
		String dob = request.getParameter("dateOfBirth");
		String usernameParam = request.getParameter("username");
		String passwordParam1 = request.getParameter("password1");
		String passwordParam2 = request.getParameter("password2");

		if (request.getParameter("submit") != null) {
			
			errors = checkingParameters(lastNameParam, firstNameParam, dob, usernameParam,
					passwordParam1, passwordParam2, errors);
			if (!checkErrors(errors)) {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate dateOfBirthParam = LocalDate.parse(dob, formatter);
				Customer customer = new Customer(0,firstNameParam, lastNameParam, dateOfBirthParam, usernameParam, passwordParam1);
				if(customer.insert())
				{
					request.setAttribute("registerSuccess", "Yes");
					request.setAttribute("errors", errors);
					getServletContext().getRequestDispatcher("/WEB-INF/Register.jsp").forward(request, response);
				}
				else
				{
					System.out.println("Erreur inscription");
				}

			}else {
				request.setAttribute("errors", errors);
				getServletContext().getRequestDispatcher("/WEB-INF/Register.jsp").forward(request, response);
			}
		}
	}

	public ArrayList<String> checkingParameters(String lastNameParam, String firstNameParam, String dob,
			String usernameParam, String passwordParam1, String passwordParam2, ArrayList<String> errors) {


		if (lastNameParam == null || lastNameParam.equals("")) {
			errors.add(0,"Le champ [nom] est vide.");
		
		}

		if (firstNameParam == null || firstNameParam.equals("")) {
			errors.add(1,"Le champ [prénom] est vide.");
		}

		errors = checkDateOfBirth(dob, errors);
		
		
		if(usernameParam == null || usernameParam.equals("") || !usernameParam.matches("^[0-9a-zA-Z]{5,}$")) {
			errors.add(3,"Le champ [nom d'utilisateur] doit avoir au moins 5 caractères.");
			
		}

		
		if (passwordParam1 == null || usernameParam.equals("") || !passwordParam1.matches("^[0-9a-zA-Z]{6,}$")) {
			errors.add(4,"Le champ [mot de passe] doit avoir au moins 6 caractères.");
		}

		if (passwordParam2 == null || passwordParam2.equals("")) {
			errors.add(5,"Le champ [mot de passe] est vide.");
		}
		if (!passwordParam2.equals(passwordParam1)) {
			errors.add(6,"Les deux mots de passes ne sont pas identiques");
		}

		return errors;
	}
	
	public ArrayList<String> checkDateOfBirth(String dob, ArrayList<String> errors) {
		LocalDate today = LocalDate.now();
		
		if(dob == null || dob.isEmpty()) {
			errors.add(3,"Le champ [Date de naissance] est vide.");
		}
		else {
			try {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate dateOfBirthParam = LocalDate.parse(dob, formatter);
				
				if (Period.between(dateOfBirthParam, today).getYears() < 16) {
					errors.add(3,"Vous devez avoir minimum de 16 ans.");
				}
				if (Period.between(dateOfBirthParam, today).getYears() > 120) {
					errors.add(3,"Vous devez avoir moins de 120 ans.");
				}
				
			}catch(Exception e) {
				errors.add(3,"Le champ [Date de naissance] est incorrecte.");
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