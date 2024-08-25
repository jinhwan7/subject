package com.example.subjectproblem.cart.service;

import java.util.List;

import com.example.subjectproblem.cart.api.AddCartRequest;
import com.example.subjectproblem.cart.api.UpdateQuantityRequest;
import com.example.subjectproblem.cart.domain.Cart;

public interface CartService {
	/**
	 * 고객 ID에 해당하는 모든 장바구니 항목을 조회합니다.
	 *
	 * @param customerId 고객 ID
	 * @return 장바구니 항목 목록
	 */
	List<Cart> getCartItemsByCustomerId(Long customerId);

	/**
	 * 장바구니에 새 항목을 추가합니다.
	 *
	 * @param addCartRequest 추가할 장바구니 항목 정보
	 * @return 추가된 장바구니 항목
	 */
	Cart addToCart(AddCartRequest addCartRequest);

	/**
	 * 장바구니 항목의 수량을 업데이트합니다.
	 *
	 * @param updateQuantityRequest 업데이트할 장바구니 항목 정보
	 * @return 업데이트된 장바구니 항목
	 */
	Cart updateQuantity(UpdateQuantityRequest updateQuantityRequest);

	/**
	 * 지정된 ID의 장바구니 항목을 삭제합니다.
	 *
	 * @param cartId 삭제할 장바구니 항목 ID
	 */
	void deleteCartItem(Long cartId);
}
