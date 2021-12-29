package ru.job4j.cars;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.cars.model.Advertisement;
import ru.job4j.cars.model.Car;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class CarHQL {

    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            /**
             * Код добавления машин и Объявлений в БД
             */

          /*  Car toyota1 = Car.of("Toyota", "Crossower", "https://yandex.ru/images/sea"
                    +  "rch?from=tabbar&text=%D1%84%D0%"
                    + "BE%D1%82%D0%BE%20%D0%BC%D0%B0%D"
                    + "1%88%D0%B8%D0%BD%D1%8B&pos"
                    + "=22&img_url=https%3A%2F%2Fwww.1zoom"
                    + ".me%2Fbig2%2F776%2F269574-svetik.jpg&rpt=simage");

            session.save(toyota1);


            Advertisement four = Advertisement.of("Продажа втророй машины toyota",
                    Timestamp.valueOf(LocalDateTime.now()), toyota1);

            session.save(four);

*/
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}