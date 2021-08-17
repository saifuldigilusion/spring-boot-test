package hk.com.nexify.dao.test;

import java.util.List;

import hk.com.nexify.dao.cmn.GenericBaseDao;
import hk.com.nexify.entity.test.Employee;

public interface EmployeeDao {
	public List<Employee> getAllEmployees();
	public Employee getEmployee(Long id);
	public Employee addEmployee(Employee book);
	public Employee updateEmployee(Employee book);
	public void deleteEmployee(Employee book);
}
