package com.example.Bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.Bookstore.domain.Book;
import com.example.Bookstore.domain.BookRepository;
import com.example.Bookstore.domain.Category;
import com.example.Bookstore.domain.CategoryRepository;
import com.example.Bookstore.domain.User;
import com.example.Bookstore.domain.UserRepository;

@SpringBootApplication
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(BookRepository repository, CategoryRepository cRepository, UserRepository urepository) {
		return (args) -> {
			cRepository.deleteAll();
			repository.deleteAll();
			
			cRepository.save(new Category("Classic"));
			cRepository.save(new Category("Novel"));
			cRepository.save(new Category("Fantasy"));
//			
			Book b1 = new Book("Sapiens", "Yuval Noah Harari", 2011, "22223", 20.00, cRepository.findByName("Classic").get(0));
			Book b2 = new Book("Homo Deus", "Yuval Noah Harari", 2015, "26537", 25.00, cRepository.findByName("Novel").get(0));
			repository.save(b1);
			repository.save(b2);
//			
//			urepository.deleteAll();
//			
			//password1 / password2
//			User user1 = new User("user", "$2y$12$MZ9kzNmNvFLDlp8s/NbJPuRTJVRsp4pBIOZ0dDSiggyXZA0fYIxlK", "USER");
//			User user2 = new User("admin", "$2y$12$xO0UJ9czc7.EOgMnu6OqT.JheBaig2.knVJhU94LO91YhUHtGrJP2", "ADMIN");
//			urepository.save(user1);
//			urepository.save(user2);

		};
	}
}
