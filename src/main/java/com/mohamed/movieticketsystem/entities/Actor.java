package com.mohamed.movieticketsystem.entities;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
public class Actor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long actorId;
    @NotEmpty(message = "Name must not be Empty")
    private String actorName;
    @NotEmpty(message = "Country must not be empty")
    private String country;
    @ManyToOne
    private Movie movie;

    public Actor() {
    }

    public Long getActorId() {
        return actorId;
    }

    public void setActorId(Long actorId) {
        this.actorId = actorId;
    }

    public String getActorName() {
        return actorName;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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
        if (!(o instanceof Actor)) return false;
        Actor actor = (Actor) o;
        return Objects.equals(getActorId(), actor.getActorId()) &&
                Objects.equals(getActorName(), actor.getActorName()) &&
                Objects.equals(getCountry(), actor.getCountry()) &&
                Objects.equals(getMovie(), actor.getMovie());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getActorId(), getActorName(), getCountry(), getMovie());
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Actor{");
        sb.append("actorId=").append(actorId);
        sb.append(", actorName='").append(actorName).append('\'');
        sb.append(", country='").append(country).append('\'');
        sb.append(", movie=").append(movie);
        sb.append('}');
        return sb.toString();
    }
}
