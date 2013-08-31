package com.abel.nfcServer.logic;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.abel.nfcServer.model.Employee;

public class TimeSheet {
	
	EmployeeLookUp empLookUp;
	double empSalary;
	
	public TimeSheet(){
		empLookUp = new EmployeeLookUp();
	}
	
	
   public Employee getEmployee (String userName){
		
		empLookUp = new EmployeeLookUp();
		Employee foundEmp = empLookUp.findEmployee(userName);		
		if (foundEmp!=null){
		  empSalary = CaluculateSalary(foundEmp);
		  foundEmp.setSalary(empSalary);
		}
		return foundEmp;
	}
	
	
	public double CaluculateSalary (Employee emp){		
		Date entranceTime = emp.getEntranceTime();	
		Date exitTime = emp.getExitTime();
		double payRate = emp.getHourlyRate();
		double duration = getDateDiff(entranceTime,exitTime);
		double salary = payRate * duration;
		return salary;		
	}
	
	public double getDateDiff(Date entrance , Date exit) {
		double minDuration=(exit.getTime()- entrance.getTime())/(60* 60 * 1000);
	    System.out.println("exittime in milies "+ exit.getTime());
	    System.out.println("entrance in milies "+ entrance.getTime());
	    System.out.println("Duration value "+ minDuration);
	    
	    return minDuration;
	   
	}
	

}
