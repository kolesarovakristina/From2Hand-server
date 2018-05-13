package sk.bazos.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@NamedQuery(name = "findByCategoryId",
        query = "SELECT a from Advert a join fetch a.category c join fetch c.parent cp where c.id=:categoryId or cp.id=:categoryId")
public class Advert {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JsonIgnore
    private User user;
    private String name;
    private String descr;
    @ManyToOne
    @JsonIgnore
    private Category category;
    private Long price;
    private String city;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
