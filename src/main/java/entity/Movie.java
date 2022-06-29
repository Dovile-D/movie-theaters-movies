package entity;

import javax.persistence.*;

@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "title")
    private String title;
    @Column(name = "rating")
    private String rating;

    @ManyToOne
    @JoinColumn(name = "theaters")
    private MovieTheater movieTheater;

    public Movie(int id, String title, String rating) {
        this.id = id;
        this.title = title;
        this.rating = rating;
    }

    public Movie(String title, String rating) {
        this.title = title;
        this.rating = rating;
    }

    public Movie() {

    }

    public Movie(int id, String title, String rating, MovieTheater movieTheater) {
        this.id = id;
        this.title = title;
        this.rating = rating;
        this.movieTheater = movieTheater;
    }

    public Movie(String title, String rating, MovieTheater movieTheater) {
        this.title = title;
        this.rating = rating;
        this.movieTheater = movieTheater;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public MovieTheater getMovieTheater() {
        return movieTheater;
    }

    public void setMovieTheater(MovieTheater movieTheater) {
        this.movieTheater = movieTheater;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", rating='" + rating + '\'' +
                ", movieTheater=" + movieTheater +
                '}';
    }
}
