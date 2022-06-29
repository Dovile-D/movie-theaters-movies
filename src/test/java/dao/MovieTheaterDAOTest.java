package dao;

import entity.Movie;
import entity.MovieTheater;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;
import utils.HibernateUtil;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MovieTheaterDAOTest {

    private static SessionFactory sessionFactory;
    private Session session;

    @BeforeAll
    public static void setup() {
        sessionFactory = HibernateUtil.getSessionFactory();
        System.out.println("SessionFactory created");
    }

    @AfterAll
    public static void tearDown() {
        if (sessionFactory != null) sessionFactory.close();
        System.out.println("SessionFactory destroyed");
    }

    @Test
    void testInsert() {
        System.out.println("Running testInsert...");

        session.beginTransaction();

        MovieTheater movietheater = new MovieTheater("Forum cinemas");
        Integer id = (Integer) session.save(movietheater);

        session.getTransaction().commit();

        Assertions.assertTrue(id > 0);
    }

    @Test
    void testUpdate() {
        System.out.println("Running testUpdate...");

        Integer id = 5;
        MovieTheater movieTheater = new MovieTheater(5,"Kankles");

        session.beginTransaction();
        session.update(movieTheater);
        session.getTransaction().commit();

        MovieTheater updatedMovieTheater = session.find(MovieTheater.class, id);

        assertEquals("Kankles", updatedMovieTheater.getName());
    }

    @Test
    void testDelete() {
        System.out.println("Running testDelete...");

        Integer id = 6;
        MovieTheater movieTheater = session.get(MovieTheater.class, id);

        session.beginTransaction();
        session.delete(movieTheater);
        session.getTransaction().commit();

        MovieTheater deletedMovieTheater = session.find(MovieTheater.class, id);

        Assertions.assertNull(deletedMovieTheater);
    }

    @Test
    void testSearchById() {
        System.out.println("Running testSearchById...");

        Integer id = 2;

        MovieTheater movieTheater = session.get(MovieTheater.class, id);

        assertEquals("Cinamon", movieTheater.getName());
    }

    @Test
    void searchByName() {
            System.out.println("Running testSearchAll...");
            String name = "Rom";
            List<MovieTheater> movieTheaters = session.createQuery("FROM MovieTheater a WHERE a.name LIKE '%" + name + "%'").getResultList();

            Assertions.assertFalse(movieTheaters.isEmpty());

    }

    @Test
    void testSearchAll() {
        System.out.println("Running testSearchAll...");
        String name = "Romuva";
        List<MovieTheater> movieTheaters = session.createQuery("FROM MovieTheater").list();

        Assertions.assertFalse(movieTheaters.isEmpty());
    }

    @BeforeEach
    public void openSession() {
        session = sessionFactory.openSession();
        System.out.println("Session created");
    }

    @AfterEach
    public void closeSession() {
        if (session != null) session.close();
        System.out.println("Session closed\n");
    }
}