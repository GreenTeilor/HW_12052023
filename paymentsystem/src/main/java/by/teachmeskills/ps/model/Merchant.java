package by.teachmeskills.ps.model;


import java.time.LocalDate;
import java.util.UUID;


public class Merchant {
    private String id;
    private String name;
    private LocalDate createdAt;

    //Constructor to add new merchant
    public Merchant(String name) {
        this.id = String.valueOf(UUID.randomUUID());
        this.name = name;
        this.createdAt = LocalDate.now();
    }

    //Constructor to read merchant info from file
    public Merchant(String id, String name, LocalDate createdAt) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getCreationDate() {
        return createdAt;
    }

    @Override
    public String toString() {
        return id + " " + name + " " + createdAt;
    }
}
