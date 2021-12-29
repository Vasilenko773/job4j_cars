package ru.job4j.integral;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.integral.model.Order;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class OrdersStoreTest {

    private BasicDataSource pool = new BasicDataSource();

    @Before
    public void setUp() throws SQLException {
        pool.setDriverClassName("org.hsqldb.jdbcDriver");
        pool.setUrl("jdbc:hsqldb:mem:tests;sql.syntax_pgs=true");
        pool.setUsername("sa");
        pool.setPassword("");
        pool.setMaxTotal(2);
        StringBuilder builder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream("./db/scripts/update_002.sql")))
        ) {
            br.lines().forEach(line -> builder.append(line).append(System.lineSeparator()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        pool.getConnection().prepareStatement(builder.toString()).executeUpdate();
    }

    @Test
    public void whenSaveOrderAndFindAllOneRowWithDescription() {
        OrdersStore store = new OrdersStore(pool);

        store.save(Order.of("name1", "description1"));

        List<Order> all = (List<Order>) store.findAll();

        assertThat(all.size(), is(1));
        assertThat(all.get(0).getDescription(), is("description1"));
        assertThat(all.get(0).getId(), is(1));
    }


    @Test
    public void whenSaveAndFindById() {
        OrdersStore store = new OrdersStore(pool);

        store.save(Order.of("name2", "description2"));

        Order order = store.findById(1);

        assertThat(order.getName(), is("name2"));
        assertThat(order.getDescription(), is("description2"));
    }

    @Test
    public void whenSaveAndFindByName() {
        OrdersStore store = new OrdersStore(pool);

        store.save(Order.of("name3", "description3"));
        Order order = Order.of("name4", "description4");
        store.save(order);

        Order rsl = store.findByName("name4");

        assertThat(rsl, is(order));
    }


    @Test
    public void whenSaveAndUpdateOrder() {
        OrdersStore store = new OrdersStore(pool);

        store.save(Order.of("name5", "description5"));
        Order order = Order.of("name6", "description6");

        assertThat(store.update(1, order), is(true));
        assertThat(store.findById(1).getDescription(), is("description6"));
    }

    @After
    public void close() {
        try {
            pool.getConnection().prepareStatement("drop table orders").executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}