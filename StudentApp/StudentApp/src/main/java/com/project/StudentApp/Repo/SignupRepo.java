package com.project.StudentApp.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.StudentApp.Entity.SignupData;

public interface SignupRepo extends JpaRepository<SignupData,Integer>{

}
