package com.simplemealplanner.ingredient.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ingredients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IngredientDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ingredient_id_seq")
    @SequenceGenerator(name = "ingredient_id_seq", sequenceName = "ingredient_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "name", nullable = false, length = 255) // Define column details
    private String name;
}
