package ru.job4j.cars.model;


import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "vacancies")
public class Vacancy {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String description;

    private int payment;

    private Timestamp created;

    public static Vacancy of(String description, int payment, Timestamp created) {
        Vacancy vacancy = new Vacancy();
        vacancy.description = description;
        vacancy.payment = payment;
        vacancy.created = created;
        return vacancy;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPayment() {
        return payment;
    }

    public void setPayment(int payment) {
        this.payment = payment;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Vacancy vacancy = (Vacancy) o;
        return id == vacancy.id && payment == vacancy.payment && Objects.equals(description, vacancy.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, payment);
    }

    @Override
    public String toString() {
        return "Vacancy{"
                + "id=" + id
                + ", description='" + description + '\''
                + ", payment=" + payment
                + ", created=" + created
                + '}';
    }
}
