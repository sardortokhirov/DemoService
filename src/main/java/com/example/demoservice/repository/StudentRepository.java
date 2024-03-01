package com.example.settings.repository;

import com.example.settings.model.Student;
import com.example.settings.model.Teacher;
import com.example.settings.model.User;
import com.example.settings.role.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

/**
 * Date-21/01/2024
 * By Sardor Tokhirov
 */

public interface StudentRepository extends JpaRepository<Student, UUID> {

    @Query("SELECT s FROM Student s WHERE s.user.userName = :username")
   Optional<Student>  findStudentByUsername(@Param("username") String username);

    Student findByUser(User user);


}
