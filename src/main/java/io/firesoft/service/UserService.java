package io.firesoft.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import javax.transaction.Transactional;

import io.firesoft.model.Post;
import io.firesoft.model.Role;
import io.firesoft.model.User;
import io.firesoft.repository.PostRepository;
import io.firesoft.repository.RoleRepository;
import io.firesoft.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository userRepository;

    	@PersistenceContext(unitName="punit")
    	private EntityManager em;

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private RoleRepository roleRepository;

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User findOne(int id) {
		return userRepository.findOne(id);
	}
	public User findUserByEmail(String email){
        	CriteriaBuilder builder = em.getCriteriaBuilder();
        	CriteriaQuery<User> query = builder.createQuery(User.class);
        	Root<User> root = query.from(User.class);

        	query.select(root).where(builder.equal(root.get("username").as(String.class), email));
        	return findUserByCriteria(query);
	}

	private User findUserByCriteria(CriteriaQuery<User> criteria) {

        	TypedQuery<User> pQuery= em.createQuery(criteria);

        	User result = null;
        	try{
            		result = pQuery.getSingleResult();
        	} catch (NoResultException e) {
            		return result;
        	}
        	return result;
	}

    @Transactional
    public User findOneWithPosts(int id) {
        User user = findOne(id);
        List<Post> posts = postRepository.findByUser(user, new PageRequest(0, 10, Direction.DESC, "title"));
        user.setPosts(posts);
        return user;
    }

    public void save(User user) {
        user.setEnabled(true);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        
        
        List<Role> roles = new ArrayList<Role>();
        roles.add(roleRepository.findByName("ROLE_USER"));
        user.setRoles(roles);
        userRepository.save(user);
    }
    
    public User findOneWithPosts(String  name) {
        User user = userRepository.findByUsername(name);
        return findOneWithPosts(user.getId());
    }

    public User findByName(String name) {
        
        return userRepository.findByUsername(name);
    }

    public void delete(int id) {
        userRepository.delete(id);
        
    }
}
