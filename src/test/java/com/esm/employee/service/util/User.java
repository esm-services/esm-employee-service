package com.esm.employee.service.util;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;

public class User {
	
	private String userName;
	
	private String password;
	
	private boolean b;
	
	private boolean c;
	
	private boolean d;
	
	private boolean e;
	
	private List<GrantedAuthority> authorities;

	public User(String string, String string2, boolean b, boolean c, boolean d, boolean e,
			List<GrantedAuthority> authorities) {
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isB() {
		return b;
	}

	public void setB(boolean b) {
		this.b = b;
	}

	public boolean isC() {
		return c;
	}

	public void setC(boolean c) {
		this.c = c;
	}

	public boolean isD() {
		return d;
	}

	public void setD(boolean d) {
		this.d = d;
	}

	public boolean isE() {
		return e;
	}

	public void setE(boolean e) {
		this.e = e;
	}

	public List<GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}
}
