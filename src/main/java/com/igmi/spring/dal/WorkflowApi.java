package com.igmi.spring.dal;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.igmi.spring.model.Workflow;

@Repository
public class WorkflowApi implements IWorkflowApi {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public void saveWorkflow(Workflow obj) {
		// TODO Auto-generated method stub
		mongoTemplate.save(obj);

	}

	@Override
	public Workflow getWorkflowById(String id) {
		// TODO Auto-generated method stub
		Query q = new Query();
		q.addCriteria(Criteria.where("_id").is(new ObjectId(id)));

		return mongoTemplate.findOne(q, Workflow.class);
	}

}
