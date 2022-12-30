package be.giftapplication.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.giftapplication.javabeans.Customer;
import be.giftapplication.javabeans.Notification;

/**
 * Servlet implementation class MyNotifications
 */
public class MyNotifications extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyNotifications() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		Customer customer = (Customer) session.getAttribute("customer");
		boolean receipt = customer.getCustomerNotifications();
		if(receipt) {
			session.setAttribute("customer", customer);
			request.setAttribute("notifications", customer.getNotifications());
		}
		getServletContext().getRequestDispatcher("/WEB-INF/MyNotifications.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idNotification = Integer.parseInt(request.getParameter("idNotification"));
		Notification notification = new Notification();
		notification.setIdNotification(idNotification);
		boolean result = notification.update();
		if(result) {
			HttpSession session = request.getSession(false);
			Customer customer = (Customer) session.getAttribute("customer");
			for(int i =0; i< customer.getNotifications().size(); i++)
			{
				if(customer.getNotifications().get(i).getIdNotification() == idNotification )
				{
					customer.getNotifications().get(i).setRead(true);
				}
			}
			request.setAttribute("notifications", customer.getNotifications());
			getServletContext().getRequestDispatcher("/WEB-INF/MyNotifications.jsp").forward(request, response);
		}
	}

}
