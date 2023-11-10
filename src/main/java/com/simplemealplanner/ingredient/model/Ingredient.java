package com.simplemealplanner.ingredient.model;


import com.simplemealplanner.common.model.IdModel;
import lombok.*;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
public class Ingredient extends IdModel {
    private String id;
    private String name;
}
