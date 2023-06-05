package by.teachmeskills.types;

import java.math.BigDecimal;

public record User(String name, String lastName, String email, BigDecimal balance, String password) {
}
