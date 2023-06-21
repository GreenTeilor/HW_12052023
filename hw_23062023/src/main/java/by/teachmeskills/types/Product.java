package by.teachmeskills.types;

import java.math.BigDecimal;

public record Product(int id, String name, String description, String imagePath, String category, BigDecimal price) {
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Product))
            return false;
        return ((Product) o).id == id;
    }
}
