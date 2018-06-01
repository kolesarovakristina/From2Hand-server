package sk.bazos.service;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import sk.bazos.model.User;
import sk.bazos.repository.UserRepository;
import sk.bazos.security.JwtTokenProvider;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
@Api
@Transactional(readOnly = true)
public class UserService implements UserDetailsService {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping("login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            return jwtTokenProvider.createToken(username, userRepository.findByUsername(username).getRoles());
        } catch (AuthenticationException e) {
            throw new SecurityException("Invalid username/password supplied");
        }
    }


    @PutMapping("/{id}")
        public User updateUser(@PathVariable(value = "id")Long id,@RequestBody User user) {
        //User userid= (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //User reference = entityManager.getReference(User.class, userid.getId());
        User userToUpdate = userRepository.getOne(id);
        userToUpdate.setUsername(user.getUsername());
        userToUpdate.setEmail(user.getEmail());
        userToUpdate.setPassword(user.getPassword());
        userToUpdate.setPhonenumber(user.getPhonenumber());
        return userRepository.save(userToUpdate);
    }


    @GetMapping("/profile")
    public User getUserProfile(HttpServletRequest req) {
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return userRepository.findByUsername(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));
    }

    @PostMapping
    @Transactional
    public Long createUser(@Valid @RequestBody(required = true) User user) {
        if(userRepository.findByUsername(user.getUsername()) == null ){
            final String password = user.getPassword();
            user.setPassword(encoder.encode(password));
            user.withRole("ROLE_USER");
            return userRepository.save(user).getId();
        }
        throw new RuntimeException("error");
    }

    @PostMapping("admin")
//  @Secured("ROLE_ADMIN")
    @Transactional
    public Long createAdmin(@Valid @RequestBody(required = true) User user) {
        final String password = user.getPassword();
        user.setPassword(encoder.encode(password));
        user.withRole("ROLE_ADMIN");
        return userRepository.save(user).getId();
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
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
