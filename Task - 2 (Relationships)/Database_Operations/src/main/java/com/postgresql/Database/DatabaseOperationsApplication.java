package com.postgresql.Database;

import com.postgresql.Database.Model.Address;
import com.postgresql.Database.Model.Author;
import com.postgresql.Database.Model.Book;
import com.postgresql.Database.Model.Category;
import com.postgresql.Database.Repository.AddressRepo;
import com.postgresql.Database.Repository.AuthorRepo;
import com.postgresql.Database.Repository.BookRepo;
import com.postgresql.Database.Repository.CategoryRepo;
import org.slf4j.ILoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Set;

@SpringBootApplication
public class DatabaseOperationsApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DatabaseOperationsApplication.class, args);
	}

	@Autowired
	private AddressRepo addressRepo;

	@Autowired
	private AuthorRepo authorRepo;

	@Autowired
	private CategoryRepo categoryRepo;

	@Autowired
	private BookRepo bookRepo;

	@Override
	public void run(String... args) throws Exception {

		Author a1 = new Author();
		a1.setName("Dale Carnegie");
		a1.setEmail("Dalecarnegie@gmail.com");
		a1.setBio("Popular Author");

		Author a2 = new Author();
		a2.setName("Robert T. Kiyosaki");
		a2.setEmail("Robert@gmail.com");
		a2.setBio("Has become a prominent figure in the personal finance education sector.");

		Book b1 = new Book();
		b1.setTitle("How to Win Friends and Influence Peoples");
		b1.setAuthor(a1);
		b1.setPublishedDate(new Date());

		Book b2 = new Book();
		b2.setTitle("Rich Dad and Poor Dad");
		b2.setAuthor(a2);
		b2.setPublishedDate(new Date());

		Book b3 = new Book();
		b3.setTitle("The Quick and Easy Way to Effective Speaking");
		b3.setAuthor(a2);
		b3.setPublishedDate(new Date());

		Address address1 = new Address();
		address1.setAuthor(a1);
		address1.setCity("Maryville");
		address1.setState("Missouri");
		address1.setZipCode(110041L);

		Address address2 = new Address();
		address2.setAuthor(a2);
		address2.setCity("Arizona");
		address2.setState("Arizona");
		address2.setZipCode(110041L);

		Category cat1 = new Category();
		cat1.setName("Self-Help");
		cat1.setDescription("Effective communication, relationship building, and influencing others.");

		categoryRepo.save(cat1);

		authorRepo.save(a1);

		addressRepo.save(address1);

		bookRepo.save(b1);
	}
}
