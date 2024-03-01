package com.example.demoservice.model.cassandra;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.*;

import java.util.Date;
import java.util.UUID;

/**
 * Date-2/29/2024
 * By Sardor Tokhirov
 * Time-10:13 AM (GMT+5)
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table("demo_class")
public class DemoClass {

    @PrimaryKeyColumn(name = "teacher_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private UUID teacherId;

    @PrimaryKeyColumn(name = "student_id", ordinal = 1, type = PrimaryKeyType.PARTITIONED)
    private UUID studentId;

    @PrimaryKeyColumn(name = "post_id", ordinal = 2, type = PrimaryKeyType.PARTITIONED)
    private UUID postId;

}
