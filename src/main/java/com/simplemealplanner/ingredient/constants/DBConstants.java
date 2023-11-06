package com.simplemealplanner.ingredient.constants;

import com.simplemealplanner.ingredient.model.IngredientDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DBConstants {
    public final static List<IngredientDTO> INITIAL_INGREDIENTS = List.of(
            IngredientDTO.builder().id("1").name("flour").build(),
            IngredientDTO.builder().id("2").name("sugar").build(),
            IngredientDTO.builder().id("3").name("butter").build(),
            IngredientDTO.builder().id("4").name("egg").build()
    );
}
