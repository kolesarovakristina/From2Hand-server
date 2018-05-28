package sk.bazos.service.util;

import org.springframework.web.multipart.MultipartFile;
import sk.bazos.model.Advert;
import sk.bazos.model.PhotoAdvert;
import sk.bazos.service.exception.ServiceException;
import sk.bazos.to.AdvertCreateDto;

import java.io.IOException;

public final class AdvertUtil {

    private AdvertUtil() {
    }
    private static PhotoAdvert createPhotoDataFromMultipart(MultipartFile photo) {
        PhotoAdvert photoData = null;
        if (photo != null) {
            try {
                photoData = new PhotoAdvert();
                photoData.setData(photo.getBytes());

            } catch (IOException e) {
                throw new ServiceException("Something terrible happens with photo data", e);
            }
        }
        return photoData;
    }
    public static Advert fromCreate(AdvertCreateDto advertCreateDto) {
        Advert advert = new Advert();
        advert.setDistrict(advertCreateDto.getDistrict());
        advert.setCityDistrict(advertCreateDto.getCityDistrict());
        advert.setDescr(advertCreateDto.getDescr());
        advert.setName(advertCreateDto.getName());
        advert.setPrice(advertCreateDto.getPrice());
        advert.setPhotoAdvert(createPhotoDataFromMultipart(advertCreateDto.getPhoto()));
        return advert;
    }
}
