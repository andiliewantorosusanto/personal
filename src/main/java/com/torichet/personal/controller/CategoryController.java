package com.torichet.personal.controller;

import com.torichet.personal.entity.database.category.Category;
import com.torichet.personal.entity.http.Response;
import com.torichet.personal.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {
  @Autowired
  CategoryService categoryService;

  @PostMapping("/category")
  public ResponseEntity<Response<Category>> createUpdate(@RequestBody Category category) {
    return categoryService.createUpdate(category);
  }

  @GetMapping("/category")
  public ResponseEntity<Response<List<Category>>> get() {
    return categoryService.get();
  }

  @DeleteMapping("/category/{id}")
  public ResponseEntity<Response<Category>> delete(@PathVariable("id") Long id) {
    return categoryService.delete(id);
  }
}
