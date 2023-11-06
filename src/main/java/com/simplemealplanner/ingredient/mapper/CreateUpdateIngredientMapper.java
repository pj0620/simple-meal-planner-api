package com.simplemealplanner.ingredient.mapper;

import com.simplemealplanner.ingredient.model.CreateUpdateIngredientModel;
import com.simplemealplanner.ingredient.model.Ingredient;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.UUID;
import java.util.function.Function;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateUpdateIngredientMapper {
    public static Function<CreateUpdateIngredientModel, Ingredient> CREATE_UPDATE_TO_INGREDIENT = crudModel ->
            Ingredient.builder()
                    .name(crudModel.getName())
                    .id(UUID.randomUUID().toString())
                    .build();
}
