package Servlets;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Controllers.LoginController;

public class MasterServlet extends HttpServlet {
	
	private static final LoginController lc = new LoginController();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
	res.setContentType("application/json");
	res.setStatus(404);
	
	final String URI = req.getRequestURI().replace("/rocp-project/", "");
	
	String[] portions = URI.split("/"); 
	
	System.out.println(Arrays.toString(portions));
	
	try {
		switch (portions[0]) {
		case "login":
			lc.login(req,res);
			break;
		case "logout":
			lc.logout(req,res);
			break;
		
		}
	}catch(NumberFormatException e) {
		e.printStackTrace();
		res.getWriter().println("The id you provided is not an integer");
		res.setStatus(400);
		}
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}
	
	

}
