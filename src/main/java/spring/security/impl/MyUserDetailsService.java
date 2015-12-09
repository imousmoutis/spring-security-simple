package spring.security.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import spring.model.LoggedUser;
import spring.model.User;
import spring.service.UserService;

/**
 * Implements Spring Security {@link UserDetailsService} that is injected into authentication provider in configuration XML.
 * It interacts with domain, loads user details and wraps it into {@link UserContext} which implements Spring Security {@link UserDetails}.
 */
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserService userService;

	/**
	 * This will be called from
	 * {@link org.springframework.security.authentication.dao.DaoAuthenticationProvider#retrieveUser(java.lang.String, org.springframework.security.authentication.UsernamePasswordAuthenticationToken)}
	 * when {@link AuthenticationService#authenticate(java.lang.String, java.lang.String)} calls
	 * {@link AuthenticationManager#authenticate(org.springframework.security.core.Authentication)}. Easy.
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		LoggedUser logged = null;
		
		User user = userService.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User " + username + " not found");
		}else{
			List<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
			
			auths.add(new SimpleGrantedAuthority("ROLE_USER"));
			logged = new LoggedUser(user.getUsername(), user.getPassword(), true, true, true, true, auths, user.getId(), user.getFullName());
			
		}
		return logged;
	}
}
