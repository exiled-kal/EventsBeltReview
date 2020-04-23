package com.houlder.eventsbeltreviewer.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.houlder.eventsbeltreviewer.models.User;
import com.houlder.eventsbeltreviewer.services.UserService;
import com.houlder.eventsbeltreviewer.validator.UserValidator;

@Controller
public class Users {
    private final UserService userService;
    private final UserValidator userValidator;
    
    public Users(UserService userService, UserValidator userValidator) {
        this.userService = userService;
        this.userValidator = userValidator;
    }
    
    // -------------------- ROUTES FOR REGISTER -------------------- //
    
    @GetMapping("/")
    // THE ROOT FOLDER TAKES THE USER TO THE REGISTRATION PAGE
    public String index(@ModelAttribute("user") User user) {
    	return "index.jsp";
    }
    
    @PostMapping("/registration")
    public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session) {
        // VALIDATE THAT SECOND PASSWORD MATCHES THE FIRST
    	userValidator.validate(user, result);
        // IF THERE ARE ERRORS, SHOW THE ERRORS AND RETURN USER TO REGISTRATION PAGE
        if (result.hasErrors()) {
			return "index.jsp;";
		}
    	// REGISTER THE USER
		User u = userService.registerUser(user);
		session.setAttribute("userId", u.getId());
		// REDIRECT USER TO THE HOME PAGE
		return "redirect:/home";
    }

    // -------------------- ROUTES FOR LOGIN -------------------- //   
    
    @PostMapping("/login")
    public String loginUser(@ModelAttribute("user") User user, @RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpSession session) {
        // if the user is authenticated, save their user id in session
        // else, add error messages and return the login page
    	boolean isAuthenticated = userService.authenticateUser(email, password);
		if (isAuthenticated) {
    		User u = userService.findByEmail(email);
    		session.setAttribute("userId", u.getId());
    		return "redirect:/home";
    	} 
		else {
    		model.addAttribute("error", "Invalid Credentials. Please try again.");
    		return "index.jsp";
    	}
    }
    
    @RequestMapping("/home")
    public String home(HttpSession session, Model model) {
        // get user from session, save them in the model and return the home page
    	Long userId = (Long) session.getAttribute("userId");
    	User u = userService.findUserById(userId);
    	model.addAttribute("user", u);
    	return "homePage.jsp";			
    }
    
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
    	session.invalidate();
    	return "redirect:/login";
    }
}
