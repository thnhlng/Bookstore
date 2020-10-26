package com.example.Bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.Bookstore.domain.Book;
import com.example.Bookstore.domain.BookRepository;
import com.example.Bookstore.domain.Category;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
public class BookRepositoryTest {

	@Autowired
	private BookRepository repository;
	
	@Test
	public void findByTitleShouldReturnBook() {
		List<Book> books = repository.findByTitle("Sapiens");
		
		assertThat(books).hasSize(1);
		assertThat(books.get(0).getTitle()).isEqualTo("Sapiens");
		System.out.println("Found:" + books);
	}

	@Test
	public void createNewBook() {
		Book btest = new Book("Test2", "Test", 2011, "222234", 20.00, new Category("Test"));
		this.repository.save(btest);
		System.out.println("created: " + btest);
		assertThat(btest.getId()).isNotNull();
	}
	
	@Test
	 public void deleteBook() {
		Book btest = new Book("Test", "Test", 2011, "222234", 20.00, new Category("Test"));
		this.repository.save(btest);
    	this.repository.delete(btest);
    	//List<Book> books = repository.findByTitle("Sapiens");
	
		System.out.println("delete:" + btest);

	}
}
