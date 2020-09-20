package com.example.Bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.Bookstore.domain.Book;
import com.example.Bookstore.domain.BookRepository;
import com.example.Bookstore.domain.Category;
import com.example.Bookstore.domain.CategoryRepository;

@SpringBootApplication
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(BookRepository repository, CategoryRepository cRepository) {
		return (args) -> {
			cRepository.save(new Category("Classic"));
			cRepository.save(new Category("Novel"));
			cRepository.save(new Category("Fantasy"));
			
			Book b1 = new Book("Sapiens", "Yuval Noah Harari", 2011, "22223", 20.00, cRepository.findByName("Classic").get(0));
			Book b2 = new Book("Homo Deus", "Yuval Noah Harari", 2015, "26537", 25.00, cRepository.findByName("Novel").get(0));
			repository.save(b1);
			repository.save(b2);

		};
	}
}
