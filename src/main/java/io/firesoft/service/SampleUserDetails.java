package io.firesoft.service;



import java.util.List;

import io.firesoft.model.Role;
import io.firesoft.model.User;
import org.springframework.security.core.authority.AuthorityUtils;


public class SampleUserDetails extends  org.springframework.security.core.userdetails.User{
    private static final long serialVersionUID = 1L;
    
    private User user;  

    
	public SampleUserDetails(User user) {
		super(user.getUsername(), user.getPassword(), AuthorityUtils
                .createAuthorityList(user.getRoles().get(user.getRoles().size()-1).getName())); // 
		this.user = user;
		List<Role> roles = user.getRoles();
		
		System.out.println("============="+roles.get(roles.size()-1).getName());
		System.out.println("============="+user.getUsername());
	}
	
	public User getUser() {
		return user;
	}

	
	
}
