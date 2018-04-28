package sk.bazos.service;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sk.bazos.model.Add;
import sk.bazos.repository.AddRepository;

import java.util.List;

/**
 * Created by martin.cuchran on 4/10/2018.
 */
@RestController
@RequestMapping("add")
@Api(value = "add", description = "Basic crud over add entity.")
public class AddService {

    @Autowired
    private AddRepository addRepository;

    @PostMapping(consumes = "application/json")
    public Long createAdd(@RequestBody(required = true) Add add) {
        if (add.getId() != null) {
            add.setId(null);
        }
        return addRepository.save(add).getId();
    }

    @GetMapping(produces = "application/json")
    public List<Add> getAdds() {
        return addRepository.findAll();
    }
}
