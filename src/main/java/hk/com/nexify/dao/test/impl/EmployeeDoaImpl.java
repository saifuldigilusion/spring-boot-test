package hk.com.nexify.dao.test.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.googlecode.genericdao.search.ExampleOptions;
import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.ISearch;
import com.googlecode.genericdao.search.SearchResult;

import hk.com.nexify.dao.cmn.impl.GenericBaseDaoImpl;
import hk.com.nexify.dao.test.EmployeeDao;
import hk.com.nexify.entity.cmn.pojo.NafFilter;
import hk.com.nexify.entity.cmn.pojo.NafPageList;
import hk.com.nexify.entity.cmn.pojo.NafPaging;
import hk.com.nexify.entity.test.Employee;

@Repository
public class EmployeeDoaImpl extends GenericBaseDaoImpl<Employee, Long> implements EmployeeDao {
	
	@Override
	public List<Employee> getAllEmployees() {
		Session session = this.getSession();
		List<Employee> list = session.createQuery("from Employee").list();
		return list;
	}

	@Override
	public Employee getEmployee(Long id) {
		Session session = this.getSession();
		return session.get(Employee.class, id);
	}

	@Override
	public Employee addEmployee(Employee employee) {
		Session session = this.getSession();
		session.save(employee);
		
		return employee;
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		Session session = this.getSession();
		//session.update(employee);
		session.saveOrUpdate(employee);
		
		return employee;
		
	}

	@Override
	public void deleteEmployee(Employee employee) {
		Session session = this.getSession();
		session.delete(employee);
		
	}

}
