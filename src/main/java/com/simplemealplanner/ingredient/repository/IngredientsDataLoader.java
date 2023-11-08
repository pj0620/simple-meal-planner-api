package com.simplemealplanner.ingredient.repository;

import com.simplemealplanner.common.util.IdUtils;
import com.simplemealplanner.ingredient.constants.DefaultIngredients;
import com.simplemealplanner.ingredient.model.IngredientDTO;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class IngredientsDataLoader {
    private final IngredientRepository ingredientRepository;
    private final DefaultIngredients defaultIngredients;

    @PostConstruct
    private void loadData() {
        ingredientRepository.saveAll(
                defaultIngredients.getAllNames().stream()
                        .map(name -> IngredientDTO.builder()
                                .name(name)
                                .build())
                        .collect(Collectors.toList())
        );
    }
}
