package com.bits.bookstore.order.web.controller;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.Is.is;

import com.bits.bookstore.order.AbstractIT;
import com.bits.bookstore.order.domain.model.OrderSummary;
import com.bits.bookstore.order.testData.TestDataFactory;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.jdbc.Sql;

@Sql("/test-orders.sql")
class OrderControllerTest extends AbstractIT {

    @Nested
    class CreateOrderTests {

        @Test
        void shouldCreateOrderSuccessfully() {
            mockGetProductByCode("P100", "Product 1", new BigDecimal("25.50"));
            var payLoad =
                    """
                    {
                                                "customer" : {
                                                    "name": "hany",
                                                    "email": "hany@gmail.com",
                                                    "phone": "999999999"
                                                },
                                                "deliveryAddress" : {
                                                    "addressLine1": "HNO 123",
                                                    "addressLine2": "Kukatpally",
                                                    "city": "Nasir",
                                                    "state": "Benisuef",
                                                    "zipCode": "500072",
                                                    "country": "Egypt"
                                                },
                                                "items": [
                                                    {
                                                        "code": "P100",
                                                        "name": "Product 1",
                                                        "price": 25.50,
                                                        "quantity": 1
                                                    }
                                                ]
                                            }
                                        ""\";
                    """;
            given().contentType(ContentType.JSON)
                    .body(payLoad)
                    .when()
                    .post("/api/orders")
                    .then()
                    .statusCode(HttpStatus.CREATED.value())
                    .body("orderNumber", notNullValue());
        }

        @Test
        void shouldReturnBadRequestWhenMandatoryDataIsMissing() {
            var payload = TestDataFactory.createOrderRequestWithInvalidCustomer();
            given().contentType(ContentType.JSON)
                    .body(payload)
                    .when()
                    .post("/api/orders")
                    .then()
                    .statusCode(HttpStatus.BAD_REQUEST.value());
        }
    }

    @Nested
    class GetOrdersTests {
        @Test
        void shouldGetOrdersSuccessfully() {
            List<OrderSummary> orderSummaries = given().when()
                    .get("/api/orders")
                    .then()
                    .statusCode(200)
                    .extract()
                    .body()
                    .as(new TypeRef<>() {});

            assertThat(orderSummaries).hasSize(2);
        }
    }

    @Nested
    class GetOrderByOrderNumberTests {
        String orderNumber = "order-123";

        @Test
        void shouldGetOrderSuccessfully() {
            given().when()
                    .get("/api/orders/{orderNumber}", orderNumber)
                    .then()
                    .statusCode(200)
                    .body("orderNumber", is(orderNumber))
                    .body("items.size()", is(2));
        }
    }
}
