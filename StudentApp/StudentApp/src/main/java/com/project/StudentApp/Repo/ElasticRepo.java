package com.project.StudentApp.Repo;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.project.StudentApp.Entity.ElasticStudentData;

public interface ElasticRepo extends ElasticsearchRepository<ElasticStudentData,Integer>{

	

}
