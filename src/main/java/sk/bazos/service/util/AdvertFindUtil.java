package sk.bazos.service.util;

import sk.bazos.model.Advert;
import sk.bazos.to.AdvertCreateDto;
import sk.bazos.to.AdvertFindDto;

public class AdvertFindUtil {

    public static Advert fromCreate(AdvertFindDto advertFindDto) {
        Advert advert = new Advert();
        advert.setDistrict(advertFindDto.getCity());
        advert.setDescr(advertFindDto.getDescription());
        return advert;
    }
}