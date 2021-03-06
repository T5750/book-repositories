package com.evangel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.evangel.db.ReadingListRepository;
import com.evangel.model.AmazonProperties;
import com.evangel.model.Book;
import com.evangel.model.Reader;

@Controller
// @RequestMapping("/readingList")
@RequestMapping("/")
// @RequestMapping({ "/", "readingList" })
// @ConfigurationProperties(prefix = "amazon")
public class ReadingListController {
	private static final String reader = "craig";
	private ReadingListRepository readingListRepository;
	private AmazonProperties amazonProperties;

	@Autowired
	public ReadingListController(ReadingListRepository readingListRepository,
			AmazonProperties amazonProperties) {
		this.readingListRepository = readingListRepository;
		this.amazonProperties = amazonProperties;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/fail")
	public void fail() {
		throw new RuntimeException();
	}

	@ExceptionHandler(value = RuntimeException.class)
	@ResponseStatus(value = HttpStatus.BANDWIDTH_LIMIT_EXCEEDED)
	public String error() {
		return "error";
	}

	@RequestMapping(method = RequestMethod.GET)
	public String readersBooks(Reader reader, Model model) {
		List<Book> readingList = readingListRepository.findByReader(reader);
		if (readingList != null) {
			model.addAttribute("books", readingList);
			model.addAttribute("reader", reader);
			model.addAttribute("amazonID", amazonProperties.getAssociateId());
		}
		return "readingList";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String addToReadingList(Reader reader, Book book) {
		book.setReader(reader);
		readingListRepository.save(book);
		// return "redirect:/readingList";
		return "redirect:/";
	}
}
