package com.project.StudentApp.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.StudentApp.Entity.ElasticStudentData;
import com.project.StudentApp.Service.ElasticService;

import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;

@RestController
public class ElasticController {

	@Autowired
	private ElasticService elasticService;

	@RequestMapping("/load")
	public String loadedData() {
		return elasticService.loadAllStudentData();
	}

	@DeleteMapping("/delete-loaded-Data")
	public String DeleteLoadedData() {
		return elasticService.deleteAllData();
	}

	@GetMapping("/get-loaded-data/{aproxValue}")
	public String getLoadedData(@PathVariable String aproxValue) throws IOException {

		SearchResponse<ElasticStudentData> searchResponse = elasticService.searchByValue(aproxValue);

		List<Hit<ElasticStudentData>> listOfHits = searchResponse.hits().hits();

		List<ElasticStudentData> listOfStudentData = new ArrayList<ElasticStudentData>();
		
		if(listOfHits !=null) {
			for (Hit<ElasticStudentData> hit : listOfHits) {
				listOfStudentData.add(hit.source());
			}
			
			return listOfStudentData.toString();
		}  else {
			
			return "Data is Not Loaded";
		}
		 
		
		 
		
	}

}
