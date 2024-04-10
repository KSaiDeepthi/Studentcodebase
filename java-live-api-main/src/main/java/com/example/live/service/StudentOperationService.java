package com.example.live.service;

import com.example.live.model.StudentProfile;

import java.util.List;
import java.util.Map;

public interface StudentOperationService {

    Map<String,Object> registerNewStudent(StudentProfile studentProfile);
    Map<String,Object> updateStudentProfile(StudentProfile studentProfile);
    Map<String,Object> deleteStudentProfile(String studentId);
    Map<String,Object> addScore(Map<String,Object> payload);
    List<Map<Object,Object>> getAllStudents();


}
