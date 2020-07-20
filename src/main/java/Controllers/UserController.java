package Controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import Models.LoginDTO;
import Models.User;
import Service.UserService;

public class UserController {

	private static final UserService us = new UserService();
	private static final ObjectMapper om = new ObjectMapper();

	public List<User> findAllUsers() {
		return us.findAllUsers();
	}

	public User findById(int id) {
		return us.findById(id);
	}

	public User findByUsername(String username) {
		return us.findByUsername(username);
	}

	public void addUser(HttpServletRequest req, HttpServletResponse res, HttpSession ses) throws IOException {
		LoginDTO l = (LoginDTO) ses.getAttribute("user");
		User user = findByUsername(l.username);

		if (user.getRole().getRoleId() == 4) {

			if (req.getMethod().equals("POST")) {
				BufferedReader reader = req.getReader();

				StringBuilder s = new StringBuilder();

				String line = reader.readLine();

				while (line != null) {
					s.append(line);
					line = reader.readLine();
				}

				String body = new String(s);

				User u = om.readValue(body, User.class);

				if (us.addUser(u)) {
					String json = om.writeValueAsString(u);
					res.setStatus(201);
					res.getWriter().println(json);
				} else {
					res.setStatus(400);
					res.getWriter().println("Invalid fields");

				}
			}
		} else {
			res.setStatus(401);
			res.getWriter().println("The requested action is not permitted");

		}

	}

}
