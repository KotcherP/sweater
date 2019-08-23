package com.example.sweater.domain;

import javax.persistence.*;
import java.util.Set;

@Entity //аннотация для создания в БД таблицы по данным объектов класса
@Table(name = "usr") //назвать таблицу не по-умолчанию user,а usr
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String userName;
    private String password;
    private boolean active;

    //аннотация для формирования доп таблицы для хранения enum.Fetch = жадная,либо ленивая подкгрузка данных.Жадная лучше, когда мало данных
    @ElementCollection(targetClass = Role.class,fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role",joinColumns = @JoinColumn(name = "user_id")) //описывает,что данное поле хранится в отдельное таблицы,не описывали mapping
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
