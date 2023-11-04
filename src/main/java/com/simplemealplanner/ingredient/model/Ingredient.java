package com.simplemealplanner.ingredient.model;


import com.simplemealplanner.common.model.IdModel;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class Ingredient extends IdModel {
    private String name;
}
