package com.simplemealplanner.ingredient.constants;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "defaultingredients")
@Getter
@Setter
public class DefaultIngredients {
    private String startIngredientNames0;
    private String startIngredientNames1;
    private String startIngredientNames2;
    private String startIngredientNames3;

    public List<String> getAllNames() {
        if (startIngredientNames0 == null) {
            return List.of("flour", "sugar", "eggs", "chocolate chips");
        }
        return List.of(startIngredientNames0, startIngredientNames1, startIngredientNames2, startIngredientNames3);
    }
}
