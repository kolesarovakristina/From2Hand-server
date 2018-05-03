package sk.bazos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sk.bazos.model.User;
import sk.bazos.repository.UserRepository;

import javax.annotation.PostConstruct;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @PostConstruct
    public void init() {
        User u = new User();
        u.setUsername("Admin");
        u.setPassword("");

        userRepository.save(u);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        final User byUsername = userRepository.findByUsername(s);
        if (byUsername == null) {
            throw new UsernameNotFoundException("User not found for username:" + s);
        }
        return byUsername;
    }
}
