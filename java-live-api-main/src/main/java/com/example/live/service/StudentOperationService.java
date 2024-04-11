package com.example.live.service;

import com.example.live.exception.CustomException;
import com.example.live.model.StudentProfile;

import java.util.List;
import java.util.Map;

public interface StudentOperationService {

    Map<String,Object> registerNewStudent(StudentProfile studentProfile) throws CustomException;
    Map<String,Object> updateStudentProfile(StudentProfile studentProfile) throws CustomException;
    Map<String,Object> deleteStudentProfile(String studentId) throws CustomException;
    Map<String,Object> addScore(Map<String,Object> payload) throws CustomException;
    List<Map<Object,Object>> getAllStudents() throws CustomException;
    Map<String,Object> SearchStudent(String studentNumber, String firstName, String lastName, String emailAddress) throws CustomException;

}
