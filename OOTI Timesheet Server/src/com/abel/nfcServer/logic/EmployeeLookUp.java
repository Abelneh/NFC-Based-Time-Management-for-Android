package com.abel.nfcServer.logic;

import java.util.Date;

import com.abel.nfcServer.model.Employee;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class EmployeeLookUp {
	
	
	public Employee findEmployee(String empKey){
		
		Entity empEntity = findEmpEntity(empKey);
		
		if (empEntity==null){
			return null;
		}
		else{
			Employee foundEmp = new Employee();
			foundEmp.setUserName(empEntity.getProperty("userName").toString());
			foundEmp.setEntranceTime((Date)empEntity.getProperty("entranceTime"));
			foundEmp.setExitTime((Date)empEntity.getProperty("exitTime"));
			foundEmp.setHourlyRate(Double.parseDouble(empEntity.getProperty("hourlyRate").toString()));
			return foundEmp;
		}
	}
	
	
	private Entity findEmpEntity(String empKey){
		
		Key nfcKey = KeyFactory.createKey("OOTIAccess", empKey);
		Entity empEntity = null;
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		try {
			empEntity = datastore.get(nfcKey);
		} catch (EntityNotFoundException e) {
			e.printStackTrace();
		} 

		return empEntity;
			
	}
	
	

}
