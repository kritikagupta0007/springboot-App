package com.admin.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin.model.Cart;
import com.admin.repository.CartRepository;

@Service
public class CartService {

	@Autowired
	private CartRepository repo;

	public List<Cart> listAll() {
		return repo.findAll();
	}

	public Cart addProductToCart(Cart cart) {
		return repo.save(cart);
	}

	public void delete(Integer cartId) {
		repo.deleteById(cartId);
	}

	public Optional<Object> updateCart(Cart newcart, Integer cartId) {
		return repo.findById(cartId).map(oldCart -> {
			oldCart.setQuantity(newcart.getQuantity());
			return repo.save(oldCart);
		});
	}

}
