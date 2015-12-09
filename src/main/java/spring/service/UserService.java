package spring.service;

import spring.model.LoggedUser;
import spring.model.User;

public interface UserService extends GenericService<User>{
	
	public User findByUsername(String username);
	
	public LoggedUser getLoggedUser();

}
