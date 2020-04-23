package com.houlder.eventsbeltreviewer.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.houlder.eventsbeltreviewer.models.User;
import com.houlder.eventsbeltreviewer.services.UserService;

@Component
public class UserValidator implements Validator {

	// Edward had these in his video, but these weren't in the dojo instructions
	private final UserService userService;
	
	public UserValidator(UserService userService) {
		this.userService = userService;
	}
	
	
	
    // 1
    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }
    
    // 2
    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        
        if (!user.getPasswordConfirmation().equals(user.getPassword())) {
            // 3
            errors.rejectValue("passwordConfirmation", "Match");
        }     
        
        User checkUser = userService.findByEmail(user.getEmail());
        if (checkUser != null) {
        	errors.rejectValue("duplicate", "Duplicate");
        }
    }
}
