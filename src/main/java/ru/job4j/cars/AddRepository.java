package ru.job4j.cars;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.cars.model.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Function;

public class AddRepository implements Store {

    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();

    private SessionFactory sf;

    private AddRepository() {
        sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    private static final class Lazy {
        private static final Store INST = new AddRepository();
    }

    public static Store instOf() {
        return Lazy.INST;
    }

    @Override
    public List<Vacancy> getVacancyToday() {
        return this.tx(session -> session.createQuery("from Vacancy v where v.created >= : date")
                .setParameter("date", Timestamp.valueOf(LocalDateTime.now().minusDays(1))).list());
    }

    @Override
    public List<Advertisement> getAdvertisementToday() {
        return this.tx(session -> session.createQuery("from Advertisement a where a.created >= : date")
                .setParameter("date", Timestamp.valueOf(LocalDateTime.now().minusDays(1))).list());
    }

    @Override
    public List<Advertisement> getBodyTape(String bodyStyle) {
        return this.tx(session -> session.createQuery("select distinct a from Advertisement a "
                        + "join fetch a.car c "
                        + "where c.bodyStyle = :bodyStyle")
                .setParameter("bodyStyle", bodyStyle).list());
    }

    @Override
    public List<Advertisement> getImage() {
        return this.tx(session -> session.createQuery("select distinct a from Advertisement a "
                        + "join fetch a.car c "
                        + "where c.imageURL is not NULL ").list());
    }


    private <T> T tx(final Function<Session, T> command) {
        Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }
}
