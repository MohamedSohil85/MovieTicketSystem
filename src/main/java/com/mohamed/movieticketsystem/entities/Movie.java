package com.mohamed.movieticketsystem.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
public class Movie implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long movieId;
    @NotEmpty(message = "Enter Movie'Name")
    private String movieName;
    @JsonFormat(pattern = "yyyy-MM-dd" ,timezone = "Europe/Berlin")
    @Temporal(TemporalType.DATE)
    private Date releaseDate;
    @Enumerated(EnumType.STRING)
    private Genre genre;
    private double price;
    @ManyToOne
    private Orders orders;
    private int rating;
    @OneToOne
    @JsonIgnore
    private MovieDetials movieDetials;
    @OneToMany(mappedBy = "movie",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Actor> starring;

    public Movie() {
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public MovieDetials getMovieDetials() {
        return movieDetials;
    }

    public void setMovieDetials(MovieDetials movieDetials) {
        this.movieDetials = movieDetials;
    }

    public List<Actor> getStarring() {
        return starring;
    }

    public void setStarring(List<Actor> starring) {
        this.starring = starring;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Movie)) return false;
        Movie movie = (Movie) o;
        return Double.compare(movie.getPrice(), getPrice()) == 0 &&
                getRating() == movie.getRating() &&
                Objects.equals(getMovieId(), movie.getMovieId()) &&
                Objects.equals(getMovieName(), movie.getMovieName()) &&
                Objects.equals(getReleaseDate(), movie.getReleaseDate()) &&
                getGenre() == movie.getGenre() &&
                Objects.equals(getOrders(), movie.getOrders()) &&
                Objects.equals(getMovieDetials(), movie.getMovieDetials()) &&
                Objects.equals(getStarring(), movie.getStarring());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMovieId(), getMovieName(), getReleaseDate(), getGenre(), getPrice(), getOrders(), getRating(), getMovieDetials(), getStarring());
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Movie{");
        sb.append("movieId=").append(movieId);
        sb.append(", movieName='").append(movieName).append('\'');
        sb.append(", releaseDate=").append(releaseDate);
        sb.append(", genre=").append(genre);
        sb.append(", price=").append(price);
        sb.append(", orders=").append(orders);
        sb.append(", rating=").append(rating);
        sb.append(", movieDetials=").append(movieDetials);
        sb.append(", starring=").append(starring);
        sb.append('}');
        return sb.toString();
    }
}
