package com.io.nest.controller;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.io.nest.exception.UserNotFoundException;
import com.io.nest.model.User;
import com.io.nest.service.LoginService;
import com.io.nest.utils.Constants;
import org.slf4j.Logger;


@RestController
@RequestMapping("/login")
@CrossOrigin("*")
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

	
	@PostMapping(value="/addUser",consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public User postUser(@RequestBody User user)  {
		return loginService.addUser(user);
		
	}
	
	
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getUser(@PathVariable(value = Constants.USERID) long uId) throws UserNotFoundException{
//        try {
        return new ResponseEntity<>(loginService.getUser(uId),HttpStatus.OK);
//        }
//        catch(UserNotFoundException e){
//        	return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
//        	
//        }
	}
	
//	@GetMapping("/Email/{email}")
//	public ResponseEntity<?> getEmail(@PathVariable(value = "email") String e) throws UserNotFoundException{
//        
//        return new ResponseEntity<>(loginService.getUserEmail(e),HttpStatus.OK);
//	}
//	 @GetMapping("/")
//     public ModelAndView login() {
//	     ModelAndView mav = new ModelAndView("login");
//	     mav.addObject("user", new User());
//	    return mav;
//	 }
//	 
//	 @PostMapping("/")
//	    public String login(@ModelAttribute("user") User user ) {
//	    
//	     User oauthUser = loginService.login(user.getEmail(), user.getPassword());
//	    
//	 
//	     System.out.print(oauthUser);
//	     if(Objects.nonNull(oauthUser))
//	     {
//	  
//	     return "redirect:/dashboard";
//	    
//	    
//	     } else {
//	     return "redirect:/";
//	    
//	    
//	     }
//	 
//	}
	 @GetMapping("/signin")
	 public ResponseEntity<?> getUser(@RequestParam String email, @RequestParam String password) throws UserNotFoundException{
	        	LOGGER.info("logging services started");
	        	LOGGER.warn("its a warning message");
	    		
	        return new ResponseEntity<>(loginService.getLogin(email, password),HttpStatus.OK);

	        
		}
	

}
