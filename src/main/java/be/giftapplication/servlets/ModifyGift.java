package be.giftapplication.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.giftapplication.javabeans.Customer;
import be.giftapplication.javabeans.Gift;
import be.giftapplication.javabeans.ListGift;

/**
 * Servlet implementation class ModifyGift
 */

@MultipartConfig
public class ModifyGift extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ModifyGift() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		Customer customer = (Customer) session.getAttribute("customer");
		int idListGift = (int) session.getAttribute("idListGift");
		int idGift = Integer.parseInt((String) request.getParameter("idGift"));

		request.setAttribute("idGift", idGift);
		getServletContext().getRequestDispatcher("/WEB-INF/ModifyGift.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String nameParam = request.getParameter("name");
		String descriptionParam = request.getParameter("description");
		String strPriceParam = request.getParameter("price");
		InputStream inputStream = request.getPart("picture").getInputStream();
		byte[] arrayBytes = inputStream.readAllBytes();
		String pictureParam = Base64.getEncoder().encodeToString(arrayBytes);
		String linkToWebsiteParam = request.getParameter("linkToWebsite");

		HttpSession session = request.getSession(false);
		Customer customer = (Customer) session.getAttribute("customer");
		int idListGift = (int) session.getAttribute("idListGift");
		int idGift = Integer.parseInt(request.getParameter("idGift"));
		
		

		if (request.getParameter("submit") != null) {
			
			if(nameParam.equals("")  && descriptionParam.equals("") && strPriceParam.equals("") && pictureParam.equals("") && linkToWebsiteParam.equals("")) {
				request.setAttribute("idGift", idGift);
				request.setAttribute("modifyGiftError",
						"Vous devez entrer au moins un des champs.");
				getServletContext().getRequestDispatcher("/WEB-INF/ModifyGift.jsp").forward(request, response);
			}
			else {
				
				double priceParam = 0;
				if (strPriceParam != null && !strPriceParam.equals("")) {
					priceParam = Double.parseDouble(strPriceParam);
				}
				

				boolean receipt = false;
				ListGift listgift = customer.getMyListGifts().stream().filter(l -> l.getIdListGift() == idListGift)
						.findFirst().orElse(null);
				Gift gift = listgift.getGifts().stream().filter(g -> g.getIdGift() == idGift).findFirst().orElse(null);

				Gift giftWithoutList = createGiftWithoutList(nameParam, descriptionParam, priceParam, pictureParam,
						linkToWebsiteParam, gift);

				
				receipt = gift.update(giftWithoutList);

				if (receipt) {
					request.setAttribute("idGift", idGift);
					request.setAttribute("modifyGiftSuccess", "Modification réussie.");
					getServletContext().getRequestDispatcher("/WEB-INF/ModifyGift.jsp").forward(request, response);
				} else {
					request.setAttribute("idGift", idGift);
					request.setAttribute("modifyGiftError",
							"Il semble qu'un prolème se soit déroulé lors de la modification.");
					getServletContext().getRequestDispatcher("/WEB-INF/ModifyGift.jsp").forward(request, response);
				}
				
			}

			
		}
		else {
			request.setAttribute("idGift", idGift);
			getServletContext().getRequestDispatcher("/WEB-INF/ModifyGift.jsp").forward(request, response);
		}

	}

	public Gift createGiftWithoutList(String nameParam, String descriptionParam, double priceParam, String pictureParam,
			String linkToWebsiteParam, Gift gift) {
		String name;
		String description;
		double price;
		String picture;
		String linkToWebsite;

		//Ne fonctionne pas
		// String linkToWebsite = linkToWebsiteParam == null ? gift.getPicture() :
		

		if (nameParam == null || nameParam.equals("")) {
			name = gift.getName();
		} else {
			name = nameParam;
		}

		if (descriptionParam == null || descriptionParam.equals("")) {
			description = gift.getDescription();
		} else {
			description = descriptionParam;
		}

		if (priceParam == 0) {
			price = gift.getPrice();
		} else {
			price = priceParam;
		}

		if (pictureParam == null || pictureParam.equals("")) {
			picture = gift.getPicture();
		} else {
			picture = pictureParam;
		}

		if (linkToWebsiteParam == null || linkToWebsiteParam.equals("")) {
			linkToWebsite = gift.getLinkToWebsite();
		} else {
			linkToWebsite = linkToWebsiteParam;
		}

		Gift giftWithoutList = new Gift(gift.getIdGift(), name, description, price, gift.getPriority(), picture,
				gift.isBooked(), gift.isMultiplePayment(), linkToWebsite, null);

		return giftWithoutList;
	}

	public boolean checkErrors(ArrayList<String> errors) {
		boolean error = false;

		for (int i = 0; i < errors.size() && error != true; i++) {
			if (!errors.get(i).isEmpty()) {
				error = true;
			}
		}

		return error;
	}
}
