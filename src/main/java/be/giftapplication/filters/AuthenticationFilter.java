package be.giftapplication.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.giftapplication.javabeans.Customer;


public class AuthenticationFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpSession session  = ((HttpServletRequest) request).getSession(false);
		
		if(session != null) {
			Customer customer = (Customer) session.getAttribute("customer");
			if(customer.getIdCustomer() != 0) {
				chain.doFilter(request, response);
			}
			else {
				//renvoyer au Register/Home ?
				((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED);
			}
		}
		else {
			((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED);
		}
		
	}

}
