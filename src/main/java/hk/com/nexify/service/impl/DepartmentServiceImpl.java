package hk.com.nexify.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hk.com.nexify.dao.test.DepartmentDao;
import hk.com.nexify.entity.test.Department;
import hk.com.nexify.service.DepartmentService;
import hk.com.nexify.service.DepartmentService;

@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	DepartmentDao departmentDao;
	
	@Transactional
	@Override
	public List<Department> listDepartments() {
		return departmentDao.getAllDepartments();
	}

	@Transactional
	@Override
	public Department get(Long id) {
		return departmentDao.getDepartment(id);
	}

	@Transactional
	@Override
	public Department add(Department department) {
		return departmentDao.addDepartment(department);
	}

	@Transactional
	@Override
	public Department update(Department department) {
		
		departmentDao.updateDepartment(department);
		return department;
	}

	@Transactional
	@Override
	public void delete(Long id) {
		Department curDepartment = departmentDao.getDepartment(id);
		if(curDepartment != null) {
			departmentDao.deleteDepartment(curDepartment);
		}
		
	}

}
