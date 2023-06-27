package by.teachmeskills.types;

import java.math.BigDecimal;
import java.time.LocalDate;

public record User(String name, String lastName, String email,  LocalDate birthDate, LocalDate registrationDate, BigDecimal balance, String password, String address, String phoneNumber) {
}
