package sk.bazos.model;

import javax.persistence.*;
import java.util.List;

@Entity

    public class SubCategoryItem {
        @Id
        @GeneratedValue
        private Long id;
        private Long categoryId;
        private String subCategoryTitle;
        @Lob
        private byte[] photoData;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Long getCategoryId() {
        return categoryId;
    }

        public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

        public String getSubCategoryTitle() {
        return subCategoryTitle;
    }

        public void setSubCategoryTitle(String subCategoryTitle) {
        this.subCategoryTitle = subCategoryTitle;
    }

        public byte[] getPhotoData() {
        return photoData;
    }

        public void setPhotoData(byte[] photoData) {
        this.photoData = photoData;
    }
}
