package ru.job4j.cars.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.*;
import javax.swing.*;
import java.util.Objects;

@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String mark;

    private String bodyStyle;

    private String imageURL;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getBodyStyle() {
        return bodyStyle;
    }

    public void setBodyStyle(String bodyStyle) {
        this.bodyStyle = bodyStyle;
    }

    public static Car of(String mark, String bodyStyle, String imageURL) {
        Car car = new Car();
        car.mark = mark;
        car.bodyStyle = bodyStyle;
        car.imageURL = imageURL;
        return car;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Car car = (Car) o;
        return id == car.id && Objects.equals(mark, car.mark) && Objects.equals(bodyStyle, car.bodyStyle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, mark, bodyStyle);
    }

    @Override
    public String toString() {
        return "Car{"
                + "id=" + id
                + ", mark='" + mark + '\''
                + ", bodyStyle='" + bodyStyle + '\''
                + '}';
    }
}
