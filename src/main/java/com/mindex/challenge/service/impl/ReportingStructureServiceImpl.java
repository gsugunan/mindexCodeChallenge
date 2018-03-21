package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.service.ReportingStructureService;
import com.mindex.challenge.data.ReportingStructure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.LinkedList;
import java.util.List;

@Service
public class ReportingStructureServiceImpl implements ReportingStructureService {
    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public ReportingStructure fetchReportingStructure(String EmployeeId){
	ReportingStructure ret=new ReportingStructure();

	//LOG.debug("before get employee");


	Employee emp=employeeRepository.findByEmployeeId(EmployeeId);

	if (emp == null) {
            throw new RuntimeException("Invalid employeeId: " + EmployeeId);
        }
	else{
	    //LOG.debug("before set employee, id: [{}]", emp.getEmployeeId());
	    ret.setEmployee(employeeRepository.findByEmployeeId(EmployeeId));

	    //LOG.debug("after get employee");

	    //we're going to look at every element, so a linked list isn't bad
	    LinkedList<Employee> inp=new LinkedList<Employee>();
	    inp.add(ret.getEmployee());
	    LOG.debug("generated starting list");

	    //call recursive method
	    ret.setNumberOfReports(getChildren(inp));
	}
	return ret;
    }

    public int getChildren(List<Employee> empList){
	LinkedList<Employee> inp=new LinkedList(empList);
	int children=0;
	while (inp.peek()!=null){
	    Employee emp=inp.pop();
	    //LOG.debug("checking employee with id [{}]", emp.getEmployeeId());
	    if (emp.getDirectReports()!=null){
		if (emp.getDirectReports().size()!=0){
		    children += 1+getChildren(emp.getDirectReports());
		}
		else{
		    children+=1;
		}
	    }
	    else{
		children+=1;
	    }
	}
	return children;
    }
}
