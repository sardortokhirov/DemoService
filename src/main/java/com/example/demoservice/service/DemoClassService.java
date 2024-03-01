package com.example.demoservice.service;

import com.example.demoservice.model.DemoClassDto;
import com.example.demoservice.model.Student;
import com.example.demoservice.model.Teacher;
import com.example.demoservice.model.cassandra.DemoClass;
import com.example.demoservice.model.payload.DemoClassPayload;
import com.example.demoservice.repository.DemoClassRepository;
import com.example.demoservice.repository.StudentRepository;
import com.example.demoservice.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


/**
 * Date-2/29/2024
 * By Sardor Tokhirov
 * Time-11:43 AM (GMT+5)
 */
@Service
@RequiredArgsConstructor
public class DemoClassService {
    private final DemoClassRepository demoClassRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    private final ClassPostService classPostService;

    public void addStudentToDemoClass(DemoClassDto demoClassDto) {
        Student student = studentRepository.findStudentByUsername(demoClassDto.getStudentUserName()).orElseThrow();
        DemoClass demoClass = new DemoClass(demoClassDto.getTeacherId(), student.getStudentId(), demoClassDto.getPostId());
        demoClassRepository.save(demoClass);
    }

    public List<DemoClassPayload> getByStudentId(String userName) {
        Student student = studentRepository.findStudentByUsername(userName).orElseThrow();
        return classPostService.getDemoPayload(demoClassRepository.findByStudentId(student.getStudentId()));
    }

    public List<DemoClass> getByTeacherId(String userName) {
        Teacher teacher = teacherRepository.findTeacherByUsername(userName).orElseThrow();
        return demoClassRepository.findByTeacherId(teacher.getTeacherId());
    }

    public List<DemoClass> getStudentByPostId(UUID uuid) {
        return demoClassRepository.findByPostId(uuid);
    }

    public boolean isStudentAttending(UUID uuid, String userName) {
        Student student = studentRepository.findStudentByUsername(userName).orElseThrow();
        return demoClassRepository.findByPostIdAndStudentId(uuid, student.getStudentId()).isPresent();
    }

    public boolean isStudentAttendingToAny(String userName) {
        Student student = studentRepository.findStudentByUsername(userName).orElseThrow();
        return demoClassRepository.existsByStudentId(student.getStudentId()).isPresent();
    }
}
