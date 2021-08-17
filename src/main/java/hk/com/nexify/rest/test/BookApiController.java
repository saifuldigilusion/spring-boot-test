package hk.com.nexify.rest.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import hk.com.nexify.cmn.response.ResponseModel;
import hk.com.nexify.entity.test.Book;
import hk.com.nexify.service.BookService;

@Controller
@RestController
@RequestMapping("/api/book")
public class BookApiController {

	@Autowired
	BookService bookService;
	
	@GetMapping(value = "/list")
	public ResponseModel<List<Book>> list() {
		return new ResponseModel<List<Book>>(bookService.listBooks());
	}
	
	@GetMapping(value = "/detail/{id}")
	public ResponseModel<Book> detail(@PathVariable Long id) {
		return new ResponseModel<Book>(bookService.get(id));
	}
	
	@PostMapping("/add")
	public ResponseModel<Book> add(@RequestBody Book book) {
		return new ResponseModel<Book>(bookService.add(book));
	}
	
	@PutMapping("/update")
	public ResponseModel<Book> update(@RequestBody Book book) {
		return new ResponseModel<Book>(bookService.update(book));
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseModel delete(@PathVariable Long id) {
		bookService.delete(id);
		return new ResponseModel();
	}
}