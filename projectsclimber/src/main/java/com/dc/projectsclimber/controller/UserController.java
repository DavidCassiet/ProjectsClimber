package com.dc.projectsclimber.controller;

import com.dc.projectsclimber.entity.User;
import com.dc.projectsclimber.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.time.LocalDate;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody @Valid User user) {
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers(@RequestParam(name= "city", required = false) String city,
                                         @RequestParam(name= "creationDate", required = false)
                                         @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateTime) {
        return new ResponseEntity<>(userService.getUsers(city, dateTime), HttpStatus.OK);
    }

    @DeleteMapping("/{idUser}")
    public ResponseEntity<?> deleteUser(@PathVariable("idUser") Long idUser,
                                    @RequestParam String name,
                                    @RequestParam String password) {
        if (userService.deleteUser(idUser, name, password)) {
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } else { return new ResponseEntity<>(HttpStatus.BAD_REQUEST); }
    }

    @PutMapping("/{idUser}")
    public ResponseEntity<?> editUser(@PathVariable("idUser") Long idUser,
                                  @RequestBody @Valid User user) {
        return new ResponseEntity<>(userService.editUser(idUser, user), HttpStatus.OK);
    }
}