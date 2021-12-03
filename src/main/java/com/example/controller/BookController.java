package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.Book;

@RestController
public class BookController {

	@GetMapping("/book/{id}")
	public Book getABook(@PathVariable Integer id) {
		Book book = new Book();
		book.setId(id);
		book.setTitle("spring rest docs in spring boot");
		book.setAuthor("taetaetae");
		book.setDev("두세덕규");
		return book;
	}
}
