package com.example.live.service;

import com.example.live.exception.CustomException;
import com.example.live.model.Marks;
import com.example.live.model.StudentProfile;
import com.example.live.repository.MarksRepository;
import com.example.live.repository.StudentRepository;
import com.example.live.util.UtilityServiceForStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentOperationServiceImpl implements StudentOperationService {

    @Autowired
    UtilityServiceForStudent utilityServiceForStudent;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    MarksRepository marksRepository;

    static SecureRandom random = new SecureRandom();
    @Override
    public Map<String,Object> registerNewStudent(StudentProfile studentProfile) throws CustomException {
        System.out.println("Entered-registerNewStudent");
        Map<String,Object> map = new HashMap<>();
        checkMandatoryFields(studentProfile);
        checkEmail(studentProfile.getEmail());
        generateStudentId(studentProfile);
        utilityServiceForStudent.saveStudentProfile(studentProfile);
        map.put("studentProfile",studentProfile);
        map.put("status","success");
        return map;
    }

    @Override
    public Map<String, Object> updateStudentProfile(StudentProfile studentProfile) throws CustomException {
        StudentProfile studentProfile1 = studentRepository.findByStudentId(studentProfile.getStudentId());
        studentProfile1.setFirstName(studentProfile.getFirstName());
        studentProfile1.setLastName(studentProfile.getLastName());
        checkEmail(studentProfile.getEmail());
        studentProfile1.setEmail(studentProfile.getEmail());
        studentProfile1.setDob(studentProfile.getDob());
        studentProfile1.setCellNo(studentProfile.getCellNo());
        utilityServiceForStudent.saveStudentProfile(studentProfile1);
        Map<String,Object> map = new HashMap<>();
        map.put("data",studentProfile1);
        map.put("status","success");
        return map;
    }

    @Override
    public Map<String, Object> deleteStudentProfile(String studentId) throws CustomException {
        StudentProfile studentProfile = studentRepository.findByStudentId(studentId);
        List<Marks> marks = marksRepository.findByStudentProfile(studentProfile);
        utilityServiceForStudent.deleteMarks(marks);
        utilityServiceForStudent.deleteStudentProfile(studentProfile);
        Map<String,Object> map = new HashMap<>();
        map.put("Message","Deleted Successfully");
        map.put("status","success");

        return map;
    }

    @Override
    public Map<String, Object> addScore(Map<String, Object> payload) throws CustomException {
        Marks marks = new Marks();
        StudentProfile studentProfile = studentRepository.findByStudentId(payload.get("studentId").toString());
        System.out.println("Student:"+studentProfile.getEmail());
        float score = Float.parseFloat((String) payload.get("currentScore"));
        System.out.println("Score"+score);
        if (score<0 || score>100) {
            throw new CustomException("Invalid score");
        }
        Map<String,Object> map = new HashMap<>();
        marks.setStudentProfile(studentProfile);
        System.out.println("MArks-Student:"+marks.getStudentProfile().getEmail());
        marks.setCurrentScore(score);
        utilityServiceForStudent.saveMarks(marks);
        calculateAvg(studentProfile, marks);
        map.put("status","success");
        map.put("Average",marks.getAverageScore());
        return map;
    }

    @Override
    public List<Map<Object, Object>> getAllStudents() throws CustomException {
        List<Map<Object,Object>> students = studentRepository.getLatestStudentMarks();
        return students;
    }

    @Override
    public Map<String, Object> SearchStudent(String studentNumber, String firstName, String lastName, String emailAddress) throws CustomException {
        StudentProfile studentProfile = studentRepository.findByStudentIdOrFirstNameOrLastNameOrEmail(studentNumber, firstName, lastName, emailAddress);
        Marks marks = marksRepository.findByStudentProfileOrderByCreatedDateAsc(studentProfile).get(0);
        Map<String,Object> map = new HashMap<>();
        map.put("student",studentProfile);
        map.put("marks",marks);
        map.put("status","success");
        return map;
    }

    private void checkMandatoryFields(StudentProfile studentProfile) {
        if (studentProfile.getFirstName() == null || studentProfile.getFirstName() == "") {
            System.out.println("FN: " + studentProfile.getFirstName());
            throw new CustomException("Please enter first name");
        }
        if (studentProfile.getLastName() == null || studentProfile.getLastName() == "") {
            System.out.println("LN: " + studentProfile.getLastName());
            throw new CustomException("Please enter last name");
        }
        if (studentProfile.getEmail() == null || studentProfile.getEmail() == "") {
            System.out.println("Email: " + studentProfile.getEmail());
            throw new CustomException("Please enter email");
        }
        if (studentProfile.getCellNo() == null || studentProfile.getCellNo() == "") {
            System.out.println("CellNo: " + studentProfile.getCellNo());
            throw new CustomException("Please enter cell no");
        }
        if (studentProfile.getDob() == null) {
            System.out.println("DOB: " + studentProfile.getDob());
            throw new CustomException("Please enter dob");
        }
    }
    private void generateStudentId(StudentProfile studentProfile) {
        String sId = String.valueOf(studentProfile.getFirstName().charAt(0));
        int s = (99999999 - 1111111) + 1;
        int number = random.nextInt(s) + 111111;
        sId = sId + String.valueOf(number) + String.valueOf(studentProfile.getLastName().charAt(0));
        studentProfile.setStudentId(sId);
    }
    private void checkEmail(String emailId) {
        if (studentRepository.findByEmail(emailId)!=null) {
            throw new CustomException("Email already exists");
        }
    }

    private void calculateAvg(StudentProfile studentProfile, Marks marks1) {
        List<Marks> marksList = marksRepository.findByStudentProfile(studentProfile);
        int total = marksList.size();
        float count = 0;
        for(Marks marks2 : marksList) {
            count += marks2.getCurrentScore();
        }
        System.out.println("Count:"+count);
        float avg = count/total;
        System.out.println("Average:"+avg);
        marks1.setAverageScore(avg);
        marks1.setCreatedDate(new Date());
        utilityServiceForStudent.saveMarks(marks1);
    }

}