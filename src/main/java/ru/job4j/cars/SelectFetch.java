package ru.job4j.cars;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import ru.job4j.cars.model.Candidate;
import ru.job4j.cars.model.DatabaseOfVacancies;
import ru.job4j.cars.model.Vacancy;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public class SelectFetch {

    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            List<Candidate> list = session.createQuery(
                            "select distinct c from Candidate c "
                                    + "join fetch c.database db "
                                    + "join fetch db.vacancySet v "
                                    + "where c.id = :id", Candidate.class)
                    .setParameter("id", 5).list();

            for (Candidate c : list) {
                System.out.println(c);
            }

/**
 * Код сохранения данных в Баззу данных, по средством HQL и СУБД
 */
            /*Vacancy first = Vacancy.of("Помошник руководителя",
                    45000, Timestamp.valueOf(LocalDateTime.now()));

            Vacancy second = Vacancy.of("Инженер",
                    75000, Timestamp.valueOf(LocalDateTime.now()));

            Vacancy third = Vacancy.of("Програмист",
                    110000, Timestamp.valueOf(LocalDateTime.now()));

            session.save(first);
            session.save(second);
            session.save(third);

            DatabaseOfVacancies database = DatabaseOfVacancies.of("Новая база вакансий");
            database.addVacancy(first);
            database.addVacancy(second);
            database.addVacancy(third);
            session.save(database);

            Candidate candidate = Candidate.of("Roman", 5, 100000, database);
            session.save(candidate);

            session.getTransaction().commit();*/
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}