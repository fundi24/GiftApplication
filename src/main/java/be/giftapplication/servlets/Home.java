package be.giftapplication.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.giftapplication.javabeans.Customer;

/**
 * Servlet implementation class Home
 */
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Home() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<String> errors = new ArrayList<>();
        for(int i=0; i<2; i++)
        {
        	errors.add("");
        }
        request.setAttribute("errors", errors);
        ServletContext context = getServletContext();
        RequestDispatcher dispatcher = context.getRequestDispatcher("/WEB-INF/Home.jsp");
        dispatcher.include(request, response);
        
        }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<String> errors = new ArrayList<>();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if(request.getParameter("submit") != null) {
            errors = checkParameters(username, password);
            if(errors.size() == 0) {
                Customer customer = Customer.login(username, password);
                //a continuer
            }
            else {
                request.setAttribute("errors", errors);
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/Home.jsp");
                dispatcher.forward(request, response);
            }
        }
    
        
    }
    
    public ArrayList<String> checkParameters(String username, String password) {
        ArrayList<String> errors = new ArrayList<>();
        
        if(username == null || username.isEmpty()) {
            errors.add("Le champ [Nom d'utilisateur] est vide.");
        }
        
        if(password == null || password.isEmpty()) {
            errors.add("Le champ [Password] est vide.");
        }
        
        return errors;
        
    }

}
