package hk.com.nexify.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hk.com.nexify.dao.test.BookDao;
import hk.com.nexify.entity.test.Book;
import hk.com.nexify.service.BookService;

@Service("bookService")
public class BookServiceImpl implements BookService {

	@Autowired
	BookDao bookDao;
	
	@Transactional
	@Override
	public List<Book> listBooks() {
		return bookDao.getAllBooks();
	}

	@Transactional
	@Override
	public Book get(Long id) {
		return bookDao.getBook(id);
	}

	@Transactional
	@Override
	public Book add(Book book) {
		return bookDao.addBook(book);
	}

	@Transactional
	@Override
	public Book update(Book book) {
		
		bookDao.updateBook(book);
		return book;
	}

	@Transactional
	@Override
	public void delete(Long id) {
		Book curBook = bookDao.getBook(id);
		if(curBook != null) {
			bookDao.deleteBook(curBook);
		}
		
	}

}
