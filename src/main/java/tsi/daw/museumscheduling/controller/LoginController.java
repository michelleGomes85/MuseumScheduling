package tsi.daw.museumscheduling.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import tsi.daw.museumscheduling.dao.AppUserDAO;
import tsi.daw.museumscheduling.model.AppUser;

@Controller
public class LoginController {

	@RequestMapping("/loginForm")
	public String loginForm(HttpSession session) {
		
		if (session.getAttribute("user") != null)
			return "redirect:/home_page"; 
		
		return "login";
	}

	@RequestMapping("/login")
	public String login(AppUser user, HttpSession session) {
		
		AppUserDAO userDAO = new AppUserDAO();
		AppUser authenticatedUser = userDAO.validateLogin(user);

		if (authenticatedUser != null) {
			session.setAttribute("user", authenticatedUser);
			session.setAttribute("userProfile", authenticatedUser.getUserProfile());
			return "redirect:/home_page"; 
		}

		return "login"; 
	}

	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:/home_page";
	}
}