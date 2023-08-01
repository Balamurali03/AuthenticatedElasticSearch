package com.project.StudentApp.ElasticUtil;

import java.util.function.Supplier;

import co.elastic.clients.elasticsearch._types.query_dsl.FuzzyQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import lombok.val;

public class ElasticSearchUtil {
	
	public static FuzzyQuery createFuzzyQuery(String AproxValue) {
		val fuzzyQuery =new FuzzyQuery.Builder();
		
		return fuzzyQuery.field("name").value(AproxValue).build();
	}

	public static Supplier<Query> createSupplierQuery(String AproxValue) {
		Supplier<Query> supplier=() -> Query.of(q ->
		q.fuzzy(createFuzzyQuery(AproxValue)));
		
		return supplier;
	}
}
