package com.example.demoservice.service;

import com.example.demoservice.model.DemoClassDto;
import com.example.demoservice.model.Student;
import com.example.demoservice.model.Teacher;
import com.example.demoservice.model.User;
import com.example.demoservice.model.cassandra.DemoClass;
import com.example.demoservice.model.payload.DemoClassPayload;
import com.example.demoservice.model.payload.DemoClassStudentPayload;
import com.example.demoservice.repository.DemoClassRepository;
import com.example.demoservice.repository.StudentRepository;
import com.example.demoservice.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;


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

    public List<DemoClassPayload> getByTeacherId(String userName) {
        Teacher teacher = teacherRepository.findTeacherByUsername(userName).orElseThrow();
        Set<UUID> uuidSet=new HashSet<>();
        List<DemoClass> byTeacherId = demoClassRepository.findByTeacherId(teacher.getTeacherId()).stream()
                .filter(demoClass -> {
                    if (!uuidSet.contains(demoClass.getPostId())) {
                        uuidSet.add(demoClass.getPostId());
                        return true;
                    }
                    return false;
                })
                .collect(Collectors.toList());

        return classPostService.getDemoPayload(byTeacherId);
    }

    public List<DemoClassStudentPayload> getStudentByPostId(UUID uuid) {
        List<UUID> byPostId = demoClassRepository.findAlLStudentsByPostId(uuid).stream().map(demoClass -> demoClass.getStudentId()).toList();
        List<Student> students=studentRepository.findAllByIdIn(byPostId);
        return students.stream()
                .map(student -> {
                    User user=student.getUser();
                    DemoClassStudentPayload payload = new DemoClassStudentPayload();
                    payload.setFirstName(user.getFirstName());
                    payload.setLastName(user.getLastName());
                    payload.setUserName(user.getUserName());
                    payload.setCountryName(user.getCountry().getCountryName());
                    return payload;
                })
                .collect(Collectors.toList());
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
