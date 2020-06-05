package com.mohamed.movieticketsystem.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class OrderDetails implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long OrderDetailsId;
    @OneToOne
    private Orders orders;
    @OneToOne
    private Movie movie;
    private int quantity;

    public OrderDetails() {
    }

    public Long getOrderDetailsId() {
        return OrderDetailsId;
    }

    public void setOrderDetailsId(Long orderDetailsId) {
        OrderDetailsId = orderDetailsId;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
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
        if (!(o instanceof OrderDetails)) return false;
        OrderDetails that = (OrderDetails) o;
        return getQuantity() == that.getQuantity() &&
                Objects.equals(getOrderDetailsId(), that.getOrderDetailsId()) &&
                Objects.equals(getOrders(), that.getOrders()) &&
                Objects.equals(getMovie(), that.getMovie());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOrderDetailsId(), getOrders(), getMovie(), getQuantity());
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("OrderDetails{");
        sb.append("OrderDetailsId=").append(OrderDetailsId);
        sb.append(", orders=").append(orders);
        sb.append(", movie=").append(movie);
        sb.append(", quantity=").append(quantity);
        sb.append('}');
        return sb.toString();
    }
}
