package com.rapisolver.api.controllers;

import com.rapisolver.api.dtos.AttentionDTO;
import com.rapisolver.api.dtos.CategoryDTO;
import com.rapisolver.api.dtos.CreateAttentionDTO;
import com.rapisolver.api.dtos.CreateCategoryDTO;
import com.rapisolver.api.exceptions.RapisolverException;
import com.rapisolver.api.response.RapisolverResponse;
import com.rapisolver.api.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/rapiSolver/"+"/v1")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping("/category")
    private RapisolverResponse<List<CategoryDTO>> getAll() {
        List<CategoryDTO> categoryList;
        try {
            categoryList = categoryService.findCategories();
        } catch (RapisolverException e) {
            return new RapisolverResponse<>(e.getCode(), e.getStatus(), e.getMessage());
        }
        return new RapisolverResponse<>(200, "OK","Lista de Categorias", categoryList);
    }

    @GetMapping("/category/{categoryId}")
    private RapisolverResponse<CategoryDTO> getByCategoryId(@PathVariable Long categoryId) {
        CategoryDTO category;
        try {
            category = categoryService.findById(categoryId);
        } catch (RapisolverException e) {
            return new RapisolverResponse<>(e.getCode(), e.getStatus(), e.getMessage());
        }
        return new RapisolverResponse<>(200, "OK","Categoria encontrada", category);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/category")
    public RapisolverResponse<CategoryDTO> createCategory(@RequestBody @Valid CreateCategoryDTO createCategoryDTO) throws RapisolverException{
        return new RapisolverResponse<>(200,String.valueOf(HttpStatus.OK),"OK",categoryService.createCategories(createCategoryDTO));
    }
}
