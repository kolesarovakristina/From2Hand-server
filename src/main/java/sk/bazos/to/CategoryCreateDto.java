package sk.bazos.to;

import org.springframework.web.multipart.MultipartFile;


public class CategoryCreateDto {
    private String title;
    private MultipartFile photo;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public MultipartFile getPhoto() {
        return photo;
    }

    public void setPhoto(MultipartFile photo) {
        this.photo = photo;
    }
}
