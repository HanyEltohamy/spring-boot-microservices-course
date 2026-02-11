package com.bits.bookstore.webapp.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductsController {
    @GetMapping
    String index() {
        return "redirect:/products";
    }

    @GetMapping("/products")
    String productPage(@RequestParam(name = "page", defaultValue = "1") int pageNum, Model model) {
        model.addAttribute("page", pageNum);
        return "products";
    }
}
