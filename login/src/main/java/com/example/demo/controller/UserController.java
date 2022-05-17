/**
 * 
 */
package com.example.demo.controller;

import java.util.Collection;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.request.UserSearchModel;
import com.example.demo.request.UserSearchResults;
import com.example.demo.service.UserService;


@CrossOrigin
@RestController
@RequestMapping(path = "/api")
public class UserController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;
    
    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping(path = "/user/{userId}")     //Get user by id
    public User getUser(@PathVariable int userId) {
        return userRepository.findByUserId(userId);
    }

    @PostMapping(path = "/user")        // Create user
    public User createUser(@Valid @RequestBody User user) {
        return userService.createUser(user);
    }

   @PatchMapping(path = "/updateuser/{userId}")   // Update user
    public User updateUser(@PathVariable int userId, @RequestBody User user) {
        User oldUser = userRepository.findByUserId(userId);
        oldUser.setUserPass(passwordEncoder.encode(user.getUserPass()));
      //  oldUser.setAccessLevel(user.getAccessLevel());
      // oldUser.setUserStatus(user.getUserStatus());
        User newUser = userRepository.save(oldUser);
        return newUser;
    }
    

    @GetMapping(path = "/search/user")    // Get users by email
    public UserSearchModel searchUser(@RequestParam(value = "email", required = true) String email) {
        User obj = userService.userSearchByEmail(email);
        UserSearchModel temp = new UserSearchModel();
        temp.setUserEmail(obj.getUserEmail());
        temp.setUserPass(obj.getUserPass());
        return temp;
    }

    @PostMapping(path = "/login")    //Login
    public ResponseEntity<User> login(@RequestBody UserSearchModel userSearchModel) {
        return userService.userLogin(userSearchModel);
    }

    @GetMapping(path = "/user/check")  // Check users by email
    public boolean isUserExist(@RequestParam(value = "userEmail") String userEmail) {
        return userService.isUserExist(userEmail);
    }
    
        
    @GetMapping(path = "/search/userstoreset") // Search all user list
    public Collection<UserSearchResults> searchUsersToReset(@RequestParam(value = "name") String name) {
        Collection<User> userList = userService.allUsersSearchByName(name);
        return userList.stream().map(temp -> {
            UserSearchResults userSearchResults = new UserSearchResults();
            userSearchResults.setUserId(temp.getUserId());
            userSearchResults.setUserName(temp.getUserName());
            userSearchResults.setUserEmail(temp.getUserEmail());
            return userSearchResults;
        }).collect(Collectors.toList());
    }
   
    }

  
		
		
		
