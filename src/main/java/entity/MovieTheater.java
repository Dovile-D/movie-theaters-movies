package entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "theaters")
public class MovieTheater {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    // ????????????????
    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    private List<Movie> movies;


    public MovieTheater(int id, String name, List<Movie> movies) {
        this.id = id;
        this.name = name;
        this.movies = movies;
    }

    public MovieTheater(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public MovieTheater(String name) {
        this.name = name;
    }

    public MovieTheater() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MovieTheater{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", movies=" + movies +
                '}';
    }
}
