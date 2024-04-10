package com.example.live.repository;

import com.example.live.model.StudentProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface StudentRepository extends JpaRepository<StudentProfile, Long> {

    public StudentProfile findByEmail(String email);
    public StudentProfile findByStudentId(String studentId);
    @Query(value = "SELECT s.id, s.first_name, s.last_name, m.current_score, m.average_score " +
            "FROM student s " +
            "LEFT JOIN (SELECT student_id, MAX(created_date) AS latest_date FROM marks GROUP BY student_id) latest_marks " +
            "ON s.id = latest_marks.student_id " +
            "LEFT JOIN marks m ON latest_marks.student_id = m.student_id AND latest_marks.latest_date = m.created_date",
            nativeQuery = true)
    List<Map<Object,Object>> getLatestStudentMarks();


}
