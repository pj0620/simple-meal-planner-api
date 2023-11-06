package com.simplemealplanner.ingredient.repository;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import static com.simplemealplanner.ingredient.constants.DBConstants.INITIAL_INGREDIENTS;

@Component
@AllArgsConstructor
public class IngredientsDataLoader {
    private final IngredientRepository ingredientRepository;

    @PostConstruct
    private void loadData() {
        ingredientRepository.saveAll(INITIAL_INGREDIENTS);
    }
}
