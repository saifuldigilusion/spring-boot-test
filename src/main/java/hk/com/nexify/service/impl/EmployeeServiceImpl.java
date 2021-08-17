package hk.com.nexify.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hk.com.nexify.dao.test.EmployeeDao;
import hk.com.nexify.entity.test.Employee;
import hk.com.nexify.service.EmployeeService;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeDao employeeDao;
	
	@Transactional
	@Override
	public List<Employee> listEmployees() {
		return employeeDao.getAllEmployees();
	}

	@Transactional
	@Override
	public Employee get(Long id) {
		return employeeDao.getEmployee(id);
	}

	@Transactional
	@Override
	public Employee add(Employee employee) {
		return employeeDao.addEmployee(employee);
	}

	@Transactional
	@Override
	public Employee update(Employee employee) {
		
		employeeDao.updateEmployee(employee);
		return employee;
	}

	@Transactional
	@Override
	public void delete(Long id) {
		Employee curEmployee = employeeDao.getEmployee(id);
		if(curEmployee != null) {
			employeeDao.deleteEmployee(curEmployee);
		}
		
	}

}
