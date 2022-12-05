package com.example.vladik.models;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "people")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person implements UserDetails{
	
	//public static long ID =1;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
	private long id;
	@Column(name = "login", unique = true)
	private String login;
	@Column(name = "password", length = 1000)
	private String password;
	 
	
	public String getLogin() {
		return login;
	}
	
	public String getPassword() {
		return password;
	}
	
	public long getId() {
		return id;
	}
	
	public Person(String login, String password) {
		this.login = login;
		this.password = password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return login;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	

}
