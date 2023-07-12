package by.teachmeskills.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class Category extends BaseEntity {
    private String name;
    private String imagePath;
}
