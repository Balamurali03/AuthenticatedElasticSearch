package com.project.StudentApp.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.StudentApp.Entity.StudentData;
import com.project.StudentApp.Service.StudentDataService;

@RestController
public class StudentController {
	
	@Autowired
	StudentDataService studentDataService;

	@GetMapping("/get-all-student-data")
	public ResponseEntity<List<StudentData>> getAll()
	{
		List<StudentData> student=studentDataService.getAll();
		return ResponseEntity.status(200).body(student);
	}

	@PostMapping("/save-student-data")
	public ResponseEntity<?> add(@RequestBody StudentData student )
	{
		boolean value=studentDataService.add(student);
		
		if(value)
		{
			return ResponseEntity.status(200).body("Student data added sucessfully");
		}
		return ResponseEntity.status(400).body("Student data not added sucessfully");
		
	}
	@PatchMapping("/update-student-data/{id}")
	public ResponseEntity<?> update(@PathVariable int id ,@RequestBody StudentData student)
	{
		boolean value=studentDataService.update(id, student);
		
		if(value)
		{
			return ResponseEntity.status(200).body("Student data updated sucessfully");
		}
		return ResponseEntity.status(400).body("Student data not updated sucessfully");
		
	}
	
	@DeleteMapping("/delete-student-data/{id}")
	public ResponseEntity<?> delete(@PathVariable int id )
	{
		boolean value=studentDataService.delete(id);
		
		if(value)
		{
			return ResponseEntity.status(200).body("Student data deleted sucessfully");
		}
		return ResponseEntity.status(400).body("Student data not deleted sucessfully");
		
	}
}
