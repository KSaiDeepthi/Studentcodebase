package com.example.live.util;

import com.example.live.exception.CustomException;
import com.example.live.model.Marks;
import com.example.live.model.StudentProfile;
import com.example.live.repository.MarksRepository;
import com.example.live.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

public class UtilityServiceForStudentTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private MarksRepository marksRepository;

    @InjectMocks
    private UtilityServiceForStudent utilityServiceForStudent;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSaveStudentProfile() {
        StudentProfile studentProfile = new StudentProfile();

        utilityServiceForStudent.saveStudentProfile(studentProfile);

        verify(studentRepository).save(studentProfile);
    }

    @Test
    public void testDeleteStudentProfile() {
        StudentProfile studentProfile = new StudentProfile();

        utilityServiceForStudent.deleteStudentProfile(studentProfile);

        verify(studentRepository).delete(studentProfile);
    }

    @Test
    public void testSaveMarks() {
        Marks marks = new Marks();

        utilityServiceForStudent.saveMarks(marks);

        verify(marksRepository).save(marks);
    }

    @Test
    public void testDeleteMarks() {
        List<Marks> marksList = Collections.singletonList(new Marks());

        utilityServiceForStudent.deleteMarks(marksList);

        verify(marksRepository).deleteAll(marksList);
    }

    @Test
    public void testSaveStudentProfile_Exception() {
        StudentProfile studentProfile = new StudentProfile();

        doThrow(RuntimeException.class).when(studentRepository).save(studentProfile);

        assertThrows(CustomException.class, () -> utilityServiceForStudent.saveStudentProfile(studentProfile));
    }

    @Test
    public void testDeleteStudentProfile_Exception() {
        StudentProfile studentProfile = new StudentProfile();

        doThrow(RuntimeException.class).when(studentRepository).delete(studentProfile);

        assertThrows(CustomException.class, () -> utilityServiceForStudent.deleteStudentProfile(studentProfile));
    }

    @Test
    public void testSaveMarks_Exception() {
        Marks marks = new Marks();

        doThrow(RuntimeException.class).when(marksRepository).save(marks);

        assertThrows(CustomException.class, () -> utilityServiceForStudent.saveMarks(marks));
    }

    @Test
    public void testDeleteMarks_Exception() {
        List<Marks> marksList = Collections.singletonList(new Marks());

        doThrow(RuntimeException.class).when(marksRepository).deleteAll(marksList);

        assertThrows(CustomException.class, () -> utilityServiceForStudent.deleteMarks(marksList));
    }
}

