package io.firesoft.model;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Entity
@Table(name="role", uniqueConstraints = {@UniqueConstraint(columnNames={"name", "id"})})
public class Role {

	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(name = "name", unique=true)
	private String name;
	
	@ManyToMany(mappedBy="roles", cascade=javax.persistence.CascadeType.REMOVE)
	private List<User> user;
	
	public List<User> getUser() {
		return user;
	}
	public void setUser(List<User> user) {
		this.user = user;
	}
	public Integer getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
}
