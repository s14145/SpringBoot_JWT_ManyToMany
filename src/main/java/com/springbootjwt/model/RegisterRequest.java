package com.springbootjwt.model;

import com.springbootjwt.entity.Roles;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String firstname;

    private String lastname;

    private String email;

    private String password;

    private List<Roles> roles;
}
