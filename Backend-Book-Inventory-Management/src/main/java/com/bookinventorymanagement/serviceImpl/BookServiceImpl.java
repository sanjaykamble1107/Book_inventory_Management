package com.bookinventorymanagement.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookinventorymanagement.dto.BookDto;
import com.bookinventorymanagement.dto.ResponseDto;
import com.bookinventorymanagement.entity.Book;
import com.bookinventorymanagement.entity.Category;
import com.bookinventorymanagement.entity.Publisher;
import com.bookinventorymanagement.exceptions.BookNotFoundException;
import com.bookinventorymanagement.exceptions.CategoryNotFoundException;
import com.bookinventorymanagement.exceptions.PublisherNotFoundException;
import com.bookinventorymanagement.repository.BookRepository;
import com.bookinventorymanagement.repository.CategoryRepository;
import com.bookinventorymanagement.repository.PublisherRepository;
import com.bookinventorymanagement.service.BookService;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private PublisherRepository publisherRepository;

	private ResponseDto responseDto = new ResponseDto();

	/**
	 * Get all books from the repository.
	 * 
	 * @return The list of BookDto containing the book details.
	 */
	@Override
	public List<BookDto> getAllBooks() {
		List<Book> list = bookRepository.findAll();
		List<BookDto> dtos = new ArrayList<>();
		for (Book book : list) {
			BookDto bookDto = new BookDto();
			BeanUtils.copyProperties(book, bookDto);
			bookDto.setCategory(book.getCatId().getCatId());
			bookDto.setPublisherId(book.getPublisherId().getPublisherId());
			dtos.add(bookDto);
		}
		return dtos;
	}

	/**
	 * Get a book by its ISBN.
	 * 
	 * @param isbn The ISBN of the book.
	 * @return The BookDto containing the book details.
	 * @throws BookNotFoundException if the book with the given ISBN is not found.
	 */
	@Override
	public BookDto getBookByIsbn(String isbn) {
		Optional<Book> bookIsbn = bookRepository.findByIsbn(isbn);
		if (bookIsbn.isPresent()) {
			BookDto dto = new BookDto();
			BeanUtils.copyProperties(bookIsbn.get(), dto);
			dto.setCategory(bookIsbn.get().getCatId().getCatId());
			dto.setPublisherId(bookIsbn.get().getPublisherId().getPublisherId());

			return dto;
		}
		throw new BookNotFoundException("Book not found with ISBN: " + isbn);

	}

	/**
	 * Get a book by its title.
	 * 
	 * @param title The title of the book.
	 * @return The BookDto containing the book details.
	 * @throws BookNotFoundException if the book with the given title is not found.
	 */
	@Override
	public BookDto getBookByTitle(String title) {
		Optional<Book> bookTitle = bookRepository.findByTitle(title);
		if (bookTitle.isPresent()) {
			BookDto dto = new BookDto();
			BeanUtils.copyProperties(bookTitle.get(), dto);
			dto.setCategory(bookTitle.get().getCatId().getCatId());
			dto.setPublisherId(bookTitle.get().getPublisherId().getPublisherId());
			return dto;
		}
		throw new BookNotFoundException("Book not found with title: " + title);
	}

	/**
	 * Get all books by the publisher ID.
	 * 
	 * @param publisherId The ID of the publisher.
	 * @return The list of BookDto containing the book details.
	 * @throws PublisherNotFoundException if the publisher with the given ID is not
	 *                                    found.
	 * @throws BookNotFoundException      if there are no books available for the
	 *                                    given publisher ID.
	 */
	@Override
	public List<BookDto> getBookByPublisherId(Integer publisherId) {
		Optional<Publisher> optional = publisherRepository.findById(publisherId);
		if (optional.isEmpty()) {
			throw new PublisherNotFoundException("Publisher not found with ID: " + publisherId);
		}

		Optional<List<Book>> books = bookRepository.findByPublisherId(optional.get());

		if (books.get().isEmpty()) {
			throw new BookNotFoundException("There are no books available with publisher ID: " + publisherId);
		}

		List<BookDto> bookDtos = new ArrayList<>();

		for (Book book : books.get()) {
			BookDto bookDto = new BookDto();
			BeanUtils.copyProperties(book, bookDto);
			bookDto.setCategory(book.getCatId().getCatId());
			bookDto.setPublisherId(book.getPublisherId().getPublisherId());
			bookDtos.add(bookDto);
		}
		return bookDtos;
	}

	/**
	 * Get all books by the category ID.
	 * 
	 * @param category The ID of the category.
	 * @return The list of BookDto containing the book details.
	 * @throws CategoryNotFoundException if the category with the given ID is not
	 *                                   found.
	 * @throws BookNotFoundException     if there are no books available for the
	 *                                   given category ID.
	 */
	@Override
	public List<BookDto> getBookByCategory(Integer category) {
		Optional<Category> bookCategory = categoryRepository.findById(category);

		if (bookCategory.isPresent()) {
			Optional<List<Book>> listOptional = bookRepository.findByCatId(bookCategory.get());

			if (listOptional.get().isEmpty()) {
				throw new BookNotFoundException("Book not found with category ID: " + category);
			}

			List<BookDto> bookDtos = new ArrayList<>();

			for (Book book : listOptional.get()) {
				BookDto bookDto = new BookDto();
				BeanUtils.copyProperties(book, bookDto);
				bookDto.setCategory(book.getCatId().getCatId());
				bookDto.setPublisherId(book.getPublisherId().getPublisherId());
				bookDtos.add(bookDto);
			}

			return bookDtos;
		}

		throw new CategoryNotFoundException("Category not found with ID: " + category);
	}

	/**
	 * Save a book to the repository.
	 * 
	 * @param bookDto The BookDto containing the book details.
	 * @return The ResponseDto with a success message.
	 * @throws BookNotFoundException      if a book with the same ISBN already
	 *                                    exists.
	 * @throws CategoryNotFoundException  if the category with the given ID is not
	 *                                    found.
	 * @throws PublisherNotFoundException if the publisher with the given ID is not
	 *                                    found.
	 */
	@Override
	public ResponseDto saveBook(BookDto bookDto) {
		Optional<Book> optional = bookRepository.findByIsbn(bookDto.getIsbn());
		if (optional.isPresent()) {
			throw new BookNotFoundException("Book is already present with ISBN: " + bookDto.getIsbn());
		}
		Book book = new Book();
		Optional<Category> category = categoryRepository.findById(bookDto.getCategory());
		if (category.isEmpty()) {
			throw new CategoryNotFoundException("Category not found with ID: " + bookDto.getCategory());
		}
		Optional<Publisher> publisher = publisherRepository.findById(bookDto.getPublisherId());
		if (publisher.isEmpty()) {
			throw new PublisherNotFoundException("Publisher not found with ID: " + bookDto.getPublisherId());
		}
		book.setCatId(category.get());
		book.setPublisherId(publisher.get());
		BeanUtils.copyProperties(bookDto, book);
		responseDto.setResponseMessage("Book added successfully");
		bookRepository.save(book);
		return responseDto;
	}

	/**
	 * Update the title of a book by its ISBN.
	 * 
	 * @param isbn    The ISBN of the book.
	 * @param bookDto The BookDto containing the updated book details.
	 * @return The BookDto containing the updated book details.
	 * @throws BookNotFoundException if the book with the given ISBN is not found.
	 */
	@Override
	public BookDto updateTitleByIsbn(String isbn, BookDto bookDto) {
		Optional<Book> optionalBook = bookRepository.findByIsbn(isbn);
		if (optionalBook.isPresent()) {
			Book book = optionalBook.get();
			book.setTitle(bookDto.getTitle());
			bookRepository.save(book);
			BookDto dto = new BookDto();
			dto.setCategory(book.getCatId().getCatId());
			dto.setPublisherId(book.getPublisherId().getPublisherId());
			BeanUtils.copyProperties(book, dto);
			return dto;
		}


		throw new BookNotFoundException("Book not found with ISBN: " + isbn);
	}

	/**
	 * Update the description of a book by its ISBN.
	 * 
	 * @param isbn    The ISBN of the book.
	 * @param bookDto The BookDto containing the updated book details.
	 * @return The BookDto containing the updated book details.
	 * @throws BookNotFoundException if the book with the given ISBN is not found.
	 */
	@Override
	public BookDto updateDescriptionByIsbn(String isbn, BookDto bookDto) {
		Optional<Book> optionalBook = bookRepository.findByIsbn(isbn);
		if (optionalBook.isPresent()) {
			Book book = optionalBook.get();
			book.setDescription(bookDto.getDescription());
			bookRepository.save(book);
			BookDto dto = new BookDto();
			dto.setCategory(book.getCatId().getCatId());
			dto.setPublisherId(book.getPublisherId().getPublisherId());
			BeanUtils.copyProperties(book, dto);
			return dto;
		}
		throw new BookNotFoundException("Book not found with ISBN: " + isbn);
	}

	/**
	 * Update the edition of a book by its ISBN.
	 * 
	 * @param isbn    The ISBN of the book.
	 * @param bookDto The BookDto containing the updated book details.
	 * @return The BookDto containing the updated book details.
	 * @throws BookNotFoundException if the book with the given ISBN is not found.
	 */
	@Override
	public BookDto updateEditionByIsbn(String isbn, BookDto bookDto) {
		Optional<Book> optionalBook = bookRepository.findByIsbn(isbn);
		if (optionalBook.isPresent()) {
			Book book = optionalBook.get();
			book.setEdition(bookDto.getEdition());
			bookRepository.save(book);
			BookDto dto = new BookDto();
			dto.setCategory(book.getCatId().getCatId());
			dto.setPublisherId(book.getPublisherId().getPublisherId());
			BeanUtils.copyProperties(book, dto);
			return dto;
		}
		throw new BookNotFoundException("Book not found with ISBN: " + isbn);
	}

	/**
	 * Update the category of a book by its ISBN.
	 * 
	 * @param isbn    The ISBN of the book.
	 * @param bookDto The BookDto containing the updated book details.
	 * @return The BookDto containing the updated book details.
	 * @throws BookNotFoundException     if the book with the given ISBN is not
	 *                                   found.
	 * @throws CategoryNotFoundException if the category with the given ID is not
	 *                                   found.
	 */
	@Override
	public BookDto updateCategoryByIsbn(String isbn, BookDto bookDto) {
		Optional<Book> optionalBook = bookRepository.findByIsbn(isbn);
		if (optionalBook.isPresent()) {
			Book book = optionalBook.get();
			Optional<Category> category = categoryRepository.findById(bookDto.getCategory());
			if (category.isEmpty()) {
				throw new CategoryNotFoundException("Category not found with ID: " + bookDto.getCategory());
			}

			book.setCatId(category.get());
			bookRepository.save(book);
			BookDto dto = new BookDto();
			dto.setCategory(book.getCatId().getCatId());
			dto.setPublisherId(book.getPublisherId().getPublisherId());
			BeanUtils.copyProperties(book, dto);
			return dto;
		}
		throw new BookNotFoundException("Book not found with ISBN: " + isbn);
	}

	/**
	 * Update the publisher ID of a book by its ISBN.
	 * 
	 * @param isbn    The ISBN of the book.
	 * @param bookDto The BookDto containing the updated book details.
	 * @return The BookDto containing the updated book details.
	 * @throws BookNotFoundException      if the book with the given ISBN is not
	 *                                    found.
	 * @throws PublisherNotFoundException if the publisher with the given ID is not
	 *                                    found.
	 */
	@Override
	public BookDto upatePublisherIdByIsbn(String isbn, BookDto bookDto) {
		Optional<Book> optionalBook = bookRepository.findByIsbn(isbn);
		if (optionalBook.isPresent()) {
			Book book = optionalBook.get();
			Optional<Publisher> publisher = publisherRepository.findById(bookDto.getPublisherId());
			if (publisher.isEmpty()) {
				throw new PublisherNotFoundException("Publisher not found with ID: " + bookDto.getPublisherId());
			}
			book.setPublisherId(publisher.get());
			bookRepository.save(book);
			BookDto dto = new BookDto();
			dto.setCategory(book.getCatId().getCatId());
			dto.setPublisherId(book.getPublisherId().getPublisherId());
			BeanUtils.copyProperties(book, dto);
			return dto;
		}
		throw new BookNotFoundException("Book not found with ISBN: " + isbn);
	}
}
