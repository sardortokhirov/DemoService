package com.example.demoservice.repository;

import com.example.demoservice.model.cassandra.DemoClass;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Date-2/29/2024
 * By Sardor Tokhirov
 * Time-10:29 AM (GMT+5)
 */
@Repository
public interface DemoClassRepository extends CassandraRepository<DemoClass, UUID> {
    @Query("SELECT * FROM demo_class WHERE student_id = ?0  ALLOW FILTERING")
    List<DemoClass> findByStudentId(UUID uuid);
    @Query("SELECT * FROM demo_class WHERE post_id = ?0  ALLOW FILTERING")
    List<DemoClass> findByPostId(UUID uuid);
    @Query("SELECT * FROM demo_class WHERE teacher_id = ?0  ALLOW FILTERING")
    List<DemoClass> findByTeacherId(UUID uuid);

    @Query("SELECT * FROM demo_class WHERE post_id = ?0 AND student_id = ?1 ALLOW FILTERING")
    Optional<DemoClass> findByPostIdAndStudentId(UUID postId, UUID studentId);

    @Query("SELECT * FROM demo_class WHERE student_id = ?0   LIMIT 1  ALLOW FILTERING")
    Optional<DemoClass> existsByStudentId(UUID studentId);
}
