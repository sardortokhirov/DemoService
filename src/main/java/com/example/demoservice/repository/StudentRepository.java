package com.example.demoservice.repository;


import com.example.demoservice.model.Student;
import com.example.demoservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Date-21/01/2024
 * By Sardor Tokhirov
 */

public interface StudentRepository extends JpaRepository<Student, UUID> {

    @Query("SELECT s FROM Student s WHERE s.user.userName = :username")
   Optional<Student>  findStudentByUsername(@Param("username") String username);

    @Query("SELECT s FROM Student s WHERE s.studentId IN :studentIds")
    List<Student> findAllByIdIn(List<UUID> studentIds);

}
