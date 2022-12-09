package org.bookarchive.webapp.controller;

import org.bookarchive.webapp.manager.ListManager;
import org.bookarchive.webapp.model.BookView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/api/favoritebooks")
@Configuration
@ComponentScan("org.bookarchive")
public class RestListController {

	Logger logger = LoggerFactory.getLogger(RestListController.class);

	@Autowired
	private ListManager bookManager;

	
	ModelAndView mv = new ModelAndView("bookList");

	@GetMapping
	public ModelAndView getBookListHome() {
			
		return mv;
	}

	@GetMapping(value = "/getAllBooks")
	public ResponseEntity<?> getAllBooks(){
		return bookManager.findAllBooks();
	}
	
//	@GetMapping(value = "/checkBook", produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<Boolean> checkBook(BookView book){
//		System.out.println("Checking if book exists REST.");
//		System.out.println(book.getTitle() + " 1");
//		ResponseEntity<Boolean> answer = bookManager.doesBookExist(book);
//		return answer;
////		return new ResponseEntity<Boolean>(answer, HttpStatus.OK);
//	}
	
	@PostMapping(value = "/saveBook")
	public ResponseEntity<?> saveBook(@RequestBody BookView book){
//        if (bookManager.doesBookExist(book) != null) {
//            logger.debug("A book with title " + book.getTitle() + " authored by " + book.getAuthor() + " is already listed");
//            System.out.println("A book with title " + book.getTitle() + " authored by " + book.getAuthor() + " is already listed");
//            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
//        }
		return bookManager.saveBook(book);
//		return new ResponseEntity<Book>(book, HttpStatus.OK);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<?> updateBook(@PathVariable("id") Long id, @RequestBody BookView book) {
		logger.debug("Updating Book " + id);

//		ResponseEntity<Book> currentBook = bookManager.findById(id);
//
//		if (currentBook == null) {
//			logger.debug("Book with id " + id + " not found");
//			return new ResponseEntity<Book>(HttpStatus.NO_CONTENT);
//		}
//
//		currentBook.setTitle(book.getTitle());
//		currentBook.setSeries(book.getSeries());
//		currentBook.setAuthor(book.getAuthor());
//		currentBook.setIllustrator(book.getIllustrator());
//		currentBook.setGenre(book.getGenre());

		return bookManager.updateBook(book);
	}

	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<?> deleteBook(@PathVariable("id") Long id) {
		logger.debug("Fetching & Deleting Book with id " + id);

//		Book book = bookManager.findById(id);
//		if (book == null) {
//			logger.debug("Unable to delete. Book with id " + id + " not found");
//			return new ResponseEntity<Book>(HttpStatus.NO_CONTENT);
//		}

		bookManager.deleteBookById(id);
		
		return new ResponseEntity<BookView>(HttpStatus.NO_CONTENT);
	}

}