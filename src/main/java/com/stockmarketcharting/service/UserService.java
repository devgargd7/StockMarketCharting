package com.stockmarketcharting.service;
import java.awt.print.Pageable;
import java.util.List;

import org.springframework.data.domain.Page;
//import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.stockmarketcharting.Exception.EmailExistsException;
import com.stockmarketcharting.Exception.UsernameExistsException;
import com.stockmarketcharting.model.Appuser;


public interface UserService{
	
    Appuser findUserByEmail(String email);
    Appuser findUserByUserName(String username) throws UsernameNotFoundException;
    void saveUser(Appuser user); //throws EmailExistsException, UsernameExistsException;
    public void updateUser(Appuser user);
//    Boolean removeAll();

//    void removeById(Long id);

    Appuser findById(Long id);

    public void updatePassword(String newPassword, Appuser user);
    public void updateMobile(String newMobile, Appuser user);
    
//    Page<User> searchByTerm(String name, Pageable pageable);
//
//    Page<User> listUsers(Pageable pageable);
//
    List<Appuser> getAllUsers();
    
    
}
