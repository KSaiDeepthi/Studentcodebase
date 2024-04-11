package com.example.live.Service;

import com.example.live.exception.CustomException;
import com.example.live.model.Marks;
import com.example.live.model.StudentProfile;
import com.example.live.repository.MarksRepository;
import com.example.live.repository.StudentRepository;
import com.example.live.service.StudentOperationServiceImpl;
import com.example.live.util.UtilityServiceForStudent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class StudentOperationServiceImplTest {

    @Mock
    private UtilityServiceForStudent utilityServiceForStudent;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private MarksRepository marksRepository;

    @InjectMocks
    private StudentOperationServiceImpl studentOperationService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRegisterNewStudent() throws CustomException {
        StudentProfile studentProfile = new StudentProfile();
        studentProfile.setFirstName("John");
        studentProfile.setLastName("Doe");
        studentProfile.setEmail("john.doe@example.com");
        studentProfile.setCellNo("1234567890");
        studentProfile.setDob(new Date());

        when(studentRepository.findByEmail(studentProfile.getEmail())).thenReturn(null);

        Map<String, Object> expectedResponse = new HashMap<>();
        expectedResponse.put("studentProfile", studentProfile);
        expectedResponse.put("status", "success");

        Map<String, Object> actualResponse = studentOperationService.registerNewStudent(studentProfile);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void testUpdateStudentProfile() throws CustomException {
        StudentProfile studentProfile = new StudentProfile();
        studentProfile.setStudentId("123");
        studentProfile.setFirstName("John");
        studentProfile.setLastName("Doe");
        studentProfile.setEmail("john.doe@example.com");
        studentProfile.setCellNo("1234567890");
        studentProfile.setDob(new Date());

        when(studentRepository.findByStudentId(studentProfile.getStudentId())).thenReturn(studentProfile);

        Map<String, Object> expectedResponse = new HashMap<>();
        expectedResponse.put("data", studentProfile);
        expectedResponse.put("status", "success");

        Map<String, Object> actualResponse = studentOperationService.updateStudentProfile(studentProfile);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void testDeleteStudentProfile() throws CustomException {
        String studentId = "123";

        StudentProfile studentProfile = new StudentProfile();
        studentProfile.setStudentId(studentId);

        List<Marks> marks = Collections.emptyList();

        when(studentRepository.findByStudentId(studentId)).thenReturn(studentProfile);
        when(marksRepository.findByStudentProfile(studentProfile)).thenReturn(marks);

        Map<String, Object> expectedResponse = new HashMap<>();
        expectedResponse.put("Message", "Deleted Successfully");
        expectedResponse.put("status", "success");

        Map<String, Object> actualResponse = studentOperationService.deleteStudentProfile(studentId);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void testAddScore() throws CustomException {
        Map<String, Object> payload = new HashMap<>();
        payload.put("studentId", "123");
        payload.put("currentScore", "90");

        StudentProfile studentProfile = new StudentProfile();
        studentProfile.setStudentId("123");

        when(studentRepository.findByStudentId(payload.get("studentId").toString())).thenReturn(studentProfile);

        Marks marks = new Marks();
        marks.setStudentProfile(studentProfile);
        marks.setCurrentScore(90);


        Map<String, Object> expectedResponse = new HashMap<>();
        expectedResponse.put("status", "success");
        expectedResponse.put("Average", marks.getAverageScore());

        Map<String, Object> actualResponse = studentOperationService.addScore(payload);

    }
    @Test
    public void testGetAllStudents() throws CustomException {
        List<Map<Object,Object>> students = new ArrayList<>();

        when(studentRepository.getLatestStudentMarks()).thenReturn(students);

        List<Map<Object,Object>> expectedResponse = students;
        List<Map<Object,Object>> actualResponse = studentOperationService.getAllStudents();

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void testSearchStudent() throws CustomException {
        StudentProfile studentProfile = new StudentProfile();
        studentProfile.setStudentId("123");
        studentProfile.setFirstName("John");
        studentProfile.setLastName("Doe");
        studentProfile.setEmail("john.doe@example.com");
        studentProfile.setCellNo("1234567890");
        studentProfile.setDob(new Date());

        Marks marks = new Marks();
        marks.setId(1L);
        marks.setStudentProfile(studentProfile);
        marks.setCurrentScore(90);

        List<Marks> marksList = Collections.singletonList(marks);

        when(studentRepository.findByStudentIdOrFirstNameOrLastNameOrEmail(anyString(), anyString(), anyString(), anyString())).thenReturn(studentProfile);
        when(marksRepository.findByStudentProfileOrderByCreatedDateAsc(studentProfile)).thenReturn(marksList);

        Map<String, Object> expectedResponse = new HashMap<>();
        expectedResponse.put("student", studentProfile);
        expectedResponse.put("marks", marks);
        expectedResponse.put("status", "success");

        Map<String, Object> actualResponse = studentOperationService.SearchStudent("123", "", "", "");

        assertEquals(expectedResponse, actualResponse);
    }

}
