package sk.bazos.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Entity
public class User implements UserDetails,Serializable {
    //@JsonIgnore
    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    //@JsonIgnore
    private String username;
    @NotNull
    @JsonIgnore
    private String password;
    //@JsonIgnore
    private String phonenumber;
    //@JsonIgnore
    private String email;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER) //TODO update cez @jointable
    //@JsonIgnore
    private List<Role> roles;
    public Long getId() { return id;
    }
    @Override @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @JsonIgnore
    public List<Role> getRoles() {
        return roles;
    }

    @Override
    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonSetter
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }

    @JsonIgnore
    public void setRole(Role role) {
        if (roles == null) {
            roles = new ArrayList<>();
        }
        roles.add(role);
    }

    @JsonIgnore
    public void withRole(String strRole) {
        if (roles == null) {
            roles = new ArrayList<>();
        }
        Role role = new Role();
        role.setAuthority(strRole);
        roles.add(role);
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                ", email='" + email + '\'' +
                ", roles=" + roles +
                '}';
    }
}
