//package com.bookinventorymanagement.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.bookinventorymanagement.dto.ShoppingCartDto;
//import com.bookinventorymanagement.service.ShoppingCartService;
//
//import jakarta.validation.Valid;
//@CrossOrigin(origins="http://localhost:4200/")
//
//@RestController
//@RequestMapping("/shoppingcart")
//@Validated
//public class ShoppingCartController {
//	
//	@Autowired
//	private ShoppingCartService shoppingCartService;
//	
//	@PostMapping("/post")
//	public ResponseEntity<String> saveShoppingCart(@Valid @RequestBody ShoppingCartDto cartDto){
//		String dto=shoppingCartService.saveShoppingCart(cartDto);
//		return new ResponseEntity<String>(dto,HttpStatus.OK);
//	}
//	
//	@GetMapping("/{userId}")
//	public ResponseEntity<List<ShoppingCartDto>> getShoppinCartById(@PathVariable Integer userId){
//		 List<ShoppingCartDto> dto=shoppingCartService.getShoppingCartByUserId(userId);
//		return new ResponseEntity<List<ShoppingCartDto>>(dto,HttpStatus.OK);
//	}
//	
//	@PutMapping("/update/isbn/{userId}")
//	public ResponseEntity<List<ShoppingCartDto>> updateIsbnById(@PathVariable Integer userId,@Valid @RequestBody ShoppingCartDto cartDto){
//		List<ShoppingCartDto> dto=shoppingCartService.updateIsbnByUserId(userId, cartDto);
//		return new ResponseEntity<List<ShoppingCartDto>>(dto,HttpStatus.OK);
//	}
//
//}
