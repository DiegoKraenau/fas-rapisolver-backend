package com.rapisolver.api.services.impl;

import com.rapisolver.api.dtos.AttentionDTO;
import com.rapisolver.api.dtos.CategoryDTO;
import com.rapisolver.api.dtos.CreateCategoryDTO;
import com.rapisolver.api.entities.Attention;
import com.rapisolver.api.entities.Category;
import com.rapisolver.api.exceptions.InternalServerErrorException;
import com.rapisolver.api.exceptions.NotFoundException;
import com.rapisolver.api.exceptions.RapisolverException;
import com.rapisolver.api.repositories.CategoryRepository;
import com.rapisolver.api.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public static final ModelMapper modelMapper=new ModelMapper();

    @Override
    public List<CategoryDTO> findCategories() throws RapisolverException {
        List<Category> categoryList;
        try {
            categoryList = categoryRepository.findAll();
        }catch (Exception e){
            throw new InternalServerErrorException("Lista de categorias no encontrada");
        }
        return categoryList.stream().map(category -> modelMapper.map(category, CategoryDTO.class)).collect(Collectors.toList());
    }

    @Override
    public CategoryDTO findById(Long categoryId) throws RapisolverException {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new NotFoundException("Categoria con el id"+ categoryId +  "no encontrado"));
        return modelMapper.map(category,CategoryDTO.class);
    }

    @Override
    public CategoryDTO createCategories(CreateCategoryDTO createCategoryDTO) throws RapisolverException {
        Category categoryEntity;
        Category category=new Category();
        category.setDescription(createCategoryDTO.getDescription());
        category.setName(createCategoryDTO.getName());

        try{
            categoryEntity=categoryRepository.save(category);
        }catch(Exception ex){
            throw new NotFoundException("Error!! No se pudo crear la categoria");
        }
        return modelMapper.map(categoryEntity,CategoryDTO.class);
    }
}
