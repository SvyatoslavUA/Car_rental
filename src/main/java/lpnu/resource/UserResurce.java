package lpnu.resource;

import lpnu.entity.User;
import lpnu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserResurce {
    @Autowired
    private UserService userService;


    @GetMapping("/users")
    public List<User> getAllUsers(){



        final User user1 = new User(1,"name1", "surname1","email1");
        final User user2 = new User(2,"name2", "surname2","email2");

        return List.of(user1, user2);
    }

    @GetMapping("/users/{id}/{email}")
    public User getUserById(@PathVariable long id, @PathVariable String email){

        final User user1 = new User(1,"name1", "surname1","email1");
        final User user2 = new User(2,"name2", "surname2","email2");

        return List.of(user1, user2).stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElse(null);
    }


    @PostMapping("/users")
    public User saveUser(@RequestBody User user){
        user.setId(10);

        return user;
    }
}
