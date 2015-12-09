package spring.model;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

//This class is used in order to give more information to the authenticated user
public class LoggedUser extends User {
	
	private static final long serialVersionUID = -3531439484732724601L;

	private Integer id;

	private String fullName;

	public LoggedUser(String username, String password, boolean enabled,
			boolean accountNonExpired, boolean credentialsNonExpired,
			boolean accountNonLocked, List<GrantedAuthority> authorities, Integer id,
			String fullname) {

		super(username, password, enabled, accountNonExpired,
				credentialsNonExpired, accountNonLocked, authorities);

		this.id = id;
		this.fullName = fullname;

	}
	

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getId() {
		return id;
	}

	public String getFullName() {
		return fullName;
	}


}
