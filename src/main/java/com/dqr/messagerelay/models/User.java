package com.dqr.messagerelay.models;

import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users")
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 3141932408657661663L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "first_name", nullable = false)
    @NotEmpty
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @NotEmpty
    private String lastName;

    @Column(nullable = false, unique = true)
    @NotEmpty @Email
    private String email;

    @Column(nullable = false, unique = true)
    @NotEmpty
    private String username;

    @Column(nullable = false, columnDefinition = "TINYINT", length = 1)
    private boolean active;

    public User() {
        // Empty
    }

    public User(String firstName, String lastName, String email, String username, boolean active) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.active = active;
    }
}
