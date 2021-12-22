package com.example.ecommerce.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.Data;
import org.hibernate.annotations.NaturalId;
import org.hibernate.engine.jdbc.Size;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "users")
public class User implements Serializable {

    private static final long serialVersionUID = 4887904943282174032L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NaturalId
    @NotNull
    private String email;
    @NotNull
    private String password;
    @NotNull
    private String name;
    @NotNull
    private String phone;
    @NotNull
    private String address;
    @NotNull
    private boolean activate;
    @NotNull
    private String role = "ROLE_CUSTOMER";

    @OneToOne(mappedBy ="user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnore
    private Cart cart;
}
