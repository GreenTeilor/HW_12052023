package by.teachmeskills.utils;

import java.time.LocalDate;

public class ValidatorUtils {
    private static final String prefix = "Неккоректно: ";

    public enum Status {
        INVALID_NAME(prefix + "имя"), INVALID_LASTNAME(prefix + "фамилия"), INVALID_EMAIL(prefix + "email"),
        INVALID_BIRTHDATE(prefix + "дата рождения"), INVALID_PASSWORD(prefix + "пароль"), INVALID_SEVERAL(prefix + "2 и более полей"), VALID("Успешно");
        private final String status;

        Status(String status) {
            this.status = status;
        }

        @Override
        public String toString() {
            return status;
        }
    }

    public static Status validateName(String name) {
        if (!name.matches("[A-Za-z]+")) {
            return Status.INVALID_NAME;
        }
        return Status.VALID;
    }

    public static Status validateLastName(String lastName) {
        if (!lastName.matches("[A-Za-z]+")) {
            return Status.INVALID_LASTNAME;
        }
        return Status.VALID;
    }

    public static Status validateEmail(String email) {
        if (!email.matches("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^-]+(?:.[a-zA-Z0-9_!#$%&’*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:.[a-zA-Z0-9-]+)*$")) {
            return Status.INVALID_EMAIL;
        }
        return Status.VALID;
    }

    public static Status validateBirthDate(LocalDate birthDate) {
        if (birthDate.isBefore(LocalDate.of(1900, 1, 1)) || birthDate.isAfter(LocalDate.now())) {
            return Status.INVALID_BIRTHDATE;
        }
        return Status.VALID;
    }

    public static Status validatePassword(String password) {
        if (password.length() < 3) {
            return Status.INVALID_PASSWORD;
        }
        return Status.VALID;
    }

    public static Status validateForm(String name, String lastName, String email, LocalDate birthDate, String password) {
        int invalidCounter = 0;
        Status status = Status.VALID;
        if (validateName(name) != Status.VALID) {
            status = validateName(name);
            invalidCounter++;
        }
        if (validateLastName(lastName) != Status.VALID) {
            status = validateLastName(lastName);
            invalidCounter++;
        }
        if (validateEmail(email) != Status.VALID) {
            status = validateEmail(email);
            invalidCounter++;
        }
        if (validateBirthDate(birthDate) != Status.VALID) {
            status = validateBirthDate(birthDate);
            invalidCounter++;
        }
        if (validatePassword(password) != Status.VALID) {
            status = validatePassword(password);
            invalidCounter++;
        }
        if (invalidCounter > 1) {
            status = Status.INVALID_SEVERAL;
        }
        return status;
    }
}