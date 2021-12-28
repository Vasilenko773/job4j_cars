package ru.job4j.cars.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String password;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "users_cars", joinColumns = {
            @JoinColumn(name = "users_id", nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "cars_id", nullable = false, updatable = false)})
    private Set<Car> cars = new HashSet<>();
}
