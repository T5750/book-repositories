package com.evangel;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.evangel.model.Book;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ReadingListApplication.class)
@WebAppConfiguration
public class MockMvcWebTests {
	@Autowired
	private WebApplicationContext webContext;
	private MockMvc mockMvc;
	private final String prefix_reader = "/reader";

	@Before
	public void setupMockMvc() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webContext).build();
	}

	@Test
	public void homePage() throws Exception {
		mockMvc.perform(get(prefix_reader + "/readingList"))
				.andExpect(status().isOk())
				.andExpect(view().name("readingList"))
				.andExpect(model().attributeExists("books"))
				.andExpect(model().attribute("books", is(empty())));
	}

	@Test
	public void postBook() throws Exception {
		mockMvc.perform(post(prefix_reader + "/readingList")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("title", "BOOK TITLE").param("author", "BOOK AUTHOR")
				.param("isbn", "1234567890")
				.param("description", "DESCRIPTION"))
				.andExpect(status().is3xxRedirection()).andExpect(header()
						.string("Location", prefix_reader + "/readingList"));
		Book expectedBook = new Book();
		expectedBook.setId(1L);
		expectedBook.setReader("readingList");// craig
		expectedBook.setTitle("BOOK TITLE");
		expectedBook.setAuthor("BOOK AUTHOR");
		expectedBook.setIsbn("1234567890");
		expectedBook.setDescription("DESCRIPTION");
		mockMvc.perform(get(prefix_reader + "/readingList"))
				.andExpect(status().isOk())
				.andExpect(view().name("readingList"))
				.andExpect(model().attributeExists("books"))
				.andExpect(model().attribute("books", hasSize(1)))
				.andExpect(model().attribute("books",
						contains(samePropertyValuesAs(expectedBook))));
	}
}
