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
import com.stockmarketcharting.model.UserEntity;


@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserDao userDao;
	
	@Autowired
	    private PasswordEncoder passwordEncoder;

	@Override
	public UserEntity findUserByEmail(String email) {
		return userDao.findByEmail(email);
	}

	 @Override
	    public void saveUser(UserEntity user) 
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
	    public void updateUser(UserEntity user) {
 
	
		    userDao.save(user);
		 
	 }

	 @Override
	 public void updatePassword(String newPassword, UserEntity user){
	 	String encodedPasword = passwordEncoder.encode(newPassword);
	    user.setPassword(encodedPasword);
	    userDao.save(user);
	 }
	 
	 @Override
	 public void updateMobile(String newMobile, UserEntity user) {
		 user.setEmail(newMobile);
		 userDao.save(user);
	 }
	 
	@Override
	public UserEntity findById(Long id) {
		return userDao.getById(id);
	}

	@Override
	public List<UserEntity> getAllUsers() {
		return userDao.findAll();
	}

	@Override
	public UserEntity findUserByUserName(String username) throws UsernameNotFoundException {
		UserEntity user = userDao.findByUsername(username);
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