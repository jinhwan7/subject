package com.example.subjectproblem.cart.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.subjectproblem.cart.domain.Cart;
import com.example.subjectproblem.cart.dto.AddCartRequest;
import com.example.subjectproblem.cart.dto.UpdateQuantityRequest;
import com.example.subjectproblem.cart.dto.CartResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

	@Autowired
	private CartService cartService;

	@PostMapping("/add")
	public ResponseEntity<GetCartResponse> addCart(@RequestBody AddCartRequest addCartRequest) {
		Cart addedCart = cartService.addToCart(addCartRequest);
		return ResponseEntity.ok(GetCartResponse.success());
	}

	@GetMapping("/")
	public ResponseEntity<GetCartResponse> getOwnCart(@RequestParam Long customerId) {
		Cart addedCart = cartService.addToCart(addCartRequest);
		return ResponseEntity.ok(GetCartResponse.fromCart(addedCart));
	}

	@PutMapping("/quantity")
	public ResponseEntity<GetCartResponse> putQuantity(@RequestBody UpdateQuantityRequest updateQuantityRequest) {
		Cart updatedCart = cartService.updateQuantity(updateQuantityRequest);
		return ResponseEntity.ok(GetCartResponse.fromCart(updatedCart));
	}

	@DeleteMapping("/{cartId}")
	public ResponseEntity<Void> deleteCart(@PathVariable Long cartId) {
		cartService.deleteCartItem(cartId);
		return ResponseEntity.noContent().build();
	}
}