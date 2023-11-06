package com.simplemealplanner.ingredient.repository;

import com.simplemealplanner.ingredient.model.IngredientDTO;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<IngredientDTO, String> {

}
