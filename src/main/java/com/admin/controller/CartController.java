package com.admin.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.admin.model.Cart;
import com.admin.service.CartService;

@RestController
public class CartController {

	@Autowired
	private CartService cartService;

	@GetMapping("/cart/")
	public ResponseEntity<List<Cart>> getProduct() {
		List<Cart> cartList = cartService.listAll();

		if (cartList.size() <= 0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(cartList));
	}

	@PostMapping("/cart/add")
	public ResponseEntity<Cart> addToCart(@Valid @RequestBody Cart cart) {
		Cart cartobj = null;
		try {
			cartobj = this.cartService.addProductToCart(cart);
			return new ResponseEntity<Cart>(cartobj, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PutMapping("/cart/{cartId}")
	public Optional<Object> updateCart(@RequestBody Cart cart, @PathVariable Integer cartId) {
		Optional<Object> cartobj = this.cartService.updateCart(cart, cartId);
		return cartobj;
	}

	@DeleteMapping("/cart/{cartId}")
	public ResponseEntity<Void> delete(@PathVariable Integer cartId) {
		try {
		  this.cartService.delete(cartId);
		  return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
}
