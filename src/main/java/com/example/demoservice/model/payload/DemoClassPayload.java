package com.example.demoservice.model.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.Indexed;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Date-2/29/2024
 * By Sardor Tokhirov
 * Time-3:25 PM (GMT+5)
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DemoClassPayload {
    private UUID postId;
    private UUID teacherId;
    private String title;
    private Date demoTime;
    private String introVideoImgLink;
}
