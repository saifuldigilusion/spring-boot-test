package hk.com.nexify.dao.test;

import java.util.List;

import hk.com.nexify.dao.cmn.GenericBaseDao;
import hk.com.nexify.entity.test.Book;
import hk.com.nexify.entity.test.Department;

public interface DepartmentDao {
	public List<Department> getAllDepartments();
	public Department getDepartment(Long id);
	public Department addDepartment(Department book);
	public Department updateDepartment(Department book);
	public void deleteDepartment(Department book);
}
