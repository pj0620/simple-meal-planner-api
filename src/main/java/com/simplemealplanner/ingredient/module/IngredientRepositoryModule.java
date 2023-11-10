package com.simplemealplanner.ingredient.module;

import com.simplemealplanner.common.repository.SMPCrudController;
import com.simplemealplanner.ingredient.model.CreateUpdateIngredientModel;
import com.simplemealplanner.ingredient.model.Ingredient;
import com.simplemealplanner.ingredient.model.IngredientDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.CrudRepository;

import static com.simplemealplanner.ingredient.mapper.CreateUpdateIngredientMapper.CREATE_UPDATE_TO_INGREDIENT;
import static com.simplemealplanner.ingredient.mapper.IngredientDBMapper.DTO_TO_INGREDIENT_MAPPER;
import static com.simplemealplanner.ingredient.mapper.IngredientDBMapper.INGREDIENT_TO_DTO_MAPPER;

@Configuration
public class IngredientRepositoryModule {
    @Bean
    public SMPCrudController<Ingredient, CreateUpdateIngredientModel, IngredientDTO> ingredientCrudRepository(
            final CrudRepository<IngredientDTO, String> baseIngredientsRepository
    ) {
        return new SMPCrudController<>(
                CREATE_UPDATE_TO_INGREDIENT,
                INGREDIENT_TO_DTO_MAPPER,
                DTO_TO_INGREDIENT_MAPPER,
                baseIngredientsRepository
        );
    }
}
