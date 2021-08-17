package hk.com.nexify.dao.test;

import java.util.List;

import hk.com.nexify.dao.cmn.GenericBaseDao;
import hk.com.nexify.entity.test.Book;

public interface BookDao {
	public List<Book> getAllBooks();
	public Book getBook(Long id);
	public Book addBook(Book book);
	public Book updateBook(Book book);
	public void deleteBook(Book book);
}
