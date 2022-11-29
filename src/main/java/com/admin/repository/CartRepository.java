package com.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.admin.model.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

}
