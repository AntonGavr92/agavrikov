package ru.job4j.models;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

/**
 * Created by gavrikov.a on 07/09/2017.
 */
@Entity
@Table(name = "redirect")
public class Redirect {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @ManyToOne
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "short_link")
    private String shortLink;

    @Column(name = "long_link")
    private String longLink;

    @Column(name = "counter")
    private long counter;

    public Redirect() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getShortLink() {
        return shortLink;
    }

    public void setShortLink(String shortLink) {
        this.shortLink = shortLink;
    }

    public String getLongLink() {
        return longLink;
    }

    public void setLongLink(String longLink) {
        this.longLink = longLink;
    }

    public long getCounter() {
        return counter;
    }

    public void setCounter(long counter) {
        this.counter = counter;
    }
}
