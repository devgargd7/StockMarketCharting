package com.stockmarketcharting.service;
import java.awt.print.Pageable;
import java.util.List;

import org.springframework.data.domain.Page;
//import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.stockmarketcharting.Exception.EmailExistsException;
import com.stockmarketcharting.Exception.UsernameExistsException;
import com.stockmarketcharting.model.AppUser;


public interface UserService{
	
    AppUser findUserByEmail(String email);
    AppUser findUserByUserName(String username) throws UsernameNotFoundException;
    void saveUser(AppUser user); //throws EmailExistsException, UsernameExistsException;
    public void updateUser(AppUser user);
//    Boolean removeAll();

//    void removeById(Long id);

    AppUser findById(Long id);

    public void updatePassword(String newPassword, AppUser user);
    public void updateMobile(String newMobile, AppUser user);
    
//    Page<User> searchByTerm(String name, Pageable pageable);
//
//    Page<User> listUsers(Pageable pageable);
//
    List<AppUser> getAllUsers();
    
    
}
