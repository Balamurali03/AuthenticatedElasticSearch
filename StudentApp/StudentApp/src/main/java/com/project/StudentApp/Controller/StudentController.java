package com.project.StudentApp.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.project.StudentApp.Entity.StudentData;
import com.project.StudentApp.Service.StudentDataService;

@Controller
public class StudentController {
	
	@Autowired
	StudentDataService studentDataService;

	@GetMapping("/get")
	public ResponseEntity<List<StudentData>> getAll()
	{
		List<StudentData> student=studentDataService.getAll();
		return ResponseEntity.status(200).body(student);
	}

	@PostMapping("/add")
	public ResponseEntity<?> add(@RequestBody StudentData student )
	{
		boolean value=studentDataService.add(student);
		
		if(value)
		{
			return ResponseEntity.status(200).body("added");
		}
		return ResponseEntity.status(400).body("not added");
		
	}
	@PatchMapping("/update/{id}")
	public ResponseEntity<?> update(@PathVariable int id ,@RequestBody StudentData student)
	{
		boolean value=studentDataService.update(id, student);
		
		if(value)
		{
			return ResponseEntity.status(200).body("updated");
		}
		return ResponseEntity.status(400).body("not updated");
		
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable int id )
	{
		boolean value=studentDataService.delete(id);
		
		if(value)
		{
			return ResponseEntity.status(200).body("deleted");
		}
		return ResponseEntity.status(400).body("not deleted");
		
	}
}
