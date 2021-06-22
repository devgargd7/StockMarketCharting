package com.stockmarketcharting.service;
import java.awt.print.Pageable;
import java.util.List;

import org.springframework.data.domain.Page;
//import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.stockmarketcharting.Exception.EmailExistsException;
import com.stockmarketcharting.Exception.UsernameExistsException;
import com.stockmarketcharting.model.Authuser;


public interface UserService{
	
    Authuser findUserByEmail(String email);
    Authuser findUserByUserName(String username) throws UsernameNotFoundException;
    void saveUser(Authuser user); //throws EmailExistsException, UsernameExistsException;
    public void updateUser(Authuser user);
//    Boolean removeAll();

//    void removeById(Long id);

    Authuser findById(Long id);

    public void updatePassword(String newPassword, Authuser user);
    public void updateMobile(String newMobile, Authuser user);
    
//    Page<User> searchByTerm(String name, Pageable pageable);
//
//    Page<User> listUsers(Pageable pageable);
//
    List<Authuser> getAllUsers();
    
    
}
