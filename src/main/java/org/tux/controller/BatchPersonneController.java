//package org.tux.controller;
//
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
//import org.apache.log4j.Logger;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.JobParameter;
//import org.springframework.batch.core.JobParameters;
//import org.springframework.batch.core.JobParametersInvalidException;
//import org.springframework.batch.core.launch.support.SimpleJobLauncher;
//import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
//import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
//import org.springframework.batch.core.repository.JobRestartException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//
//@RestController
//@RequestMapping(value="/personnes")
//public class BatchPersonneController {
//	
//	/**
//	 * logger
//	 */
//	private Logger logger = Logger.getLogger(BatchPersonneController.class);
//	
//	@Autowired
//	private Job importUserJob;
//	
//	@Autowired
//	private SimpleJobLauncher jobLauncher;
//	
//	
//	
//	@RequestMapping(value = "/job1", method = RequestMethod.GET)
//	public void  executionJob() {
//		
//		logger.info("lunch the importUserJob");
//		
//		Map<String,JobParameter> parameters = new HashMap<String,JobParameter>();
//		parameters.put("date", new JobParameter(new Date()));
//		
//		try {
//			jobLauncher.run(importUserJob, new JobParameters(parameters));
//		} catch (JobExecutionAlreadyRunningException | JobRestartException
//				| JobInstanceAlreadyCompleteException
//				| JobParametersInvalidException e) {
//			e.printStackTrace();
//			logger.error("finish the importUserJob " + e);
//		}
//	}
//
//}
