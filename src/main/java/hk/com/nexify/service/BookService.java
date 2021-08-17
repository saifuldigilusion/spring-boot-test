package hk.com.nexify.service;

import java.util.List;

import hk.com.nexify.entity.test.Book;

public interface BookService {
	public List<Book> listBooks();
	public Book get(Long id);
	public Book add(Book book);
	public Book update(Book book);
	public void delete(Long id);
}
