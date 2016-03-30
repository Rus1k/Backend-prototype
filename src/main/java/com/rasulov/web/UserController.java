package com.rasulov.web;

import com.rasulov.model.User;
import com.rasulov.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Controller
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/getUsers", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getListUsers() {
        List<User> users = userService.getAllUsers();
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else
            return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public ResponseEntity<Void> addUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {
        log.info("user {}", user);
        if (userService.isUserNameExist(user.getName())) {
            System.out.println("A user name " + user.getName() + " already exist, please, change user name");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        userService.addUser(user);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/updateUser", method = RequestMethod.PUT)
    public ResponseEntity<User> updateUser(@RequestBody User user) {

        User currentUser = userService.findByUserName(user.getName());

        if (currentUser == null) {
            System.out.println("User with name " + user.getName() + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        currentUser.setActive(user.isActive());
        currentUser.setPassword(user.getPassword());
        //currentUser.setRoles(user.getRoles());

        userService.updateUser(currentUser);
        return new ResponseEntity<>(currentUser, HttpStatus.OK);
    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteUser(@RequestBody User user) {
        User remoteUser = userService.findByUserName(user.getName());

        if (remoteUser == null) {
            System.out.println("User with name " + user.getName() + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userService.deleteUser(user);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

}
