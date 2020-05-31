package com.mohamed.movieticketsystem.entities;

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
    @Temporal(value = TemporalType.DATE)
    @DateTimeFormat(pattern = "DD.MM.YYYY HH:MM:SS")
    @NotEmpty(message = "Enter Date of Release")
    private Date releaseDate;
    @Enumerated(value = EnumType.STRING)
    @NotEmpty(message = "Enter the Genre of the Movie")
    private Genre genre;
    @NotEmpty(message = "Enter the Number of Movie")
    @Size(min = 1)
    private int noOfTickets;
    @NotEmpty(message = "Enter the Price")
    private double price;
    @NotEmpty(message = "Enter the Rating")
    @Size(min = 1,max = 10)
    private int rating;
    @OneToOne
    private MovieDetials movieDetials;
    @OneToMany(mappedBy = "movie",cascade = CascadeType.ALL)
    private List<Actor> starring;
    @OneToMany(mappedBy = "movie",cascade = CascadeType.ALL)
    private List<Orders>Orderss;
    public Movie() {
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getNoOfTickets() {
        return noOfTickets;
    }

    public void setNoOfTickets(int noOfTickets) {
        this.noOfTickets = noOfTickets;
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

    public List<Orders> getOrderss() {
        return Orderss;
    }

    public void setOrderss(List<Orders> Orderss) {
        this.Orderss = Orderss;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Movie)) return false;
        Movie movie = (Movie) o;
        return getNoOfTickets() == movie.getNoOfTickets() &&
                Double.compare(movie.getPrice(), getPrice()) == 0 &&
                getRating() == movie.getRating() &&
                Objects.equals(getMovieId(), movie.getMovieId()) &&
                Objects.equals(getMovieName(), movie.getMovieName()) &&
                Objects.equals(getReleaseDate(), movie.getReleaseDate()) &&
                getGenre() == movie.getGenre() &&
                Objects.equals(getMovieDetials(), movie.getMovieDetials()) &&
                Objects.equals(getStarring(), movie.getStarring());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMovieId(), getMovieName(), getReleaseDate(), getGenre(), getNoOfTickets(), getPrice(), getRating(), getMovieDetials(), getStarring());
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Movie{");
        sb.append("movieId=").append(movieId);
        sb.append(", movieName='").append(movieName).append('\'');
        sb.append(", releaseDate=").append(releaseDate);
        sb.append(", genre=").append(genre);
        sb.append(", noOfTickets=").append(noOfTickets);
        sb.append(", price=").append(price);
        sb.append(", rating=").append(rating);
        sb.append(", movieDetials=").append(movieDetials);
        sb.append(", starring=").append(starring);
        sb.append('}');
        return sb.toString();
    }
}
