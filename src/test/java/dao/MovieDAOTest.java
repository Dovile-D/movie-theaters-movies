package dao;

import entity.Movie;
import entity.MovieTheater;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;
import utils.HibernateUtil;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MovieDAOTest {

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
    public void testInsert() {
        System.out.println("Running testInsert...");

        session.beginTransaction();

        Movie movie = new Movie("Green Book", "8.95", new MovieTheater(1, "Romuva"));
        Integer id = (Integer) session.save(movie);

        session.getTransaction().commit();

        Assertions.assertTrue(id > 0);
    }

    @Test
    public void testUpdate() {
        System.out.println("Running testUpdate...");

        Integer id = 13;
        Movie movie = new Movie(13,"Titanic", "3.7", new MovieTheater(2, "Cinamon"));

        session.beginTransaction();
        session.update(movie);
        session.getTransaction().commit();

        Movie updatedMovie = session.find(Movie.class, id);

        assertEquals("Titanic", updatedMovie.getTitle());
    }

    @Test
    public void testSearchById() {
        System.out.println("Running testSearchById...");

        Integer id = 8;

        Movie movie = session.get(Movie.class, id);

        assertEquals("Green Book", movie.getTitle());
    }

    @Test
    public void testSearchAll() {
        System.out.println("Running testSearchAll...");
        String name = "Green Book";
        List<Movie> movies = session.createQuery("FROM Movie").list();

        Assertions.assertFalse(movies.isEmpty());
    }

    @Test
    public void testDeleteById() {
        System.out.println("Running testDelete...");

        Integer id = 14;
        Movie movie = session.get(Movie.class, id);

        session.beginTransaction();
        session.delete(movie);
        session.getTransaction().commit();

        Movie deletedMovie = session.find(Movie.class, id);

        Assertions.assertNull(deletedMovie);
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