package com.walletBuddy.userservice.models.entity;


import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.vladmihalcea.hibernate.type.json.JsonType;
import com.walletBuddy.userservice.models.enums.*;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

@Entity
@Table(indexes = {@Index(name = "UNIQUE_EMAIL", columnList = "emailId", unique = true)})
public class UserInfo implements UserDetails{


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    String name;

    @Column(length = 15, unique = true)
    Long phoneNumber;
    
    String emailId;

    @Enumerated(value = EnumType.STRING)
    UserType userType;

    @Enumerated(value = EnumType.STRING)
    UserStatus userStatus;


    @CreationTimestamp
    LocalDateTime createdAt;

    @UpdateTimestamp
    LocalDateTime updatedAt;
    
    String password;
    
    String passwordRaw;
    
    @Type(JsonType.class)
    @Column(columnDefinition = "json")
    private Set<String> roles;


    @PrePersist
    public void prePersist(){
        this.userStatus = UserStatus.ACTIVE;
    }


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Long getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public String getEmailId() {
		return emailId;
	}


	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}


	public UserType getUserType() {
		return userType;
	}


	public void setUserType(UserType userType) {
		this.userType = userType;
	}


	public UserStatus getUserStatus() {
		return userStatus;
	}


	public void setUserStatus(UserStatus userStatus) {
		this.userStatus = userStatus;
	}


	public LocalDateTime getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}


	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}


	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	


	public Set<String> getRoles() {
		return roles;
	}


	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}
	


	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setPasswordRaw(String passwordRaw) {
		this.passwordRaw = passwordRaw;
	}
	
	public String getPasswordRaw() {
		return passwordRaw;
	}
	


	@Override
	public String toString() {
		return "UserInfo [id=" + id + ", name=" + name + ", phoneNumber=" + phoneNumber + ", emailId=" + emailId
				+ ", userType=" + userType + ", userStatus=" + userStatus + ", createdAt=" + createdAt + ", updatedAt="
				+ updatedAt + "]";
	}


	public UserInfo(String id, String name, Long phoneNumber, String emailId, UserType userType, UserStatus userStatus,
			LocalDateTime createdAt, LocalDateTime updatedAt) {
		this.id = id;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.emailId = emailId;
		this.userType = userType;
		this.userStatus = userStatus;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
    
	public UserInfo() {
	}


	@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return userStatus == UserStatus.ACTIVE;
    }
}
