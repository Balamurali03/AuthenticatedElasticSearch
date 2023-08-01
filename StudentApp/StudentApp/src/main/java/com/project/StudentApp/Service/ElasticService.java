package com.project.StudentApp.Service;

import java.io.IOException;
import java.util.List;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.StudentApp.Repo.ElasticRepo;
import com.project.StudentApp.ElasticUtil.ElasticSearchUtil;
import com.project.StudentApp.Entity.ElasticStudentData;
import com.project.StudentApp.Entity.StudentData;

import com.project.StudentApp.Repo.StudentRepo;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.SearchResponse;

@Service
public class ElasticService {
@Autowired
private StudentRepo studentRepo;
@Autowired
private ElasticRepo elasticRepo;
@Autowired
private ElasticsearchClient elasticSearchClient;


public String loadAllStudentData(){
	
	List<StudentData> studentData=studentRepo.findAll();
	if(studentData !=null) {		
		ElasticStudentData elasticStudentData =new ElasticStudentData() ;
		for(StudentData data : studentData) {
			elasticStudentData.setAge(data.getAge());
			elasticStudentData.setId(data.getId());
			elasticStudentData.setBranch(data.getBranch());
			elasticStudentData.setCollege(data.getCollege());
			elasticStudentData.setHobby(data.getHobby());
			elasticStudentData.setName(data.getName());
			
			elasticRepo.save(elasticStudentData);
			
		}
		return "Data Loaded Sucessfull";
	}
	else {
		return "Data Not Loaded Sucessfull";
	}
}

public String deleteAllData() {
	
	long noOfEntities=elasticRepo.count();
	if(noOfEntities>0) {
		elasticRepo.deleteAll();
		return "Data Deleted Sucessfully";
	}
	else {
		return "There is no data. First Load the data to delete it !!!";
	}
}

public SearchResponse<ElasticStudentData> searchByValue(String aproxValue) throws IOException {
	
	Supplier<Query> supplier= ElasticSearchUtil.createSupplierQuery(aproxValue);
	SearchResponse<ElasticStudentData> searchResponse= elasticSearchClient.search(s-> s.index("studentData").query(supplier.get()), ElasticStudentData.class);
	
	return searchResponse;
}
}
