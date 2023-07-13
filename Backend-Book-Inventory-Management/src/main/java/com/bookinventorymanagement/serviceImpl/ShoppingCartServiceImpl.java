//package com.bookinventorymanagement.serviceImpl;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.bookinventorymanagement.dto.ShoppingCartDto;
//import com.bookinventorymanagement.entity.Book;
//import com.bookinventorymanagement.entity.ShoppingCart;
//import com.bookinventorymanagement.entity.User;
//import com.bookinventorymanagement.exceptions.BookNotFoundException;
//import com.bookinventorymanagement.exceptions.ShoppingCartNotFoundException;
//import com.bookinventorymanagement.exceptions.UserNotFoundException;
//import com.bookinventorymanagement.repository.BookRepository;
//import com.bookinventorymanagement.repository.ShoppingCartRepository;
//import com.bookinventorymanagement.repository.UserRepository;
//import com.bookinventorymanagement.service.ShoppingCartService;
//
//@Service
//public class ShoppingCartServiceImpl implements ShoppingCartService {
//
//	@Autowired
//	private ShoppingCartRepository shoppingCartRepository;
//	@Autowired
//	private UserRepository userRepository;
//	@Autowired
//	private BookRepository bookRepository;
//
//	@SuppressWarnings("deprecation")
//	@Override
//	public String saveShoppingCart(ShoppingCartDto shoppingCartDto) {
//		ShoppingCart shoppingCart = new ShoppingCart();
//		Optional<User> user = userRepository.findById(shoppingCartDto.getUserId());
//		if (user.isEmpty()) {
//			throw new UserNotFoundException("User not found with id : " + shoppingCartDto.getUserId());
//		}
//		Optional<Book> optional = bookRepository.findByIsbn(shoppingCartDto.getIsbn());
//		if (optional.isEmpty()) {
//			throw new BookNotFoundException("Book not found with isbn : " + shoppingCartDto.getIsbn());
//
//		}
//		shoppingCart.setuserID(user.get());
//		shoppingCart.setIsbn(optional.get());
//		shoppingCartRepository.save(shoppingCart);
//		return "Shopping cart added successfully";
//
//	}
//
//	@Override
//	public List<ShoppingCartDto> getShoppingCartByUserId(Integer userId) {
//
//		Optional<List<ShoppingCart>> optional = shoppingCartRepository.findAllByUserId(userId);
//		if (!optional.get().isEmpty()) {
//			List<ShoppingCartDto> cartDtos = new ArrayList<>();
//			for (ShoppingCart cart : optional.get()) {
//				ShoppingCartDto dto = new ShoppingCartDto();
//				dto.setUserId(cart.getuserID().getuserId());
//				dto.setIsbn(cart.getIsbn().getIsbn());
//				cartDtos.add(dto);
//			}
//			return cartDtos;
//		}
//		throw new ShoppingCartNotFoundException("Shopping cart not found with id : " + userId);
//
//	}
//
//	@Override
//	public List<ShoppingCartDto> updateIsbnByUserId(Integer userId, ShoppingCartDto shoppingCartDto) {
//
//		Optional<Book> book = bookRepository.findByIsbn(shoppingCartDto.getIsbn());
//		if (!book.isPresent()) {
//			throw new BookNotFoundException("Book not found with isbn : " + shoppingCartDto.getIsbn());
//		}
//
//		Optional<List<ShoppingCart>> cart = shoppingCartRepository.findAllByUserId(userId);
//
//		if (!cart.get().isEmpty()) {
//			
//			List<ShoppingCartDto> list = new ArrayList<>();
//			
//			for (ShoppingCart cart2 : cart.get()) {
//				ShoppingCart shoppingCart =cart2;
//				shoppingCart.setIsbn(book.get());
//				shoppingCartRepository.save(shoppingCart);
//				ShoppingCartDto dto = new ShoppingCartDto();
//				dto.setUserId(shoppingCart.getuserID().getuserId());
//				dto.setIsbn(shoppingCart.getIsbn().getIsbn());
//				 list.add(dto);
//			}
//		}
//		throw new ShoppingCartNotFoundException("Shopping cart not found with id : " + userId);
//	}
//
//	
//
//}
