package com.bits.bookstore.webapp.web.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OrderController {

    private static final Logger log = LoggerFactory.getLogger(OrderController.class);

    @GetMapping("/cart")
    String productPage(@RequestParam(name = "page", defaultValue = "1") int pageNum, Model model) {
        model.addAttribute("page", pageNum);
        return "cart";
    }

    @GetMapping("/orders")
    String getOrders() {
        return "orders";
    }

    @GetMapping("/orders/{orderNo}")
    String getOrderDetails(@PathVariable String orderNo, Model model) {
        log.info("Get Order with number {} ", orderNo);
        model.addAttribute("orderNo", orderNo);
        return "order_details";
    }
}
