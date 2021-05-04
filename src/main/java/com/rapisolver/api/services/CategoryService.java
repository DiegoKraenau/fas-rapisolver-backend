package com.rapisolver.api.services;

import com.rapisolver.api.dtos.CategoryDTO;
import com.rapisolver.api.dtos.CreateCategoryDTO;
import com.rapisolver.api.exceptions.RapisolverException;

import java.util.List;

public interface CategoryService {
    List<CategoryDTO> findCategories() throws RapisolverException;
    CategoryDTO findById(Long categoryId) throws RapisolverException;
    CategoryDTO createCategories(CreateCategoryDTO createCategoryDTO) throws RapisolverException;
}
