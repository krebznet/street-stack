package com.dunkware.genesis.protocol.pojo;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class UserPojo {

    private Integer id;

    @NotBlank(message = "First Name cannot be blank")
    private String firstName;
    
    private String lastName; 

    @NotBlank(message = "Username cannot be blank")
    private String username;

    @NotBlank(message = "Email cannot be blank")
    private String email;

    @NotBlank(message = "Password cannot be blank")
    private String password;

    @NotEmpty(message = "Timezone cannot be blank")
    private String timezone;

    @NotEmpty(message = "Roles cannot be blank")
    private List<String> roles = new ArrayList<>();

}
