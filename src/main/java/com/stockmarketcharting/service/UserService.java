package com.stockmarketcharting.service;
import java.awt.print.Pageable;
import java.util.List;

import org.springframework.data.domain.Page;
//import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.stockmarketcharting.Exception.EmailExistsException;
import com.stockmarketcharting.Exception.UsernameExistsException;
import com.stockmarketcharting.model.User;


public interface UserService{
	
    User findUserByEmail(String email);
    User findUserByUserName(String username) throws UsernameNotFoundException;
    void saveUser(User user) throws EmailExistsException, UsernameExistsException;
    public void updateUser(User user);
//    Boolean removeAll();

//    void removeById(Long id);

    User findById(Long id);

    public void updatePassword(String newPassword, User user);
    public void updateMobile(String newMobile, User user);
    
//    Page<User> searchByTerm(String name, Pageable pageable);
//
//    Page<User> listUsers(Pageable pageable);
//
    List<User> getAllUsers();
    
    
}
