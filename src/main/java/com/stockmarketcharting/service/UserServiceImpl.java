package com.stockmarketcharting.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.stockmarketcharting.Exception.EmailExistsException;
import com.stockmarketcharting.dao.UserDao;
import com.stockmarketcharting.model.AppUser;


@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserDao userDao;
	
	@Autowired
	    private PasswordEncoder passwordEncoder;

	@Override
	public AppUser findUserByEmail(String email) {
		return userDao.findByEmail(email);
	}

	 @Override
	    public void saveUser(AppUser user) 
	    		//throws EmailExistsException{
	    {
//		 if(userDao.findByEmail(user.getEmail()) != null)
//			 throw new EmailExistsException();
//		 else {
			String encodedPasword = passwordEncoder.encode(user.getPassword());
//			System.out.println(encodedPasword);
		    user.setPassword(encodedPasword);

		    userDao.save(user);
//		 }
	 }
	 
	 @Override
	    public void updateUser(AppUser user) {
 
	
		    userDao.save(user);
		 
	 }

	 @Override
	 public void updatePassword(String newPassword, AppUser user){
	 	String encodedPasword = passwordEncoder.encode(newPassword);
	    user.setPassword(encodedPasword);
	    userDao.save(user);
	 }
	 
	 @Override
	 public void updateMobile(String newMobile, AppUser user) {
		 user.setEmail(newMobile);
		 userDao.save(user);
	 }
	 
	@Override
	public AppUser findById(Long id) {
		return userDao.getById(id);
	}

	@Override
	public List<AppUser> getAllUsers() {
		return userDao.findAll();
	}

	@Override
	public AppUser findUserByUserName(String username) throws UsernameNotFoundException {
		AppUser user = userDao.findByUsername(username);
		return user;
	}


	
}

/*
User TODO:
-register
-login
-logout
-email confirmation
-update profile/ password
*/