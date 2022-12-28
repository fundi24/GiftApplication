package be.giftapplication.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
        HttpSession session = request.getSession();
        if (session.isNew() == false) {
            session.invalidate();
        }
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
        for(int i=0; i<2; i++)
        {
        	errors.add("");
        }
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if(request.getParameter("submit") != null) {
            errors = checkParameters(username, password, errors);
            if(!checkErrors(errors)) {
                Customer customer = Customer.login(username, password);
                if(customer == null) {
                	request.setAttribute("usernameSave", username);
                	request.setAttribute("passwordSave", password);
                	request.setAttribute("loginError", "Utilisateur non trouvé.");
                	request.setAttribute("errors", errors);
                	getServletContext().getRequestDispatcher("/WEB-INF/Home.jsp").forward(request, response);
                	
                }
                else {
                	HttpSession session = request.getSession();
                    if (session.isNew() == false) {
                        session.invalidate();
                        session = request.getSession();
                        session.setAttribute("customer", customer);
                        response.sendRedirect("mygiftlists");
                    }
                	
                }

            }
            else {
            	request.setAttribute("usernameSave", username);
            	request.setAttribute("passwordSave", password);
                request.setAttribute("errors", errors);
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/Home.jsp");
                dispatcher.forward(request, response);
            }
        }
    
        
    }
    
    public ArrayList<String> checkParameters(String username, String password, ArrayList<String> errors) {
        
        
        if(username == null || username.isEmpty()) {
            errors.add(0,"Le champ [Nom d'utilisateur] est vide.");
        }
        
        if(password == null || password.isEmpty()) {
            errors.add(1,"Le champ [Password] est vide.");
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
