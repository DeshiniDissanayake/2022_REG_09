/**
 * 
 */
package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.User;



/**
 * @author Harini
 *
 */
public interface UserRepository extends JpaRepository<User, Long> {

	  List<User> findTop10ByUserNameIgnoreCaseContaining(String userName);
	User findByUserId(int userId);
  User findByUserEmail(String email);
  boolean existsByUserEmail(String email);
  

}
