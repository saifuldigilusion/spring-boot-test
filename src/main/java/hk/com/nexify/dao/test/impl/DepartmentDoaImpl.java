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
import hk.com.nexify.dao.test.DepartmentDao;
import hk.com.nexify.dao.test.DepartmentDao;
import hk.com.nexify.entity.cmn.pojo.NafFilter;
import hk.com.nexify.entity.cmn.pojo.NafPageList;
import hk.com.nexify.entity.cmn.pojo.NafPaging;
import hk.com.nexify.entity.test.Department;

@Repository
public class DepartmentDoaImpl extends GenericBaseDaoImpl<Department, Long> implements DepartmentDao {
	
	@Override
	public List<Department> getAllDepartments() {
		Session session = this.getSession();
		List<Department> list = session.createQuery("from Department").list();
		return list;
	}

	@Override
	public Department getDepartment(Long id) {
		Session session = this.getSession();
		return session.get(Department.class, id);
	}

	@Override
	public Department addDepartment(Department department) {
		Session session = this.getSession();
		session.save(department);
		
		return department;
	}

	@Override
	public Department updateDepartment(Department department) {
		Session session = this.getSession();
		//session.update(department);
		session.saveOrUpdate(department);
		
		return department;
		
	}

	@Override
	public void deleteDepartment(Department department) {
		Session session = this.getSession();
		session.delete(department);
		
	}

}
