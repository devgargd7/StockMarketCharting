package com.stockmarketcharting.service;
import java.awt.print.Pageable;
import java.util.List;

import org.springframework.data.domain.Page;
//import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.stockmarketcharting.Exception.EmailExistsException;
import com.stockmarketcharting.Exception.UsernameExistsException;
import com.stockmarketcharting.model.UserEntity;


public interface UserService{
	
    UserEntity findUserByEmail(String email);
    UserEntity findUserByUserName(String username) throws UsernameNotFoundException;
    void saveUser(UserEntity user) throws EmailExistsException, UsernameExistsException;
    public void updateUser(UserEntity user);
//    Boolean removeAll();

//    void removeById(Long id);

    UserEntity findById(Long id);

    public void updatePassword(String newPassword, UserEntity user);
    public void updateMobile(String newMobile, UserEntity user);
    
//    Page<User> searchByTerm(String name, Pageable pageable);
//
//    Page<User> listUsers(Pageable pageable);
//
    List<UserEntity> getAllUsers();
    
    
}
