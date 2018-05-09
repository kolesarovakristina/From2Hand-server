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
    //private List<Photo>photos;

    @ManyToOne
    @JsonIgnore
    private Category parent;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Category> subcategories = new ArrayList<>();

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

    //public List<Photo> getPhotos(){return photos;}

    //public void setPhotos (List<Photo> photos){this.photos=photos;}

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

}


