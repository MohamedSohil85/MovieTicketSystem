package com.mohamed.movieticketsystem.entities;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
public class RegistretedUser implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    @NotEmpty(message = "Enter the Name")
    private String name;
    @NotEmpty(message = "Enter Username")
    private String userName;
    @NotEmpty(message = "Enter the Address")
    private String Address;
    @NotEmpty(message = "Enter your Age")
    private int age;
    @NotEmpty(message = "Enter the Password")
    private String password;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Orders>OrdersList;
//




    public RegistretedUser() {
    }

    public List<Orders> getOrdersList() {
        return OrdersList;
    }

    public void setOrdersList(List<Orders> OrdersList) {
        this.OrdersList = OrdersList;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RegistretedUser)) return false;
        RegistretedUser that = (RegistretedUser) o;
        return getAge() == that.getAge() &&
                Objects.equals(getUserId(), that.getUserId()) &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getUserName(), that.getUserName()) &&
                Objects.equals(getAddress(), that.getAddress()) &&
                Objects.equals(getPassword(), that.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getName(), getUserName(), getAddress(), getAge(), getPassword());
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("RegistretedUser{");
        sb.append("userId=").append(userId);
        sb.append(", name='").append(name).append('\'');
        sb.append(", userName='").append(userName).append('\'');
        sb.append(", Address='").append(Address).append('\'');
        sb.append(", age=").append(age);
        sb.append(", password='").append(password).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
