package com.example.accessingdatamysql;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete
@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Integer> {
 public User findByEmail(String email);
 public User findByPhone(String phone);
 public User findByUname(String uname);
 public List<User> findByAdminTrue();
 public List<User> findByAdminFalse();
 
}
