package com.mohamed.movieticketsystem.entities;

import com.fasterxml.jackson.annotation.JsonFormat;

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
    private RegistretedUser user;

    @JsonFormat(pattern = "yyyy-MM-dd HH:MM:SS" ,timezone = "Europe/Berlin")
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



    public RegistretedUser getUser() {
        return user;
    }

    public void setUser(RegistretedUser user) {
        this.user = user;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Orders)) return false;
        Orders Orders = (Orders) o;
        return
                Objects.equals(getOrdersId(), Orders.getOrdersId()) &&
                Objects.equals(getUser(), Orders.getUser()) &&
                Objects.equals(getCreateOrders(), Orders.getCreateOrders());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOrdersId(),  getUser(), getCreateOrders());
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Orders{");
        sb.append("OrdersId=").append(OrdersId);

        sb.append(", user=").append(user);
        sb.append(", createOrders=").append(createOrders);
        sb.append('}');
        return sb.toString();
    }
}
