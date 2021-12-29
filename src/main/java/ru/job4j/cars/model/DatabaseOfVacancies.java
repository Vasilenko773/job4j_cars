package ru.job4j.cars.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "data_base")
public class DatabaseOfVacancies {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<Vacancy> vacancySet = new HashSet<>();

    public void addVacancy(Vacancy vacancy) {
        this.vacancySet.add(vacancy);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Vacancy> getVacancySet() {
        return vacancySet;
    }

    public void setVacancySet(Set<Vacancy> vacancySet) {
        this.vacancySet = vacancySet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DatabaseOfVacancies that = (DatabaseOfVacancies) o;
        return id == that.id && Objects.equals(title, that.title) && Objects.equals(vacancySet, that.vacancySet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, vacancySet);
    }

    @Override
    public String toString() {
        return "DatabaseOfVacancies{"
                + "id=" + id
                + ", title='" + title + '\''
                + ", vacancySet=" + vacancySet
                + '}';
    }

    public static DatabaseOfVacancies of(String title) {
        DatabaseOfVacancies db = new DatabaseOfVacancies();
        db.title = title;
        return db;
    }
}
