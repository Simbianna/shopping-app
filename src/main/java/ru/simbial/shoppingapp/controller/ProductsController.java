package ru.simbial.shoppingapp.controller;

import ru.simbial.shoppingapp.entity.Product;
import ru.simbial.shoppingapp.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductsController {
    private ProductsService productsService;

    @Autowired
    public void setProductsService(ProductsService productsService) {
        this.productsService = productsService;
    }

//    @GetMapping
//    public String showProductsList(Model model) {
//        Product product = new Product();
//        model.addAttribute("products", productsService.getAllProducts());
//        model.addAttribute("product", product);
//        return "products";
//    }

    @GetMapping()
    String showProductsListWithFilter(Model model,
                                      @RequestParam(value = "input", required = false) String input,
                                      @RequestParam(value = "maxPrice", required = false) String maxPrice,
                                      @RequestParam(value = "minPrice", required = false) String minPrice) {
        Product product = new Product();
        model.addAttribute("product", product);
        model.addAttribute("input", input);
        model.addAttribute("minPrice", minPrice);
        model.addAttribute("maxPrice", maxPrice);
        model.addAttribute("products", productsService.getAllFiltered(input, minPrice, maxPrice));
        return "products";
    }

    /*@GetMapping()
    String showProductsListWithFilter(Model model,
                                      @ModelAttribute(value = "minPrice") String minPrice,
                                      @ModelAttribute(value = "maxPrice") String maxPrice,
                                      @ModelAttribute(value = "input") String input) {
        Product product = new Product();
        //model.addAttribute("input", input);
        model.addAttribute("product", product);
        model.addAttribute("minPrice", minPrice);
        model.addAttribute("maxPrice", maxPrice);
        model.addAttribute("products", productsService.getAllFiltered(input, minPrice, maxPrice));
        return "products";
    }*/

    @GetMapping("/add")
    public String showAddProductForm(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "product-edit";
    }

    @GetMapping("/edit/{id}")
    public String showEditProductForm(Model model, @PathVariable(value = "id") Long id) {
        Product product = productsService.getById(id);
        model.addAttribute("product", product);
        return "product-edit";
    }

    @PostMapping("/edit")
    public String editProduct(@ModelAttribute(value = "product") Product product) {
        productsService.save(product);
        return "redirect:/products";
        //  return "product-page";
    }

    @GetMapping("/show/{id}")
    public String showOneProduct(Model model, @PathVariable(value = "id") Long id) {
        Product product = productsService.getById(id);
        model.addAttribute("product", product);
        return "product-page";
    }

    @PostMapping("/remove/{id}")
    public String removeProduct(@PathVariable(value = "id") Long id) {
        productsService.deleteById(id);
        return "redirect:/products";
    }

        @PostMapping("/add")
    public String addProduct(@ModelAttribute(value = "product") Product product) {
        productsService.save(product);
        return "redirect:/products";
    }

}
