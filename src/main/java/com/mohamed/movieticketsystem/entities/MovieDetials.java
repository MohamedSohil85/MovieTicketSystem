package com.mohamed.movieticketsystem.entities;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class MovieDetials implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long movieDetialsId;
    @NotEmpty(message = "insert Director's Name")
    private String director;
    @NotEmpty(message = "insert Producer's Name")

    private String producer;
    @NotEmpty(message = "insert RunnigTime")
    private String runnigTime;
    @NotEmpty(message = "insert Country")
    private String country;
    @NotEmpty(message = "insert Language")
    private String language;

    @OneToOne(mappedBy = "movieDetials",cascade = CascadeType.ALL)
    private Movie movie;


    public MovieDetials() {
    }

    public Long getMovieDetialsId() {
        return movieDetialsId;
    }

    public void setMovieDetialsId(Long movieDetialsId) {
        this.movieDetialsId = movieDetialsId;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getRunnigTime() {
        return runnigTime;
    }

    public void setRunnigTime(String runnigTime) {
        this.runnigTime = runnigTime;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MovieDetials)) return false;
        MovieDetials that = (MovieDetials) o;
        return Objects.equals(getMovieDetialsId(), that.getMovieDetialsId()) &&
                Objects.equals(getDirector(), that.getDirector()) &&
                Objects.equals(getProducer(), that.getProducer()) &&
                Objects.equals(getRunnigTime(), that.getRunnigTime()) &&
                Objects.equals(getCountry(), that.getCountry()) &&
                Objects.equals(getLanguage(), that.getLanguage()) &&
                Objects.equals(getMovie(), that.getMovie());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMovieDetialsId(), getDirector(), getProducer(), getRunnigTime(), getCountry(), getLanguage(), getMovie());
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("MovieDetials{");
        sb.append("movieDetialsId=").append(movieDetialsId);
        sb.append(", director='").append(director).append('\'');
        sb.append(", producer='").append(producer).append('\'');
        sb.append(", runnigTime='").append(runnigTime).append('\'');
        sb.append(", country='").append(country).append('\'');
        sb.append(", language='").append(language).append('\'');
        sb.append(", movie=").append(movie);
        sb.append('}');
        return sb.toString();
    }
}
