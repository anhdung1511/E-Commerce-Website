package com.ecommerce.cozashop.repository;

import com.ecommerce.cozashop.model.CartItem;
import com.ecommerce.cozashop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CartItemRepo extends JpaRepository<CartItem, Integer> {

    @Query("select n from CartItem n where n.user.id=?1")
    List<CartItem> findCart(Long id);

    @Query("select c.qty from CartItem c where c.product.id=?1")
    Integer getQtyCart(Long id);
}
