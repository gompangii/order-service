package com.polarbookshop.orderservice.order.domain;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;
import java.time.LocalDateTime;

@Table("orders")
public record Order (
  @Id
  Long id,

  String bookIsbn,
  String bookName,
  Double bookPrice,
  Integer quantity,
  OrderStatus status,

  @CreatedDate
  LocalDateTime createdDate,    // DB가 DATETIME 이나 TIMESTAMP 형인경우 Instant 대신 LocalDateTime 으로 선언 해야 함.

  @LastModifiedDate
  LocalDateTime lastModifiedDate,

  @Version
  int version
){
  public static Order of(
    String bookIsbn, String bookName, Double bookPrice, Integer quantity, OrderStatus status
  ) {
    return new Order(null, bookIsbn, bookName, bookPrice, quantity, status, null, null, 0);
  }
}
