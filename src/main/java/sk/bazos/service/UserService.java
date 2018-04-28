package sk.bazos.service;


import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sk.bazos.model.User;
import sk.bazos.repository.UserRepository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@Api(value = "user", description = "User.")

public class UserService {
    @Autowired
    private UserRepository userRepository;

    //Create User

    @PostMapping(consumes = "application/json",produces = "application/json")
    public Long createUser(@RequestBody User user) throws IOException {
        return userRepository.save(user).getId();
    }

    //Delete User

    @DeleteMapping("/deleteUser/{id}")
    public void deleteOwnAdvert(@PathVariable(value = "id")Long id){
        User user=userRepository.getOne(id);

        userRepository.delete(user);
    }

    //Update User

    @PutMapping("/updateUser/{id}")
    public User updateAdvert(@PathVariable(value = "id")Long id, @RequestBody User userDetails ){

        User userAdvertUpdate= userRepository.getOne(id);

        userAdvertUpdate.setEmail(userDetails.getEmail());
        userAdvertUpdate.setStatus(userDetails.getEmail());
        userAdvertUpdate.setPassword(userDetails.getPassword());
        userAdvertUpdate.setName(userDetails.getName());
        userAdvertUpdate.setPhonenumber(userDetails.getPhonenumber());
        userAdvertUpdate.setStatus(userDetails.getStatus());
        if (userDetails.getPhotoData()!=null){
            userAdvertUpdate.setPhotoData(userDetails.getPhotoData());
        }
        return userRepository.save(userAdvertUpdate);
    }

    //All Category

    @GetMapping(value = "/userGetAllCategories",produces = "application/json")
    public List<User> getAllCategories(){

        return userRepository.findAll();
    }

    //One Category

    @GetMapping("/userGetOneCategory/{id}")
    public Optional<User> getCategoryById (@PathVariable(value = "id")Long userId){
        Optional<User> user = userRepository.findById(userId);
        return user;
    }

    //All Adverts

    @GetMapping(value = "/userGetAllAdverts",produces = "application/json")
    public List<User> getAllAdverts(){

        return userRepository.findAll();
    }

    //One Advert

    @GetMapping("/userGetOneAdvert/{id}")
    public Optional<User> getAdvertById (@PathVariable(value = "id")Long userId){
        Optional<User> user = userRepository.findById(userId);
        return user;
    }
}
