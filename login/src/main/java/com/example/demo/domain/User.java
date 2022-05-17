/**
 * 
 */
package com.example.demo.domain;


import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;
import javax.validation.constraints.Email;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;


import lombok.Data;

/**
 * @author Harini
 *
 */


@Entity
@Data
@Table(name = "user", schema = "new_db")
public class User {
    private int userId;
    private String userName;
    private String userPass;
    private String userEmail;
   
    private byte userStatus;





    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    //@Pattern(regexp = "^[\\p{L} .'-]+$")
    @Column(name = "user_name", nullable = false, length = 50)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    //@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,19}$",message = "Password between 8 and 20 characters; must contain at least one lowercase letter, one uppercase letter, one numeric digit, and one special character, but cannot contain whitespace.")
    @Column(name = "user_pass", nullable = false, length = 100)
    @JsonIgnore
    public String getUserPass() {
        return userPass;
    }

    @JsonProperty
    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    @Basic
    @Email(message = "Enter valid email address.")
    @Column(name = "user_email", nullable = false, length = 30)
    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

   

    @Basic
    @Column(name = "user_status", nullable = false)
    public byte getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(byte userStatus) {
        this.userStatus = userStatus;
    }

    

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User that = (User) o;
        return userId == that.userId &&
                userStatus == that.userStatus &&
            
                Objects.equals(userName, that.userName) &&
                Objects.equals(userPass, that.userPass) &&
                Objects.equals(userEmail, that.userEmail) 
               ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userName, userPass, userEmail,  userStatus);
    }

   


   
}