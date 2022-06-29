package dao;

import entity.MovieTheater;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class MovieTheaterDAO {

    public static void insert(MovieTheater movieTheater) {

        //bet kokiam CRUD veiksmui atlikti butina transaction(ivykdymas)
        Transaction transaction = null;

        //bet kokiam CRUD veiksmui atlikti butina nauja sesija
        //per aplikacijos veikima visada viena sesiju gamykla ir daug sesiju
        Session session = HibernateUtil.getSessionFactory().openSession();

        //kai gauname sesija, galime pradeti transaction
        transaction = session.beginTransaction();

        //saugojamas oro uosto objektas
        session.save(movieTheater); //nereikia rasyti zemo lygio uzklausu kodo, koks buvo rasytas per JDBC

        //transaction ivykdymas
        transaction.commit();
    }

    public static void update(MovieTheater movieTheater) {
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        session.update(movieTheater);
        transaction.commit();
    }

    public static void delete(int id) {
        Transaction transaction = null;
        MovieTheater movieTheater = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        movieTheater = session.get(MovieTheater.class, id);
        session.delete(movieTheater);
        transaction.commit();
    }

    public static MovieTheater searchById(int id) {
        Transaction transaction = null;
        MovieTheater movieTheater = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        movieTheater = session.get(MovieTheater.class, id);
        transaction.commit();

        return movieTheater;
    }

    public static List<MovieTheater> searchByName(String name) {
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        List<MovieTheater> movieTheaters = session.createQuery("FROM MovieTheater a WHERE a.name LIKE '%" + name + "%'").getResultList();
        transaction.commit();

        return movieTheaters;
    }

    public static List<MovieTheater> searchAll() {
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        List<MovieTheater> movieTheaters = null;
        movieTheaters = session.createQuery("FROM MovieTheater").list();
        transaction.commit();

        return movieTheaters;
    }
}
