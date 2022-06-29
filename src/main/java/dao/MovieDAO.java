package dao;

import entity.Movie;
import entity.MovieTheater;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtil;

import java.util.List;

public class MovieDAO {

    public static void insert(Movie movie) {
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        session.save(movie);
        transaction.commit();
    }

    public static Movie searchById(int id) {
        Transaction transaction = null;
        Movie movie = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        movie = session.get(Movie.class, id);
        transaction.commit();

        return movie;
    }

    public static void deleteById(int id) {
        Transaction transaction = null;
        Movie movie = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        movie = session.get(Movie.class, id);
        session.delete(movie);
        transaction.commit();
    }

    public static void update(Movie movie) {
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        session.update(movie);
        transaction.commit();
    }

    public static List<Movie> searchByName(String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<Movie> employees = session.createQuery("FROM Movie e WHERE e.title = " + name).getResultList();
        transaction.commit();

        return employees;
    }

    public static List<Movie> searchAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<Movie> movies = null;
        movies = session.createQuery("FROM Movie").list();

        return movies;
    }
}
