package com.example.demo.controller;

import com.example.demo.services.StudentServices;
import com.example.demo.entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1")
public class StudentController {

    private final StudentServices studentService;

    @Autowired
    public StudentController(StudentServices studentService) {
        this.studentService = studentService;
    }

    @GetMapping(path = "/students")
    public List<Student> getStudents() {
       return studentService.getStudents();
    }

    @PostMapping(path = "/students")
    public void addAllStudents(@RequestBody List<Student> studentList){
        studentService.addAllStudents(studentList);
    }

    @PostMapping(path = "/student")
    public void addSingleStudent(@RequestBody Student student) throws Exception {
        student.setCreated_at(new Timestamp(System.currentTimeMillis()));
        student.setUpdated_at(new Timestamp(System.currentTimeMillis()));
        studentService.addNewStudent(student);
    }

    @GetMapping(path = "/student/{id}")
    public Optional<Student> getStudentById(@PathVariable("id") long id) throws Exception {
        return studentService.getStudentById(id);
    }

    @DeleteMapping(path = "/student/{id}")
    public void deleteStudent(@PathVariable("id") long id) throws Exception {
        studentService.deleteStudent(id);
    }

    @PutMapping(path = "/student/{id}")
    public void updateStudentDetails(@PathVariable("id") long id, @RequestBody Student student) throws Exception {
        studentService.updateStudentDetails(id, student);
    }

    @GetMapping(path = "/student/email/{email}")
    public Optional<Student> getStudentInfoFromEmail(@PathVariable("email") String email) throws Exception {
        return studentService.getStudentInfoFromEmail(email);
    }

    @GetMapping(path = "/student/name/{name}")
    public Student getStudentFromName(@PathVariable("name") String name) throws Exception {
        return studentService.getStudentByName(name);
    }

    @GetMapping(path = "/student/allemailid")
    public List<String> getAllEmailId(){
        return studentService.getAllEmailId();
    }
}
