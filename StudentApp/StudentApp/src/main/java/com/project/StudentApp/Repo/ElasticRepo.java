package com.project.StudentApp.Repo;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.project.StudentApp.Entity.ElasticStudentData;

@Repository
public interface ElasticRepo extends ElasticsearchRepository<ElasticStudentData,Integer>{

	

}
