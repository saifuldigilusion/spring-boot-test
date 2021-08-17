package hk.com.nexify.service;

import java.util.List;

import hk.com.nexify.entity.test.Employee;

public interface EmployeeService {
	public List<Employee> listEmployees();
	public Employee get(Long id);
	public Employee add(Employee employee);
	public Employee update(Employee employee);
	public void delete(Long id);
}
