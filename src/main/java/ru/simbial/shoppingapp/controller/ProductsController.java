package ru.simbial.shoppingapp.controller;

import org.springframework.data.domain.Page;
import org.springframework.security.access.annotation.Secured;
import ru.simbial.shoppingapp.entity.Product;
import ru.simbial.shoppingapp.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Controller
@RequestMapping("/products")
public class ProductsController {
    private ProductsService productsService;
    private final static Integer PAGE_SIZE = 100;

    @Autowired
    public void setProductsService(ProductsService productsService) {
        this.productsService = productsService;
    }


    //TODO check decimal params
    @GetMapping()
    String showProductsListWithFilter(Model model,
                                      @RequestParam(value = "page", required = false) Integer pageNum,
                                      @RequestParam(value = "input", required = false) String input,
                                      @RequestParam(value = "maxPrice", required = false) BigDecimal maxPrice,
                                      @RequestParam(value = "minPrice", required = false) BigDecimal minPrice) {
        int pageNumber = pageNum != null ? pageNum - 1 : 0;

        StringBuilder filter = new StringBuilder();
        Page<Product> products = productsService.getAllFilteredWithPaginating(pageNumber, PAGE_SIZE, input, maxPrice, minPrice, filter);
        Product product = new Product();

        model.addAttribute("product", product);
        model.addAttribute("filter", filter.toString());
        model.addAttribute("input", input);
        model.addAttribute("minPrice", minPrice);
        model.addAttribute("maxPrice", maxPrice);
        model.addAttribute("products", products.getContent());
        model.addAttribute("page", pageNum);

        return "products";
    }

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

    @PostMapping("/edit/{id}")
    public String editProduct(@ModelAttribute(value = "product") Product product, @PathVariable(value = "id") Long id) {
        productsService.saveOrUpdate(product);
        return "redirect:/products";
        //  return "product-page";
    }

    @GetMapping("/show/{id}")
    public String showOneProduct(Model model, @PathVariable(value = "id") Long id) {
        Product product = productsService.getById(id);
        model.addAttribute("product", product);
        return "product-page";
    }

    @PostMapping("/{id}")
    @Secured("ADMIN")
    public String removeProduct(@PathVariable(value = "id") Long id) {
        productsService.deleteById(id);
        return "redirect:/products";
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute(value = "product") Product product) {
        productsService.saveOrUpdate(product);
        return "redirect:/products";
    }

}
