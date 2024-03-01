package com.example.demoservice.model.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Date-3/1/2024
 * By Sardor Tokhirov
 * Time-5:29 AM (GMT+5)
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DemoClassStudentPayload {
    private String firstName;
    private String lastName;
    private String userName;
    private String countryName;
}
