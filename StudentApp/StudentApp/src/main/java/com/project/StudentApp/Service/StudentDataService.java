package com.project.StudentApp.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.project.StudentApp.Entity.StudentData;
import com.project.StudentApp.Repo.StudentRepo;

public class StudentDataService {

	@Autowired
	private StudentRepo studentRepo;
	
	public List<StudentData> getAll()
	{
		List<StudentData> l1=new ArrayList<StudentData>();
		l1.addAll(studentRepo.findAll());
		return l1;
	}

	public boolean add(StudentData student)
	{
		studentRepo.save(student);
		return true;
	}
	
	public boolean update(int id, StudentData student)
	{
		StudentData students=  studentRepo.findById(id).get();
		if(students!=null)
		{
			students.setAge(student.getAge());
			students.setBranch(student.getBranch());
			students.setCollege(student.getCollege());
			students.setHobby(student.getHobby());
			studentRepo.save(students);
			return true;
			
		}
		else 
		{
			return false;
		}
	}
	public boolean delete(int id)
	{
		studentRepo.deleteById(id);
		return true;
	}
}
