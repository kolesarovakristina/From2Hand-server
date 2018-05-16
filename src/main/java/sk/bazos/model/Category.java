package sk.bazos.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Category implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Photo photo;

    @ManyToOne
    @JsonIgnore
    private Category parent;

    @OneToMany(fetch = FetchType.LAZY) //todo bud pouzit jointable
    private List<Category> subcategories = new ArrayList<>();

    @OneToMany(mappedBy = "category")
    private List<Advert> adverts;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Category getParent() {return parent;}

    public void setParent(Category parent) {
        this.parent = parent;
    }

    public List<Category> getSubcategories() {
        return subcategories;
    }

    public void addSubcategory(Category subcategory) {
        subcategories.add(subcategory);
        subcategory.setParent(this);
    }

    public List<Advert> getAdverts() {
        return adverts;
    }

    public void setAdverts(List<Advert> adverts) {
        this.adverts = adverts;
    }

    public void setSubcategories(List<Category> subcategories) {
        this.subcategories = subcategories;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }
}


