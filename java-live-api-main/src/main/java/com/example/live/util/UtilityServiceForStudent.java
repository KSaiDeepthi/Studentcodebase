package com.example.live.util;

import com.example.live.exception.CustomException;
import com.example.live.model.Marks;
import com.example.live.model.StudentProfile;
import com.example.live.repository.MarksRepository;
import com.example.live.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilityServiceForStudent {

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    MarksRepository marksRepository;

    public void saveStudentProfile(StudentProfile studentProfile) {
        try {
            studentRepository.save(studentProfile);
        }
        catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }
    public void deleteStudentProfile(StudentProfile studentProfile) {
        try {
            studentRepository.delete(studentProfile);
        }
        catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }
    public void saveMarks(Marks marks) {
        try {
            marksRepository.save(marks);
        }
        catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }
    public void deleteMarks(List<Marks> marks) {
        try {
            marksRepository.deleteAll(marks);
        }
        catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }
}
