package com.igmi.spring.dal;

import com.igmi.spring.model.Workflow;

public interface IWorkflowApi {
	void saveWorkflow(Workflow obj);
	Workflow getWorkflowById(String id);
}
