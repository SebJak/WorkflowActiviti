package org.workflow.config;

import org.activiti.spring.SpringProcessEngineConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("xmlContext")
public class XmlContext {

	@Autowired
	private SpringProcessEngineConfiguration processEngine;

	public SpringProcessEngineConfiguration getProcessEngine() {
		return processEngine;
	}

	public void setProcessEngine(SpringProcessEngineConfiguration processEngine) {
		this.processEngine = processEngine;
	}

	public XmlContext(SpringProcessEngineConfiguration processEngine) {
		super();
		this.processEngine = processEngine;
	}

	public XmlContext() {
		super();
		processEngine = new SpringProcessEngineConfiguration();
	}
	
	
}
