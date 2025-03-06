package com.polarbookshop.orderservice.order.web;

import com.polarbookshop.orderservice.order.domain.Order;
import com.polarbookshop.orderservice.order.domain.OrderService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("orders")
public class OrderController {

  private static final Logger log = LoggerFactory.getLogger(OrderController.class);
  private final OrderService orderService;

  public OrderController(OrderService orderService) {
    this.orderService = orderService;
  }

  @GetMapping
  public Flux<Order> getAllOrders() {
    log.info("Fetching all orders");
    return orderService.getAllOrders();
  }

  @PostMapping
  public Mono<Order> submitOrder(@RequestBody @Valid OrderRequest orderRequest) {
    log.info("Order for {} copies of the book with ISBN {}", orderRequest.quantity(), orderRequest.isbn());
    return orderService.submitOrder(orderRequest.isbn(), orderRequest.quantity());
  }
}
