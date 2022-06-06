package me.spring.log1.develop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LoggerTest3 {

	private static final Logger logger
		= LoggerFactory.getLogger(LoggerTest3.class);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		logger.error("error");
		logger.warn ("warn");
		logger.info ("info");
		logger.debug("debug");
		logger.trace("trace");
	}

}
