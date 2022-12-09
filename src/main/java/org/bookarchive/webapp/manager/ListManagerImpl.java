package org.bookarchive.webapp.manager;

import java.util.List;

import org.bookarchive.webapp.model.BookView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ListManagerImpl implements ListManager{
	
	Logger logger = LoggerFactory.getLogger(ListManagerImpl.class);
	private final String RESOURCE_URI = "/api/favoritebooks";
	
	@Value("${service.url}")
	private String serviceBaseUrl;

	
	@Autowired
	private RestTemplate restTemplate;

	public ResponseEntity<BookView> saveBook(BookView book) {
		String requestUri = serviceBaseUrl + RESOURCE_URI;
		return restTemplate.postForEntity(requestUri, book, BookView.class);
	}
	
	public ResponseEntity<BookView[]> findAllBooks() {
		String requestUri = serviceBaseUrl + RESOURCE_URI;
		ResponseEntity<BookView[]> books = restTemplate.getForEntity(
				requestUri,
				BookView[].class);
		
//		List<Object> booksL = Arrays.stream(books.getBody())  //Requires booksL be Type 'List<Object>'
//				.collect(Collectors.toList());
//		return new ResponseEntity<List<BookView>>(booksL, HttpStatus.OK); //Requires booksL be Type 'List<BookView>' OR Remove booksL argument
		
		return books;
	}
	
	
	public ResponseEntity<BookView> findById(Long id) {
		String requestUri = serviceBaseUrl + RESOURCE_URI;
		return restTemplate.getForEntity(requestUri + "/{id}", BookView.class, Long.toString(id));
	}

	public ResponseEntity<BookView> updateBook(BookView book) {		
		String requestUri = serviceBaseUrl + RESOURCE_URI;
		return restTemplate.exchange(requestUri + "/{id}", 
				HttpMethod.PUT,
				new HttpEntity<>(book),
				BookView.class,
				Long.toString(book.getId()));

	}
	
	public void deleteBookById(Long id) {
		String requestUri = serviceBaseUrl + RESOURCE_URI;
		restTemplate.delete(requestUri + "/{id}", Long.toString(id));
	}

//	@Override
//	public ResponseEntity<Boolean> doesBookExist(BookView book) {
//		System.out.println(book.getTitle());
//		Query query = sessionFactory.openSession().createQuery("FROM Book b WHERE b.title = :title and b.author = :author and b.illustrator = :illustrator");
//		query.setParameter("title", book.getTitle());
//		query.setParameter("author", book.getAuthor());
//		query.setParameter("illustrator", book.getIllustrator());
//		System.out.println(book.getTitle());
//		List<?> pList = query.getResultList();
//		System.out.println(pList.size());
//		if (!pList.isEmpty()) {
//			return true;
//		}
//		return false;
//	
//	}


}
