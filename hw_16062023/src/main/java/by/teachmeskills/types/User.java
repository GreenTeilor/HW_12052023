package by.teachmeskills.types;

import java.math.BigDecimal;
import java.time.LocalDate;

public record User(String name, String lastName, String email,  LocalDate birthDate, BigDecimal balance, String password) {
}
