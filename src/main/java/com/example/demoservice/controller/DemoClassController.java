package com.example.demoservice.controller;


import com.example.demoservice.model.DemoClassDto;
import com.example.demoservice.model.Student;
import com.example.demoservice.model.cassandra.DemoClass;
import com.example.demoservice.model.payload.DemoClassPayload;
import com.example.demoservice.model.payload.DemoClassStudentPayload;
import com.example.demoservice.repository.StudentRepository;
import com.example.demoservice.service.DemoClassService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

/**
 * Date-2/29/2024
 * By Sardor Tokhirov
 * Time-10:31 AM (GMT+5)
 */
@RestController
@RequestMapping("/api/v1/demo-class")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class DemoClassController {
    private final DemoClassService demoClassService;

    @PostMapping(value = "/add")
    public void getStudents(@RequestBody DemoClassDto demoClassDto) {
        demoClassService.addStudentToDemoClass(demoClassDto);
    }

    @GetMapping(value = "/classes/{username}")
    public ResponseEntity<List<DemoClassPayload>> getStudentDemoClass(@PathVariable String username) {
        return ResponseEntity.ok(demoClassService.getByStudentId(username));
    }

    @GetMapping(value = "/posts/{username}")
    public ResponseEntity<List<DemoClassPayload>> getTeacherDemoClass(@PathVariable String username) {
        return ResponseEntity.ok(demoClassService.getByTeacherId(username));
    }

    @GetMapping("/students/{uuid}")
    public ResponseEntity<List<DemoClassStudentPayload>> getStudentsByPostId(@PathVariable UUID uuid) {
        return ResponseEntity.ok(demoClassService.getStudentByPostId(uuid));
    }

    @GetMapping("/is-attending/{uuid}/{username}")
    public ResponseEntity<Boolean> isStudentAttending(@PathVariable UUID uuid, @PathVariable String username) {
        return ResponseEntity.ok(demoClassService.isStudentAttending(uuid, username));
    }

    @GetMapping("/is-attending/{username}")
    public ResponseEntity<Boolean> isStudentAttendingToAny(@PathVariable String username) {
        return ResponseEntity.ok(demoClassService.isStudentAttendingToAny(username));
    }
}
