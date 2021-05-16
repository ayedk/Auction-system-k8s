package com.fis.upStream.model;

import javax.persistence.*;
import java.io.Serializable;
@Entity(name = "User")
@Table(name = "user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private long id;
    @Column(name = "firstname")
    private String firstname;
    @Column(name = "lastname")
    private String lastname;
    @Column(name = "email")
    private String email;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User(){

    }

    public long getId(){
            return id;
        }

    public void setId(long id) {
        this.id = id;
        }

    public String getUsername() {
        return username;
        }

    public void setUsername(String username) {
        this.username = username;
        }

    public String getPassword() {
        return password;
        }

    public void setPassword(String password) {
        this.password = password;
        }
}