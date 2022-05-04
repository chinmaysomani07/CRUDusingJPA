package com.example.demo.services;

import com.example.demo.repository.StudentRepository;
import com.example.demo.entities.Student;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServices {
    private final StudentRepository studentRepository;

    @Autowired
    StudentServices(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        return studentRepository.findAllByOrderByNameAsc();
    }

    public void addAllStudents(List<Student> studentList) {
        studentRepository.saveAll(studentList);
    }

    public ResponseEntity<?> addNewStudent(Student student) throws Exception {
        Optional<Student> studentByEmailId = studentRepository.findStudentByEmailid(student.getEmailid());
        if(studentByEmailId.isPresent()){
            throw new Exception("Email id already taken.");
        }
        studentRepository.save(student);
        return ResponseEntity.ok("Student added successfully.");
    }

    public Optional<Student> getStudentById(long id) throws Exception {
        Optional<Student> student = studentRepository.findById(id);
        if(!student.isPresent()){
            throw new Exception("Student with id:" +id+ " not found");
        }
        return Optional.of(student.get());
    }

    public void deleteStudent(long id) throws Exception {
        boolean studentExistsById = studentRepository.existsById(id);
        if(!studentExistsById){
            throw new Exception ("Student with given id does not exist.");
        }else {
            studentRepository.deleteById(id);
        }
    }

    public void updateStudentDetails(long id, Student student) throws Exception {
        Optional<Student> studentById = Optional.of(studentRepository.getById(id));
        if(!studentById.isPresent()){
            throw new Exception("Student with id: " +id+ " does not exist.");
        }
        studentRepository.save(student);
    }

    public Optional<Student> getStudentInfoFromEmail(String email) throws Exception {
        Optional<Student> studentFromEmail = studentRepository.findStudentByEmailid(email);
        System.out.println(studentFromEmail);
        if(studentFromEmail.isEmpty()){
            throw new Exception ("Student with this email id does not exist.");
        }
        return studentFromEmail;
    }


    public Student getStudentByName(String name) throws Exception {
        Student studentFromName = studentRepository.findStudentByName(name);
        System.out.println(studentFromName);
        if(studentFromName.getName().isEmpty()){
            throw new Exception ("Student with this email id does not exist.");
        }
        return studentFromName;
    }

    public List<String> getAllEmailId() {
        List<String> allMailIdList = studentRepository.getAllEmailId();
        System.out.println(allMailIdList);
        return allMailIdList;
    }
}
