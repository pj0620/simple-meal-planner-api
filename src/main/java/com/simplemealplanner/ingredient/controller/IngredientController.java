package com.simplemealplanner.ingredient.controller;

import com.simplemealplanner.ingredient.model.CreateUpdateIngredientModel;
import com.simplemealplanner.ingredient.model.Ingredient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/ingredients")
@Slf4j
public class IngredientController {
    private final List<Ingredient> ingredients = new ArrayList<>();
    private static int lastId = 4;

    public IngredientController() {
        ingredients.addAll(List.of(
                Ingredient.builder().id("1").name("flour").build(),
                Ingredient.builder().id("2").name("sugar").build(),
                Ingredient.builder().id("3").name("butter").build(),
                Ingredient.builder().id("4").name("egg").build()
        ));
    }

    @GetMapping
    Iterable<Ingredient> listIngredients() {
        return ingredients;
    }

    @PostMapping
    Ingredient createIngredient(
            @RequestBody CreateUpdateIngredientModel newIngredient
    ) {
        Ingredient ingredient = new Ingredient(newIngredient.getName());
        ingredient.setId(String.valueOf(++lastId));
        ingredients.add(ingredient);
        return ingredient;
    }

    @PutMapping("/{id}")
    ResponseEntity<Ingredient> updateIngredient(
            @PathVariable String id,
            @RequestBody CreateUpdateIngredientModel updateIngredient
    ) {
        if (id == null) {
            // return a 400 Bad Request response
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        for (Ingredient ingredient: ingredients) {
            if (id.equals(ingredient.getId())) {
                ingredient.setName(updateIngredient.getName());
                return new ResponseEntity<>(ingredient, HttpStatus.OK);
            }
        }

        Ingredient ingredient = new Ingredient(updateIngredient.getName());
        ingredient.setId(id);
        ingredients.add(ingredient);
        return new ResponseEntity<>(ingredient, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteIngredient(
            @PathVariable String id
    ) {
        if (id == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        boolean found = ingredients.removeIf(ingredient -> id.equals(ingredient.getId()));
        return found ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
