package sk.bazos.model;

import javax.persistence.*;
import java.util.List;

@Entity

    public class SubCategoryItem {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @OneToMany(cascade = CascadeType.ALL)
        private Long id;
        private String subCategoryTitle;
        private List<SubCategoryItem>subCategory;
        @Lob
        private byte[] photoData;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getSubCategoryTitle() {
        return subCategoryTitle;
    }

        public void setSubCategoryTitle(String subCategoryTitle) {
        this.subCategoryTitle = subCategoryTitle;
    }

        public List<SubCategoryItem> getSubCategory(){return subCategory;}

        public void setSubCategory (List<SubCategoryItem>subCategory ){this.subCategory = subCategory;}

        public byte[] getPhotoData() {
        return photoData;
    }

        public void setPhotoData(byte[] photoData) {
        this.photoData = photoData;
    }
}
