package by.teachmeskills.types;

import java.math.BigDecimal;

public record Product(int id, String name, String description, String imagePath, String category, BigDecimal price) {
}
