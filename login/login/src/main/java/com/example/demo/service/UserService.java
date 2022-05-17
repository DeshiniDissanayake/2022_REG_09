/**
 * 
 */
package com.example.demo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.request.UserSearchModel;


@Service

public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public User userSearchByEmail(String email) {   // Search user
        return userRepository.findByUserEmail(email);
    }

@Transactional

    public User createUser(User user) {  // Create user
        User tempUser;
        tempUser = user;
        tempUser.setUserPass(passwordEncoder.encode(user.getUserPass()));
        return userRepository.save(tempUser);
    }
    
  
    
   
    
    public ResponseEntity<User> userLogin(UserSearchModel userSearchModel) {  // Check user is exist or not
        if (isUserExist(userSearchModel.getUserEmail())) {
            User user = userSearchByEmail(userSearchModel.getUserEmail());
            if (passwordEncoder.matches(userSearchModel.getUserPass(), user.getUserPass())) {
                return new ResponseEntity<>(user, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    public boolean isUserExist(String email) {
        return userRepository.existsByUserEmail(email);
    }

    public List<User> allUsersSearchByName(String name) { 
        return userRepository.findTop10ByUserNameIgnoreCaseContaining(name);
    }
    
   
        
      
    }


    