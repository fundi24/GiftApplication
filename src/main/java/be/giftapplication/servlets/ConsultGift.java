package be.giftapplication.servlets;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Base64;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.giftapplication.javabeans.Customer;
import be.giftapplication.javabeans.Gift;
import be.giftapplication.javabeans.ListGift;

/**
 * Servlet implementation class ConsultGift
 */

public class ConsultGift extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConsultGift() {
        super();
       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		Customer customer = (Customer) session.getAttribute("customer");
		int idListGift = (int) session.getAttribute("idListGift");
		int idGift = Integer.parseInt(request.getParameter("idGift"));
		ListGift listGift = customer.getMyListGifts().stream().filter(l -> l.getIdListGift() == idListGift).findFirst().orElse(null);
		Gift gift = listGift.getGifts().stream().filter(l -> l.getIdGift() == idGift).findFirst().orElse(null);
		
		request.setAttribute("gift", gift);
		
	    request.setAttribute("picture", gift.getPicture());
		
		request.setAttribute("idListGift", idListGift);
		getServletContext().getRequestDispatcher("/WEB-INF/ConsultGift.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
