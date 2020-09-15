package com.example.Bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.Bookstore.domain.Book;
import com.example.Bookstore.domain.BookRepository;

@SpringBootApplication
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(BookRepository repository){
	return(args)->{
		Book b1 = new Book("Sapiens", "Yuval Noah Harari", 2011, "22223", 20.00);
		Book b2 = new Book("Homo Dues", "Yuval Noah Harari", 2015,"26537", 25.00);
		repository.save(b1);
		repository.save(b2);
	
	};
	}
	

}
