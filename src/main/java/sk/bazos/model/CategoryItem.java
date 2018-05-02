package sk.bazos.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.util.List;

@Entity

public class CategoryItem {
    @Id
    @GeneratedValue
    private Long categoryId;
    private String categoryTitle;
    private String subcategoryTitle;
    @Lob
    private byte[] photoData;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    public String getSubcategoryTitle() {
        return subcategoryTitle;
    }

    public void setSubcategoryTitle(String subcategoryTitle) {
        this.subcategoryTitle = subcategoryTitle;
    }

    public byte[] getPhotoData() {
        return photoData;
    }

    public void setPhotoData(byte[] photoData) {
        this.photoData = photoData;
    }




}
