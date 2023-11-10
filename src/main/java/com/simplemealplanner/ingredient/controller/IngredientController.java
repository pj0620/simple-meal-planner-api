package com.simplemealplanner.ingredient.controller;

import com.simplemealplanner.common.repository.SMPCrudController;
import com.simplemealplanner.ingredient.model.CreateUpdateIngredientModel;
import com.simplemealplanner.ingredient.model.Ingredient;
import com.simplemealplanner.ingredient.model.IngredientDTO;
import com.simplemealplanner.ingredient.repository.IngredientRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ingredients")
@AllArgsConstructor
@Slf4j
public class IngredientController {
    @Deprecated
    private IngredientRepository ingredientRepository;
    private SMPCrudController<Ingredient, CreateUpdateIngredientModel, IngredientDTO> ingredientCrudRepository;
    @GetMapping
    Iterable<Ingredient> listIngredients() {
        return ingredientCrudRepository.listData();
    }

    @GetMapping("/{id}")
    ResponseEntity<Ingredient> getIngredient(
            @PathVariable String id
    ) {
        return ingredientCrudRepository.getData(id);
    }

    @PostMapping
    Ingredient createIngredient(
            @RequestBody CreateUpdateIngredientModel newCreateIngredient
    ) {
        return ingredientCrudRepository.createData(newCreateIngredient);
    }

    @PutMapping("/{id}")
    ResponseEntity<Ingredient> updateIngredient(
            @PathVariable String id,
            @RequestBody CreateUpdateIngredientModel updateIngredient
    ) {
        return ingredientCrudRepository.update(id, updateIngredient);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteIngredient(
            @PathVariable String id
    ) {
        return ingredientCrudRepository.delete(id);
    }
}
