package com.igmi.spring.kafka.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.igmi.spring.dal.IWorkflowApi;
import com.igmi.spring.model.Workflow;


@RestController
@RequestMapping(value="/workflow")
public class WorkflowPublishers {

	@Autowired
	private KafkaTemplate<String, Object> template;

	@Autowired
	private IWorkflowApi workflowApi;

	private final static String TOPICNAME = "Test";

	
	@PostMapping("/getData")
	public String getData(@RequestBody JsonNode obj) {
		if (obj != null) {
			try {
				String idValue = getId(obj);
				if (idValue != null && !idValue.isEmpty()) {

					Workflow workflow = workflowApi.getWorkflowById(idValue);
					if (workflow == null) {
						template.send(TOPICNAME, idValue);
						return "Data Published";
					} else {
						return "Data in DB ->" + idValue;
					}

				} else {
					return "No ID";
				}
	
			} catch (Exception e) {
				return e.getMessage();
			}
		} else {
			return "Null Data";
		}
	}

private String getId(JsonNode jsonString) {
		JsonNode nameValue = jsonString.get("name");
		return nameValue.asText();
	}

	@PostMapping("/create")
	public String createRecord(@RequestBody Workflow obj) {

		if (obj != null) {
			try {
				workflowApi.saveWorkflow(obj);
				return "Record Saved";
			} catch (Exception e) {
				// TODO: handle exception
				return e.getMessage();
			}

		} else {
			return "No Body";
		}

	}

}
