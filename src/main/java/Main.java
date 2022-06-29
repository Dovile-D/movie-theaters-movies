import dao.MovieDAO;
import dao.MovieTheaterDAO;
import entity.Movie;
import entity.MovieTheater;

public class Main {
    public static void main(String[] args) {
        MovieTheater theater = new MovieTheater("Romuva");
        MovieTheater theater1 = new MovieTheater("Cinamon");

        Movie movie = new Movie("Don't look up", "4.8");
        Movie movie1 = new Movie("Talented Mr Ripley", "4.9");
        Movie movie2 = new Movie("Life or Something Like That", "4.6");
        Movie movie3 = new Movie("Intouchables", "4.9");

        //bandysime uzpildyti lenteles duomenimis
        //1. Sukuriami oro uostai
        MovieTheaterDAO.insert(theater);
        MovieTheaterDAO.insert(theater1);

        //2. Darbuotojams priskiriami anksciau sukurti oro uostai
        movie.setMovieTheater(theater);
        movie1.setMovieTheater(theater);
        movie2.setMovieTheater(theater1);
        movie3.setMovieTheater(theater1);

        //3. Darbuotojai issaugojami duomenu bazeje(kartu su oro uostais, kuriuose jie dirba)
        MovieDAO.insert(movie);
        MovieDAO.insert(movie1);
        MovieDAO.insert(movie2);
        MovieDAO.insert(movie3);

        //Bandysim paredaguoti irasus
        theater.setName("Forum Cinemas");
        //norint trinti arba redaguoti butina nurodyti id
        theater.setId(6);
        MovieTheaterDAO.update(theater);

        movie2.setTitle("Burn After Reading");
        movie2.setId(7);
        movie2.setMovieTheater(theater);
        MovieDAO.update(movie2);

        //Bandysime istrinti irasus. Istrynus oro uosta, issitrina taip pat ir visi darbuotojai dirbantys jame, taciau
        //istrynus darbuotoja oro theater neissitrina
        MovieTheaterDAO.delete(6);

        //Bandom istrinti darbuotoja Mariu(movie2)
        MovieDAO.deleteById(9);

        MovieTheaterDAO.insert(theater);

        MovieTheaterDAO.delete(3);

        MovieTheaterDAO.update(theater);

        System.out.println(MovieTheaterDAO.searchById(1));
        System.out.println(MovieTheaterDAO.searchByName("Romuva"));

        System.out.println(MovieTheaterDAO.searchByName("Cinamon"));

        System.out.println(MovieTheaterDAO.searchAll());

        Movie movie4 = new Movie("Titanic", "7.4");
        MovieDAO.insert(movie4);
    }
}
