package com.polarbookshop.orderservice.order.domain;

import com.polarbookshop.orderservice.config.DataConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MariaDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;
import reactor.test.StepVerifier;





@DataR2dbcTest
@Import(DataConfig.class)
@Testcontainers
public class OrderRepositoryR2dbcTests {
  @Container
  static MariaDBContainer<?> mariadb = new MariaDBContainer<>(DockerImageName.parse("mariadb:10.11"));

  @Autowired
  private OrderRepository orderRepository;

  @DynamicPropertySource
  static void postgresqlProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.r2dbc.url", OrderRepositoryR2dbcTests::r2dbcUrl);
    registry.add("spring.r2dbc.username", mariadb::getUsername);
    registry.add("spring.r2dbc.password", mariadb::getPassword);

    //registry.add("spring.flyway.url", mariadb::getJdbcUrl);
  }

  private static String r2dbcUrl() {
    return String.format("r2dbc:mariadb://%s:%s/%s", mariadb.getHost(),
      mariadb.getMappedPort(3306), mariadb.getDatabaseName());
  }

  @Test
  void findOrderByIdWhenNotExisting() {
    StepVerifier.create(orderRepository.findById(394L))
      .expectNextCount(0)
      .verifyComplete();
  }

  @Test
  void createRejectedOrder() {
    var rejectedOrder = OrderService.buildRejectedOrder( "1234567890", 3);
    StepVerifier.create(orderRepository.save(rejectedOrder))
      .expectNextMatches(order -> order.status().equals(OrderStatus.REJECTED))
      .verifyComplete();
  }
}
