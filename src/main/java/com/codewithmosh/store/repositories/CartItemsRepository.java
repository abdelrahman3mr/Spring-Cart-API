package com.codewithmosh.store.repositories;

import com.codewithmosh.store.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemsRepository extends JpaRepository<CartItem, Long> {
}
