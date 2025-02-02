package co.simplon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import co.simplon.dto.ProductDTO;
import co.simplon.service.ProductService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }
    
    @GetMapping("/products")
    public String getProducts(Model model){
        model.addAttribute("products", productService.getAllProducts());
        return "products/list.html";
    }

    @GetMapping("/products/new")
    public String showCreateProductForm(Model model) {
        model.addAttribute("product", new ProductDTO());
        return "products/new.html";
    }

    @PostMapping("/products")
    public String createProduct(@ModelAttribute("product") ProductDTO productDTO) {
        productService.createProduct(productDTO);
        return "redirect:/products";
    }

    @GetMapping("/products/{id}")
    public String getProductDetail(@PathVariable String id, Model model) {
        ProductDTO product = productService.getProductById(id);
        if (product != null) {
            model.addAttribute("product", product);
            return "products/detail.html";
        }
        return "redirect:/products";
    }
}
