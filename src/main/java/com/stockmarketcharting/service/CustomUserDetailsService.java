package com.stockmarketcharting.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stockmarketcharting.dao.UserDao;
import com.stockmarketcharting.model.Appuser;

@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService{

    @Autowired
    private UserDao userDao;
    
    private static Collection<? extends GrantedAuthority> getAuthorities (Appuser user) {
    	Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(user.getUserType()));
        return authorities;
    }
    
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	
        Appuser user = userDao.findByUsername(username);
        if (user == null) {
        	throw new UsernameNotFoundException("No user found with username: " + username);
        }
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        
        return new org.springframework.security.core.userdetails.User(
          user.getUsername(), user.getPassword(), enabled, accountNonExpired,
          credentialsNonExpired, accountNonLocked, getAuthority(user));
    }
    
    private Set getAuthority(Appuser user) {
        Set authorities = new HashSet<>();
//		user.getUserType().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getUserType()));
//		});
		return authorities;
	}
    

}
