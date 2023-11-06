package com.simplemealplanner.ingredient.controller;

import com.simplemealplanner.ingredient.model.CreateUpdateIngredientModel;
import com.simplemealplanner.ingredient.model.Ingredient;
import com.simplemealplanner.ingredient.model.IngredientDTO;
import com.simplemealplanner.ingredient.repository.IngredientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static com.simplemealplanner.ingredient.mapper.CreateUpdateIngredientMapper.CREATE_UPDATE_TO_INGREDIENT;
import static com.simplemealplanner.ingredient.mapper.IngredientDBMapper.DTO_TO_INGREDIENT_MAPPER;
import static com.simplemealplanner.ingredient.mapper.IngredientDBMapper.INGREDIENT_TO_DTO_MAPPER;

@RestController
@RequestMapping("/ingredients")
@Slf4j
public class IngredientController {
    private IngredientRepository ingredientRepository;
    private static int lastId = 4;

    public IngredientController(
            IngredientRepository ingredientRepository
    ) {
        this.ingredientRepository = ingredientRepository;
        this.ingredientRepository.saveAll(List.of(
                IngredientDTO.builder().id("1").name("flour").build(),
                IngredientDTO.builder().id("2").name("sugar").build(),
                IngredientDTO.builder().id("3").name("butter").build(),
                IngredientDTO.builder().id("4").name("egg").build()
        ));
    }

    @GetMapping
    Iterable<Ingredient> listIngredients() {
        return StreamSupport.stream(ingredientRepository.findAll().spliterator(), false).map(
                DTO_TO_INGREDIENT_MAPPER).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    ResponseEntity<Ingredient> getIngredient(
            @PathVariable String id
    ) {
        if (id == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return ingredientRepository.findById(id)
                .map(DTO_TO_INGREDIENT_MAPPER)
                .map(ingredient -> new ResponseEntity<>(ingredient, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @PostMapping
    Ingredient createIngredient(
            @RequestBody CreateUpdateIngredientModel newCreateIngredient
    ) {
        // TODO: add validator
        Ingredient newIngredient = CREATE_UPDATE_TO_INGREDIENT.apply(newCreateIngredient);
        IngredientDTO createdIngredient = ingredientRepository.save(INGREDIENT_TO_DTO_MAPPER.apply(newIngredient));
        return DTO_TO_INGREDIENT_MAPPER.apply(createdIngredient);
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

        Ingredient ingredient = CREATE_UPDATE_TO_INGREDIENT.apply(updateIngredient);

        HttpStatus responseCode;
        if (ingredientRepository.existsById(id)) {
            ingredient.setId(id);
            responseCode = HttpStatus.OK;
        } else {
            responseCode = HttpStatus.CREATED;
        }

        ingredientRepository.save(INGREDIENT_TO_DTO_MAPPER.apply(ingredient));
        return new ResponseEntity<>(ingredient, responseCode);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteIngredient(
            @PathVariable String id
    ) {
        if (id == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        ingredientRepository.deleteById(id);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}
