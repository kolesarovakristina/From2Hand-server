package sk.bazos.to;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

public class AdvertCreateDto implements Serializable{


    private String name;
    private String descr;
    private Long category;
    private Long price;
    private String district;
    private String cityDistrict;
    private MultipartFile photo;



    public MultipartFile getPhoto() {
        return photo; }

    public void setPhoto(MultipartFile photo) {
        this.photo = photo;
    }
    public Long getCategory() {
        return category;
    }

    public void setCategory(Long category) {
        this.category = category;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCityDistrict() {
        return cityDistrict;
    }

    public void setCityDistrict(String cityDistrict) {
        this.cityDistrict = cityDistrict;
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

}
