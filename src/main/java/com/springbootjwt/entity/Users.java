package com.springbootjwt.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "USERS")
public class Users implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name =  "first_name")
    @NotBlank(message = "First Name is a required field")
    @Size(min = 3, max = 20, message = "First Name should be between 3 and 20 characters")
    private String firstname;

    @Column(name = "last_name")
    @Size(min = 3, max = 20, message = "First Name should be between 3 and 20 characters")
    private String lastname;

    @Column(name = "email")
    @Email(message = "Please enter a valid email Id", regexp="^[a-zA-Z0-9._-]+@[a-zA-Z0-9-]+\\.[a-zA-Z.]{2,5}")
    @NotBlank(message = "Email is a required field")
    private String email;

    @Column(name = "password")
    @NotBlank(message = "Password is a required field")
    @Size(min = 8, message = "Password should have at least 8 characters")
    private String password;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name ="USERS_ROLES",
               joinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "userId"),
               inverseJoinColumns = @JoinColumn(name = "ROLES_ID", referencedColumnName = "rolesId"))
    @JsonIgnore
    private List<Roles> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
       List<Roles> roles = this.getRoles();
       List<SimpleGrantedAuthority> authorities = new ArrayList<>();
       for(Roles role: roles){
           authorities.add(new SimpleGrantedAuthority(role.getName()));
       }
       return authorities;
    }

    @Override
    public String getUsername() {
        return email;
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
        return true;
    }
}