package com.bits.bookstore.webapp.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OrderController {

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
        model.addAttribute("orderNo", orderNo);
        return "order_details";
    }
}
