package com.example.Bookstore.web;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Bookstore.domain.Book;
import com.example.Bookstore.domain.BookRepository;
import com.example.Bookstore.domain.Category;
import com.example.Bookstore.domain.CategoryRepository;

@Controller
public class BookController {
	
	@Autowired
	private BookRepository repository;
	
	@Autowired
	private CategoryRepository cRepository;
	
	private final AtomicLong counter = new AtomicLong();
	
//	//Test
//	@RequestMapping("/")
//	public String greeting() {
//		 return "redirect:../booklist";
//	}
//
//	@RequestMapping("/hello")
//	public Book test(@RequestParam(value = "title", defaultValue = "World") String name) {
//		return new Book(name);
//	}
	
	// Show all books

//	@RequestMapping(value= {​​​​​"/", "/booklist"}​​​​​)
//	    public String bookList(Model model) {​​​​​
//	        model.addAttribute("books", repository.findAll());
//	        return "booklist";
//	    }​​​​​
	
	
	@RequestMapping(value= {"/", "/booklist"})
	public String bookList(Model model) {
		model.addAttribute("books", repository.findAll());
		return "booklist";
	}
	
	@RequestMapping(value="/add")
	public String addBook(Model model){
	model.addAttribute("book",new Book());
	model.addAttribute("categorys", cRepository.findAll());
	return"addbook";
	}
	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public String save(Book book){
	repository.save(book);
	return"redirect:booklist";
	}
	
	//delete book
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable("id") Long bookId, Model model) {
    	repository.deleteById(bookId);
    	 return "redirect:../booklist";
	}
	
    @RequestMapping(value = "/modify/{id}", method = RequestMethod.GET)
    public String modifyBook(@PathVariable("id") Long bookId, Model model) {
    	Optional<Book> book = repository.findById(bookId);
    	model.addAttribute("book", book);
    	model.addAttribute("categorys", cRepository.findAll());
        return "modifybook";
    } 
    
    @RequestMapping(value="/login")
    public String login() {
    	return "login";
    }
	
    //RESTFUL service
    @RequestMapping(value="/books", method = RequestMethod.GET)
    public @ResponseBody List<Book> bookListRest(){
		return (List<Book>) this.repository.findAll();
    }
    
    @RequestMapping(value="/books/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long bookId){
		return this.repository.findById(bookId);
    }
    
    @RequestMapping(value="/addBook/{title}/{author}", method = RequestMethod.GET)
    public @ResponseBody Object addBookRest(@PathVariable("title") String bookTitle, @PathVariable("author") String author){
		Book t = new Book(bookTitle, author, 2001, "TISB", 23, cRepository.findByName("Classic").get(0));
    	this.repository.save(t);
    	return this.repository.findByTitle(bookTitle);
    }
    
    
    

	
	

}
