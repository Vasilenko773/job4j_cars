package ru.job4j.cars.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.*;
import javax.swing.*;

@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String mark;

    private String bodyStyle;

}
