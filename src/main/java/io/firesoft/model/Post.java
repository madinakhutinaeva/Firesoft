package io.firesoft.model;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="posts")
public class Post {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="POST_IP")
	private Long id;

	@NotNull
	@NotEmpty
	@Column(name="TITLE")
	private String title;

	@NotNull
	@NotEmpty
	
	@Column(name="CONTENT")
	@Lob
	 private String content;
	
	@NotNull
	@Column(name="CATEGORY")
	private String themes;
	
	
	
    @Column(name = "POSTAT")
    private String postDate;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
	
	
	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public void setPostDate(String postDate) {
		this.postDate = postDate;
	}


	public String getPostDate() {
	return postDate;
}

	
public void setPostDate() {
	Date date =new Date();
	DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy, HH:mm:ss");
	dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+3:00"));
	postDate = dateFormat.format(date);
}

	public String getThemes() {
		return themes;			
	}

	public void setThemes(String themes) {
		this.themes = themes;
	}

	public String getContent() {
		return content;
	}
	
	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	
	
}
