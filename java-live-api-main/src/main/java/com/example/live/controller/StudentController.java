package com.example.live.controller;

import com.example.live.model.StudentProfile;
import com.example.live.service.StudentOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    StudentOperationService studentOperationService;

    @PostMapping(value = "/registerNewStudent", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Map<String,Object>> registerNewStudent(@RequestBody StudentProfile studentProfile) {
        Map<String,Object> response = studentOperationService.registerNewStudent(studentProfile);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/updateStudentProfile", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Map<String,Object>> updateStudentProfile(@RequestBody StudentProfile studentProfile) {
        Map<String,Object> response = studentOperationService.updateStudentProfile(studentProfile);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping(value = "/deleteStudentProfile/{id}")
    public ResponseEntity<Map<String,Object>> deleteStudentProfile(@PathVariable String studentId) {
        Map<String,Object> response = studentOperationService.deleteStudentProfile(studentId);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/addScore", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Map<String,Object>> addScore(@RequestBody Map<String, Object> payload) {
        Map<String,Object> response = studentOperationService.addScore(payload);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/getAllStudents", consumes = "application/json", produces = "application/json")
    public ResponseEntity<List<Map<Object,Object>>> getAllStudents() {
        List<Map<Object,Object>> response = studentOperationService.getAllStudents();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
