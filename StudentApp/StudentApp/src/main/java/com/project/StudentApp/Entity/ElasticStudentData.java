package com.project.StudentApp.Entity;



import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import jakarta.persistence.Id;
import lombok.Data;

@Document(indexName="studentdata")
@Data
public class ElasticStudentData {
	@Id
	private int id;
	@Field(type = FieldType.Integer, name = "age")
	private int age;
	@Field(type = FieldType.Text, name = "name")
	private String name;
	@Field(type = FieldType.Text, name = "college")
	private String college;
	@Field(type = FieldType.Text, name = "branch")
	private String branch;
	@Field(type = FieldType.Text, name = "hobby")
	private String hobby;

}
