package hk.com.nexify.service;

import java.util.List;

import hk.com.nexify.entity.test.Department;

public interface DepartmentService {
	public List<Department> listDepartments();
	public Department get(Long id);
	public Department add(Department department);
	public Department update(Department department);
	public void delete(Long id);
}
