package com.example.live.controller;

import com.example.live.exception.CustomException;
import com.example.live.model.StudentProfile;
import com.example.live.service.StudentOperationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class StudentControllerTest {

    @Mock
    private StudentOperationService studentOperationService;

    @InjectMocks
    private StudentController studentController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRegisterNewStudent() throws CustomException {
        StudentProfile studentProfile = new StudentProfile(); // You can initialize with required data
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("message", "Student registered successfully");

        when(studentOperationService.registerNewStudent(any(StudentProfile.class))).thenReturn(responseMap);

        ResponseEntity<Map<String,Object>> responseEntity = studentController.registerNewStudent(studentProfile);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(responseMap, responseEntity.getBody());
    }
    @Test
    public void testUpdateStudentProfile() throws CustomException {
        StudentProfile studentProfile = new StudentProfile(); // You can initialize with required data
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("message", "Student profile updated successfully");

        when(studentOperationService.updateStudentProfile(any(StudentProfile.class))).thenReturn(responseMap);

        ResponseEntity<Map<String,Object>> responseEntity = studentController.updateStudentProfile(studentProfile);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(responseMap, responseEntity.getBody());
    }
    @Test
    public void testDeleteStudentProfile() throws CustomException {
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("message", "Student profile Deleted successfully");

        when(studentOperationService.deleteStudentProfile("s45457656y")).thenReturn(responseMap);

        ResponseEntity<Map<String,Object>> responseEntity = studentController.deleteStudentProfile("s45457656y");

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(responseMap, responseEntity.getBody());
    }

    @Test
    public void testAddScore() throws CustomException {
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("message", "Student Score updated successfully");

        when(studentOperationService.addScore(any(Map.class))).thenReturn(responseMap);

        ResponseEntity<Map<String,Object>> responseEntity = studentController.addScore(responseMap);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(responseMap, responseEntity.getBody());
    }
    @Test
    public void testGetAllStudents() throws CustomException {
        List<Map<Object, Object>> responseMap = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();

        when(studentOperationService.getAllStudents()).thenReturn(responseMap);

        ResponseEntity<List<Map<Object,Object>>> responseEntity = studentController.getAllStudents();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(responseMap, responseEntity.getBody());
    }

    @Test
    public void testSearchStudentProfile() throws CustomException {
        Map<String, Object> responseMap = new HashMap<>();

        when(studentOperationService.SearchStudent("e35479u",null,null,null)).thenReturn(responseMap);

        ResponseEntity<Map<String,Object>> responseEntity = studentController.searchStudents("e35479u", null, null, null);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(responseMap, responseEntity.getBody());
    }

}