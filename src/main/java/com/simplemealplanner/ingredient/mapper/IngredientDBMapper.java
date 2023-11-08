package com.simplemealplanner.ingredient.mapper;

import com.simplemealplanner.ingredient.model.Ingredient;
import com.simplemealplanner.ingredient.model.IngredientDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.function.Function;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class IngredientDBMapper {
    public static Function<IngredientDTO, Ingredient> DTO_TO_INGREDIENT_MAPPER = ingredientDTO ->
            Ingredient.builder()
            .name(ingredientDTO.getName())
            .id(String.valueOf(ingredientDTO.getId()))
            .build();

    public static Function<Ingredient, IngredientDTO> INGREDIENT_TO_DTO_MAPPER = ingredient ->
            IngredientDTO.builder()
                    .name(ingredient.getName())
                    .build();
}
