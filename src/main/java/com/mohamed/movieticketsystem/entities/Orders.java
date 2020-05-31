package com.mohamed.movieticketsystem.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
public class Orders implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long OrdersId;
    @ManyToOne
    private Movie movie;
    @ManyToOne
    private RegistretedUser user;
    private double sum;
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date createOrders;
    public Orders() {
    }

    public Date getCreateOrders() {
        return createOrders;
    }

    public void setCreateOrders(Date createOrders) {
        this.createOrders = createOrders;
    }

    public Long getOrdersId() {
        return OrdersId;
    }

    public void setOrdersId(Long OrdersId) {
        this.OrdersId = OrdersId;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public RegistretedUser getUser() {
        return user;
    }

    public void setUser(RegistretedUser user) {
        this.user = user;
    }

    public double getSum() {
        return movie.getNoOfTickets()*movie.getPrice();
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Orders)) return false;
        Orders Orders = (Orders) o;
        return Double.compare(Orders.getSum(), getSum()) == 0 &&
                Objects.equals(getOrdersId(), Orders.getOrdersId()) &&
                Objects.equals(getMovie(), Orders.getMovie()) &&
                Objects.equals(getUser(), Orders.getUser()) &&
                Objects.equals(getCreateOrders(), Orders.getCreateOrders());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOrdersId(), getMovie(), getUser(), getSum(), getCreateOrders());
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Orders{");
        sb.append("OrdersId=").append(OrdersId);
        sb.append(", movie=").append(movie);
        sb.append(", user=").append(user);
        sb.append(", sum=").append(sum);
        sb.append(", createOrders=").append(createOrders);
        sb.append('}');
        return sb.toString();
    }
}
