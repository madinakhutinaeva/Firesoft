package io.firesoft.service;



import javax.transaction.Transactional;

import io.firesoft.model.User;
import io.firesoft.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.stereotype.Service;


@Service
public class SampleUserDetailsService extends JdbcDaoImpl{
    
    @Autowired
    private UserRepository userRepository;
    
    
    @Transactional
     public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
        
         
            User user = userRepository.findByUsername(username);
    
            if (user==null) {
                logger.debug("Query returned no results for user '" + username + "'");
     
                throw new UsernameNotFoundException(
                        messages.getMessage("JdbcDaoImpl.notFound", new Object[]{username}, "Username {0} not found"));
            }
            return new SampleUserDetails(user);
            
        }
}
