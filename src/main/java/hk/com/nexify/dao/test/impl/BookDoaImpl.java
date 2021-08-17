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
import hk.com.nexify.dao.test.BookDao;
import hk.com.nexify.entity.cmn.pojo.NafFilter;
import hk.com.nexify.entity.cmn.pojo.NafPageList;
import hk.com.nexify.entity.cmn.pojo.NafPaging;
import hk.com.nexify.entity.test.Book;

@Repository
public class BookDoaImpl extends GenericBaseDaoImpl<Book, Long> implements BookDao {
	
	@Override
	public List<Book> getAllBooks() {
		Session session = this.getSession();
		List<Book> list = session.createQuery("from Book").list();
		return list;
	}

	@Override
	public Book getBook(Long id) {
		Session session = this.getSession();
		return session.get(Book.class, id);
	}

	@Override
	public Book addBook(Book book) {
		Session session = this.getSession();
		session.save(book);
		
		return book;
	}

	@Override
	public Book updateBook(Book book) {
		Session session = this.getSession();
		//session.update(book);
		session.saveOrUpdate(book);
		
		return book;
		
	}

	@Override
	public void deleteBook(Book book) {
		Session session = this.getSession();
		session.delete(book);
		
	}

}
