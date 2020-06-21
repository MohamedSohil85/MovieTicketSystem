package com.mohamed.movieticketsystem.entities;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
public class Orders implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long OrdersId;
    private int quantity;
    @JsonFormat(pattern = "yyyy-MM-dd" ,timezone = "Europe/Berlin")
    @Temporal(value = TemporalType.DATE)
    private Date createOrders;
    private double totalPrice;
    @ManyToOne
    private RegistretedUser user;
    @ManyToOne
    private Movie movie;
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

    public  RegistretedUser getUser() {
        return user;
    }

    public Movie getMovie() {
        return movie;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public void setUser(RegistretedUser user) {
        this.user = user;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Orders)) return false;
        Orders orders = (Orders) o;
        return getQuantity() == orders.getQuantity() &&
                Objects.equals(getOrdersId(), orders.getOrdersId()) &&
                Objects.equals(getCreateOrders(), orders.getCreateOrders()) &&

                Objects.equals(getUser(), orders.getUser()) &&
                Objects.equals(getMovie(), orders.getMovie());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOrdersId(), getQuantity(), getCreateOrders(),  getUser(), getMovie());
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Orders{");
        sb.append("OrdersId=").append(OrdersId);
        sb.append(", quantity=").append(quantity);
        sb.append(", createOrders=").append(createOrders);
        sb.append(", totalPrice=").append(totalPrice);
        sb.append(", user=").append(user);
        sb.append(", movie=").append(movie);
        sb.append('}');
        return sb.toString();
    }
}
