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
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletContext context = getServletContext();
		RequestDispatcher dispatcher = context.getRequestDispatcher("/WEB-INF/Register.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String lastNameParam = request.getParameter("lastName");
		String firstNameParam = request.getParameter("firstName");
		String dob = request.getParameter("dateOfBirth");
		String usernameParam = request.getParameter("username");
		String passwordParam1 = request.getParameter("password1");
		String passwordParam2 = request.getParameter("password2");

		if (request.getParameter("submit") != null && dob != null && !dob.equals("")) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate dateOfBirthParam = LocalDate.parse(dob, formatter);
			ArrayList<String> errors = checkingParameters(lastNameParam, firstNameParam, dateOfBirthParam, usernameParam,
					passwordParam1, passwordParam2);
			System.out.println(errors);
			if (errors.size() == 0) {
				Customer customer = new Customer(0,firstNameParam, lastNameParam, dateOfBirthParam, usernameParam, passwordParam1);
				if(customer.insert())
				{
					request.setAttribute("registerSuccess", "Yes");
					response.sendRedirect("home");
				}
				else
				{
					System.out.println("Erreur inscription");
				}

			} else {
				request.setAttribute("errors", errors);
				getServletContext().getRequestDispatcher("/WEB-INF/Error.jsp").forward(request, response);
			}
		}
	}

	public ArrayList<String> checkingParameters(String lastNameParam, String firstNameParam, LocalDate dateOfBirthParam,
			String usernameParam, String passwordParam1, String passwordParam2) {

		ArrayList<String> errors = new ArrayList<String>();

		LocalDate today = LocalDate.now();

		if (lastNameParam == null || lastNameParam.equals("")) {
			errors.add("Le champ [nom] est vide.");
		}

		if (firstNameParam == null || firstNameParam.equals("")) {
			errors.add("Le champ [prénom] est vide.");
		}

		if (dateOfBirthParam == null || dateOfBirthParam.equals("") ) {
			errors.add("Le champ [date de naissance] est null.");
		}
		if (Period.between(dateOfBirthParam, today).getYears() < 16) {
			errors.add("Vous devez avoir minimum de 16 ans");
		}
		if (Period.between(dateOfBirthParam, today).getYears() > 120) {
			errors.add("Vous devez avoir moins de 120 ans");
		}

		if (usernameParam == null || usernameParam.equals("")) {
			errors.add("Le champ [nom d'utilisateur] est vide.");
		}
		if (!usernameParam.matches("^[0-9a-zA-Z]{5,}$")) {
			errors.add("Le champ [nom d'utilisateur] doit avoir au moins 5 caractères.");
		}

		if (passwordParam1 == null || usernameParam.equals("")) {
			errors.add("Le champ [mot de passe] est vide.");
		}
		if (!passwordParam1.matches("^[0-9a-zA-Z]{6,}$")) {
			errors.add("Le champ [mot de passe] doit avoir au moins 6 caractères.");
		}

		if (passwordParam2 == null || passwordParam2.equals("")) {
			errors.add("Le champ [mot de passe] est vide.");
		}
		if (!passwordParam2.equals(passwordParam1)) {
			errors.add("Les deux mots de passes ne sont pas identiques");
		}

		return errors;
	}
}