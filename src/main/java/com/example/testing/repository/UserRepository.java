package com.example.testing.repository;

import com.example.testing.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "select * from users u where u.firstName like %:key%", nativeQuery = true)
    List<User> searchUser(@Param("key") String key);
}
