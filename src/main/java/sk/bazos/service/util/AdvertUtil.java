package sk.bazos.service.util;

import sk.bazos.model.Advert;
import sk.bazos.to.AdvertCreateDto;

public final class AdvertUtil {

    private AdvertUtil() {
    }

    public static Advert fromCreate(AdvertCreateDto advertCreateDto) {
        Advert advert = new Advert();
        advert.setCity(advertCreateDto.getCity());
        advert.setDescr(advertCreateDto.getDescr());
        advert.setName(advertCreateDto.getName());
        advert.setPrice(advertCreateDto.getPrice());
        return advert;
    }
}
