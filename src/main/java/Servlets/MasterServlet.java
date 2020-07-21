package Servlets;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Controllers.LoginController;
import Controllers.UserController;

public class MasterServlet extends HttpServlet {

	private static final LoginController lc = new LoginController();
	private static final UserController uc = new UserController();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		res.setContentType("application/json");
		res.setStatus(404);

		final String URI = req.getRequestURI().replace("/rocp-project/", "");

		String[] portions = URI.split("/");

		try {
			switch (portions[0]) {
			case "login":
				lc.login(req, res);
				break;

			case "logout":
				lc.logout(req, res);
				break;

			case "register":
				HttpSession ses = req.getSession(false);
				if (ses != null && ((Boolean) ses.getAttribute("loggedin"))) {
					uc.addUser(req, res, ses);
				} else {
					res.setStatus(400);
					res.getWriter().println("There was no user logged into the session");
				}
				break;

			case "users":
				ses = req.getSession(false);
				if (ses != null && ((Boolean) ses.getAttribute("loggedin"))) {
					uc.manageUser(req, res, ses, portions);
				} else {
					res.setStatus(400);
					res.getWriter().println("There was no user logged into the session");
				}
				break;
				
			case "accounts":
				ses = req.getSession(false);
				if (ses != null && ((Boolean) ses.getAttribute("loggedin"))) {
					
					if (req.getMethod().equals("POST") && portions.length == 2) {
						//transaction controller takes over
					} else {
						//accounts controller takes over
					}
					// uc.manageUser(req, res, ses, portions);
				} else {
					res.setStatus(400);
					res.getWriter().println("There was no user logged into the session");
				}
				break;
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
			res.setStatus(400);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}
