package com.project.StudentApp.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.StudentApp.Entity.StudentData;

public interface StudentRepo extends JpaRepository<StudentData,Integer> {

}
