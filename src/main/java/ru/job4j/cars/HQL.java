package ru.job4j.cars;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import ru.job4j.cars.model.Candidate;

import java.util.List;

public class HQL {

    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            /**
             * Получение всех кандидатов из БД
             */
            List<Candidate> list = session.createQuery("from Candidate").list();
            for (Candidate c : list) {
                System.out.println(c);
            }

            /**
             * Получение кандидата по полю :id
             */
            Query query = session.createQuery("from Candidate c where c.id = 3");
            System.out.println(query.uniqueResult());

            /**
             * Получение кандидата по полю :name
             */

            Query candidateN = session.createQuery("from Candidate c where c.name = :name").setParameter("name", "Ivan");
            System.out.println(candidateN.uniqueResult());

            /**
             * Обновление записи кандидата
             */
            Query update = session.createQuery("update Candidate c set c.name = :name, c.salary = :salary where c.id = :id")
                    .setParameter("name", "Ashot")
                    .setParameter("salary", 150000)
                    .setParameter("id", 2);
            System.out.println(update.executeUpdate());

            /**
             * Удаление кандидата из БД по полю :id
             */

            session.createQuery("delete from Candidate where id = :id")
                    .setParameter("id", 4)
                    .executeUpdate();

            for (Candidate c : list) {
                System.out.println(c);
            }

            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}