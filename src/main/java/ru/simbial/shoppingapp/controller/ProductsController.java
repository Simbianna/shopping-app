package ru.simbial.shoppingapp.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import ru.simbial.shoppingapp.entity.Product;
import ru.simbial.shoppingapp.repository.specification.ProductSpec;
import ru.simbial.shoppingapp.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import java.util.Optional;;

@Controller
@RequestMapping("/products")
public class ProductsController {
    private ProductsService productsService;
    private final static Integer PAGE_SIZE = 10;

    @Autowired
    public void setProductsService(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping()
    String showProductsListWithFilter(HttpServletRequest request, Model model,
                                     // @RequestParam(value = "p", required = false) Integer pageNum,
                                      @RequestParam(value = "page") Optional<Integer> page,
                                      @RequestParam(value = "input", required = false) String input,
                                      @RequestParam(value = "maxPrice", required = false) Double maxPrice,
                                      @RequestParam(value = "minPrice", required = false) Double minPrice) {
        //  final int currentPage = (page.orElse(0) < 1) ? 1 : page.get() - 1;
        int pageNumber = 0;

        StringBuilder filters = new StringBuilder();
        Specification<Product> specification = Specification.where(null);
        if (request.getParameter("pageNum") != null && !request.getParameter("pageNum").isEmpty()) {
            pageNumber = Integer.parseInt(request.getParameter("pageNum")) - 1;
        }

        if (input != null) {
            specification = specification.and(ProductSpec.titleContains(input));
            filters.append("@contains word=").append(input);
        }
        if (minPrice != null) {
            specification = specification.and(ProductSpec.priceGraterThanOrEquals(minPrice));
            filters.append("@min=").append(minPrice);
        }
        if (maxPrice != null) {
            specification = specification.and(ProductSpec.priceLessThanOrEquals(maxPrice));
            filters.append("@max=").append(maxPrice);
        }
        Page<Product> products = productsService.getAllFilteredWithPaginating(pageNumber, PAGE_SIZE, specification);

        //  List<Product> products = productsService.getAllProds();

        Product product = new Product();

        model.addAttribute("product", product);
        model.addAttribute("input", input);
        model.addAttribute("minPrice", minPrice);
        model.addAttribute("maxPrice", maxPrice);
        model.addAttribute("products", products);
      //  model.addAttribute("page", pageNum);
        model.addAttribute("filter", filters);
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
