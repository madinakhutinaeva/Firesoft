package io.firesoft.service;


import io.firesoft.model.Post;
import io.firesoft.model.RegistrationType;
import io.firesoft.model.Role;
import io.firesoft.model.User;
import io.firesoft.repository.PostRepository;
import io.firesoft.repository.RoleRepository;
import io.firesoft.repository.UserRepository;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class InitDbService {
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	
	@Autowired
	private PostRepository postRepository;

	@PostConstruct
	public void init() {
	    String role_user_str = "ROLE_USER";
	    Role roleUser = roleRepository.findByName(role_user_str);
	    if (roleUser == null){
    		roleUser = new Role();
    		roleUser.setName(role_user_str);
    		roleRepository.save(roleUser);
	    }
	    String role_admin_str = "ROLE_ADMIN";
	    Role roleAdmin = roleRepository.findByName(role_admin_str);
	    if (roleAdmin == null){
    		roleAdmin = new Role();
    		roleAdmin.setName(role_admin_str);
    		roleRepository.save(roleAdmin);
	    }

        Properties prop = new Properties();
        String propFileName = "auth.properties";
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

        if (inputStream != null) {
            try {
                prop.load(inputStream);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            return;
            //throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
        }
        User userAdmin = userRepository.findByUsername(prop.getProperty("admin.username"));
        if (userAdmin == null) {
    		userAdmin=new User();
    		userAdmin.setEnabled(true);
    		userAdmin.setUsername(prop.getProperty("admin.username"));
    		userAdmin.setFullName("admin");
    		List<Role> rolesAdmin = new ArrayList<Role>();
    		rolesAdmin.add(roleAdmin);
    		rolesAdmin.add(roleUser);
    		userAdmin.setRoles(rolesAdmin);
    		userAdmin.setRegType(RegistrationType.Google);
    		userAdmin.setEmail(prop.getProperty("admin.username"));
    		BCryptPasswordEncoder encoderadmin = new BCryptPasswordEncoder();
    		UUID random = UUID.randomUUID();
    		userAdmin.setPassword(encoderadmin.encode(random.toString()));
    		userRepository.save(userAdmin);
        }
		
		/*
		User Justuser=new User();
		Justuser.setEnabled(true);
		Justuser.setUsername("user");
		Justuser.setFullName("user");		
		BCryptPasswordEncoder encoderuser = new BCryptPasswordEncoder();
		userAdmin.setPassword(encoderadmin.encode("admin"));

		Justuser.setPassword(encoderuser.encode("user"));
		
		List<Role> rolesAdmin = new ArrayList<Role>();
		List<Role> rolesUser = new ArrayList<Role>();
		rolesAdmin.add(roleAdmin);
		rolesAdmin.add(roleUser);
		rolesUser.add(roleUser);
		userAdmin.setRoles(rolesAdmin);	
		Justuser.setRoles(rolesUser);
		
		userRepository.save(userAdmin);
		userRepository.save(Justuser);
		
		
		Post postAdmin = new Post();
		postAdmin.setTitle("Programmer");
		postAdmin.setThemes("Madina");
		postAdmin.setContent("MMMMMM");
		postAdmin.setUser(userAdmin);
		postRepository.save(postAdmin);
		
		
	    Post postUser = new Post();
		postUser.setTitle("Fashion");
		postUser.setThemes("Tolunya");
		postUser.setContent("FFFFFF");
		postUser.setUser(Justuser);
		postRepository.save(postUser);
		*/
			
	}

}
