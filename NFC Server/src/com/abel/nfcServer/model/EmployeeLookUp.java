package com.abel.nfcServer.model;

import java.util.Date;

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
			foundEmp.setUserName((String)empEntity.getProperty("userName"));
			foundEmp.setEntranceTime((Date)empEntity.getProperty("entranceTime"));
			foundEmp.setExitTime((Date)empEntity.getProperty("exitTime"));
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
