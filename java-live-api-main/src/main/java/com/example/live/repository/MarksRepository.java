package com.example.live.repository;

import com.example.live.model.Marks;
import com.example.live.model.StudentProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MarksRepository extends JpaRepository<Marks, Long> {

    public List<Marks> findByStudentProfile(StudentProfile studentProfile);
}
