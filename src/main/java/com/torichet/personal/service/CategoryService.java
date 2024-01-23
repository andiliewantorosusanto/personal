package com.torichet.personal.service;

import com.torichet.personal.entity.database.category.Category;
import com.torichet.personal.entity.database.category.CategoryRepository;
import com.torichet.personal.entity.http.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
  @Autowired
  CategoryRepository categoryRepository;

  public ResponseEntity<Response<List<Category>>> get() {
    return new ResponseEntity<>(new Response<>("Data Successfully Retrieved",categoryRepository.findAll()), HttpStatus.OK);
  }

  public ResponseEntity<Response<Category>> createUpdate(Category category) {
    return new ResponseEntity<>(new Response<>("Data Successfully Retrieved",categoryRepository.save(category)), HttpStatus.OK);
  }

  public ResponseEntity<Response<Category>> delete(Long id) {
    categoryRepository.deleteById(id);
    return new ResponseEntity<>(new Response<>("Data Successfully Deleted",null), HttpStatus.OK);
  }
}
