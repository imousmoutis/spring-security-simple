package spring.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.model.LoggedUser;
import spring.model.User;
import spring.repository.UserDAO;

@Service("userService")
@Transactional
public class UserServiceImpl extends GenericServiceImpl<User> implements UserService{
	
	@Autowired
	private UserDAO userDAO;

	@Override
	public User findByUsername(String username) {
		
		return userDAO.findByUsername(username);
	}

	@Override
	public LoggedUser getLoggedUser() {
		
		LoggedUser loggedUser = null;
		List<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if (auth.getPrincipal()!= "anonymousUser")
			 loggedUser  = (LoggedUser)auth.getPrincipal();
		else
			loggedUser = new LoggedUser("Not Found", "NA", false, false, false, false, auths, 0, "Not Found");
		
		return loggedUser;
	}

}
