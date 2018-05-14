package sk.bazos.service.util;

import org.springframework.web.multipart.MultipartFile;
import sk.bazos.model.Category;
import sk.bazos.model.Photo;
import sk.bazos.service.exception.ServiceException;
import sk.bazos.to.CategoryCreateDto;

import java.io.IOException;

/**
 * Created by martin.cuchran on 5/14/2018.
 */
public final class CategoryUtil {
    private CategoryUtil() {
    }

    private static Photo createPhotoDataFromMultipart(MultipartFile photo) {
        Photo photoData = null;
        if (photo != null) {
            try {
                photoData = new Photo();
                photoData.setData(photo.getBytes());

            } catch (IOException e) {
                throw new ServiceException("Something terrible happens with photo data", e);
            }
        }
        return photoData;
    }

    public static Category fromCreate(CategoryCreateDto categoryCreateDto) {
        Category category = new Category();
        category.setTitle(categoryCreateDto.getTitle());
        category.setPhoto(createPhotoDataFromMultipart(categoryCreateDto.getPhoto()));
        return category;
    }
}
