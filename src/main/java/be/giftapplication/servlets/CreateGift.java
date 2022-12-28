package be.giftapplication.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import be.giftapplication.javabeans.Customer;
import be.giftapplication.javabeans.Gift;
import be.giftapplication.javabeans.ListGift;

/**
 * Servlet implementation class CreateGift
 */
@MultipartConfig
public class CreateGift extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateGift() {
        super();
       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int idListGift = Integer.parseInt(request.getParameter("idListGift"));
		
		ArrayList<String> errors = new ArrayList<>();
		for (int i = 0; i < 4; i++) {
			errors.add("");
		}
		request.setAttribute("errors", errors);
		request.setAttribute("idListGift", idListGift);
		getServletContext().getRequestDispatcher("/WEB-INF/CreateGift.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<String> errors = new ArrayList<>();
		for (int i = 0; i < 4; i++) {
			errors.add("");
		}
		String nameParam = request.getParameter("name");
		String descriptionParam = request.getParameter("description");
		String strPriceParam = request.getParameter("price");
		InputStream inputStream = request.getPart("picture").getInputStream();
		byte[] arrayBytes = inputStream.readAllBytes();
		String strPicture = new String(arrayBytes, StandardCharsets.UTF_8);
		String linkToWebsiteParam = request.getParameter("linkToWebsite");
		
		
		if (request.getParameter("submit") != null) {
			errors = checkingParameters(nameParam, descriptionParam, strPriceParam,linkToWebsiteParam ,errors);
			if (!checkErrors(errors)) {
				double priceParam = Double.parseDouble(strPriceParam);
				//Get Customer from session and get idListGift from request
				HttpSession session = request.getSession(false);
				Customer customer = (Customer) session.getAttribute("customer");
				int idListGift = Integer.parseInt((String)request.getParameter("idListGift"));
				
				//Find listgift from the giftlists of the customer with the id
				ListGift listGift = customer.getMyListGifts().stream().filter(l -> l.getIdListGift() == idListGift).findFirst().orElse(null);
				int priority = listGift.getGifts().size() + 1;
				ListGift listGiftWithoutList = new ListGift();
				listGiftWithoutList.setIdListGift(listGift.getIdListGift());
				
				//giving a listGift with only the id because the DAO only need the id
				Gift gift = new Gift(0, nameParam, descriptionParam, priceParam, priority  ,false, false, linkToWebsiteParam, listGiftWithoutList);
				//!!! constructeur!!
				gift.setPicture(strPicture);
				
				if(gift.insert()) {
					request.setAttribute("createGiftSuccess", "Création du cadeau réussite.");
					request.setAttribute("errors", errors);
					getServletContext().getRequestDispatcher("/WEB-INF/CreateGift.jsp").forward(request, response);
				}
				else {
					request.setAttribute("createGiftError", "Erreur dans la création du cadeau.");
                	request.setAttribute("errors", errors);
                	getServletContext().getRequestDispatcher("/WEB-INF/CreateGift.jsp").forward(request, response);
				}
			}
		}
		
	}
		
	public ArrayList<String> checkingParameters(String nameParam, String descriptionParam, String strPriceParam, 
			String linkToWebsiteParam, ArrayList<String> errors) {

		if (nameParam == null || nameParam.equals("")) {
			errors.add(0, "Le champ [nom] est vide.");

		}
			
		if (descriptionParam == null || descriptionParam.equals("")) {
			errors.add(1, "Le champ [description] est vide.");

		}
			
		if (strPriceParam == null || strPriceParam.equals("")) {
			errors.add(2, "Le champ [prix] est vide.");
		}
			
		//Picture et Link ??
		

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
