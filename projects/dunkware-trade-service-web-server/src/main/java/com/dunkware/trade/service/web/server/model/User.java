package com.dunkware.trade.service.web.server.model;

import java.time.LocalDateTime;

public class User {
	
    Long userId;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
    
    public static void main(String[] args) {
		User user = new User();
		user.setPassword("test");
		user.setFirstName("Alpha");
		user.setLastName("Krebs");
		user.setEmail("alpha@dunkware.com");
		user.setAddress("102 Dunkstreet, New York, New York");
		user.setCreatedOn(LocalDateTime.now());
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

    public Long getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public LocalDateTime getUpdatedOn() {
        return updatedOn;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public void setUpdatedOn(LocalDateTime updatedOn) {
        this.updatedOn = updatedOn;
    }
}
