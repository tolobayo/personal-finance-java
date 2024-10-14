package com.example.finance.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//This acts as the api layer or the routing, defines endpoints for this data type
@RestController
@RequestMapping(path = "api/v1/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping
	public List<User> getUsers() {
        return userService.getUsers();
    }
    //request body maps the payload into the user parameter as a string
    @PostMapping
    public void resgisterNewUser(
        @RequestBody User user) {
        userService.addNewUser(user);
    }

    @DeleteMapping(path= "{userId}")
    public void deleteUser(@PathVariable("userId") Long id) {
        //TODO: Validate that user id is not null
        userService.deleteUser(id);

    }

    @PutMapping(path="{userId}")
    public void updateUser( @PathVariable("userId") Long id, @RequestParam(required = false) String firstName, @RequestParam(required = false) String lastName) {
        //TODO: Add support for updating your email, username, and password
        userService.updateUser(id, firstName, lastName);
    }

}
