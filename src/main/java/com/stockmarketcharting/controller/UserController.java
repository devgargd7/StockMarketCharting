package com.stockmarketcharting.controller;

import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.stockmarketcharting.Exception.EmailExistsException;
import com.stockmarketcharting.Exception.UsernameExistsException;
import com.stockmarketcharting.dao.UserDao;
import com.stockmarketcharting.dto.AuthenticationRequest;
import com.stockmarketcharting.dto.AuthenticationResponse;
import com.stockmarketcharting.model.AppUser;
import com.stockmarketcharting.service.CustomUserDetailsService;
import com.stockmarketcharting.service.UserService;
import com.stockmarketcharting.util.JwtUtil;



@RestController
public class UserController {
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtTokenUtil;

	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
			);
		}
		catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}


		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());
		final AppUser user = userService.findUserByUserName(authenticationRequest.getUsername());
		final String jwt = jwtTokenUtil.generateToken(userDetails);
		return ResponseEntity.status(HttpStatus.OK).body(new AuthenticationResponse(jwt, user));
	}

    @GetMapping("/users")
    public List<AppUser> getAllUsers() {
    	return userService.getAllUsers();
    }
	
    @RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> registerUserAccount (@RequestBody AppUser user, HttpServletRequest request) 
			//throws EmailExistsException, Exception 
	{
	    
//    	if(user.getUserType() == null) {
//    		user.setUserType();
//    	}
    	
//    	try {
	        userService.saveUser(user);
//	    } catch (EmailExistsException eeEx) {
//	    	ResponseEntity.badRequest().body("Email Aready Exists");
//	    } catch (Exception e) {
//	    	ResponseEntity.badRequest().body(e);
//	    }
	    

	    return ResponseEntity.ok(user);
	}
	
	@PostMapping("/update/password")
	public ResponseEntity<?> updatePassword (@RequestBody String password, HttpServletRequest request
	  ) throws UsernameNotFoundException{
		if(password==null || password=="") return ResponseEntity.badRequest().body("invalid password");
		  final String authorizationHeader = request.getHeader("Authorization");

	        String username = null;
	        String jwt = null;

	        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
	            jwt = authorizationHeader.substring(7);
	            username = jwtTokenUtil.extractUsername(jwt);
	        }
	        
	        try{
	        	AppUser user = userService.findUserByUserName(username);
	        	userService.updatePassword(password, user);
	        }catch(UsernameNotFoundException e) {
	        	return ResponseEntity.badRequest().body(e);
	        }

	    return ResponseEntity.ok("password updated");
	}
	
	@PostMapping("/update/mobile")
	public ResponseEntity<?> updateMobile (@RequestBody String mobileNumber, HttpServletRequest request
	  ) throws UsernameNotFoundException{
		System.out.println(mobileNumber);
		  final String authorizationHeader = request.getHeader("Authorization");

	        String username = null;
	        String jwt = null;

	        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
	            jwt = authorizationHeader.substring(7);
	            username = jwtTokenUtil.extractUsername(jwt);
	        }
	        
	        try{
	        	AppUser user = userService.findUserByUserName(username);
	        	userService.updateMobile(mobileNumber, user);
	        }catch(UsernameNotFoundException e) {
	        	return ResponseEntity.badRequest().body(e);
	        }

	    return ResponseEntity.ok("password updated");
	}
	
	@GetMapping("/confirm/{username}")
	ResponseEntity<?> confirmUser(@PathVariable String username){
		AppUser user = userService.findUserByUserName(username);
		user.setConfirmed(true);
		try {
			userService.updateUser(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.ok(user);
	}
	
//	@GetMapping("/email/{username}")
//    public void sendEmail(@PathVariable String username) throws AddressException, MessagingException {
//
//    	User user = userService.findUserByUserName(username);
////        final String username = "";
////        final String password = "";
//
//        Properties prop = new Properties();
//        prop.put("mail.smtp.host", "smtp.gmail.com");
//        prop.put("mail.smtp.port", "587");
//        prop.put("mail.smtp.auth", "true");
//        prop.put("mail.smtp.starttls.enable", "true"); //TLS
//
//        Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication("", "");
//            }
//        });
//
//        try {
//
//            Message message = new MimeMessage(session);
//            message.setFrom(new InternetAddress(""));
//            message.setRecipients(
//                    Message.RecipientType.TO,
//                    InternetAddress.parse(user.getEmail())
//            );
//            message.setSubject("User confirmation email");
//
//            message.setContent(
//                    "<h1><a href =\"http://localhost:8085/confirm/" + user.getUsername() + "/\"> Click to confirm </a></h1>",
//                    "text/html");
//            Transport.send(message);
//
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }
//
//    }
}

/*
User TODO:
-register
-login
-logout
-email confirmation
-update profile/ password
*/