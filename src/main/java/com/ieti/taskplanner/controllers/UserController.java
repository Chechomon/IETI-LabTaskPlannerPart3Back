package com.ieti.taskplanner.controllers;

import com.ieti.taskplanner.model.User;
import com.ieti.taskplanner.services.UserService;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
/**
 *
 * @author Sergio
 */
@RestController
@RequestMapping(value = "/Users")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/allUsers")
    public ResponseEntity<?> getUsersList(){
        return new ResponseEntity<>(userService.getUsersList(), HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable(name = "userId") String userId){
        try {
            return new ResponseEntity<>(userService.getUserById(userId),HttpStatus.OK);
        } catch (Exception e) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, e);
            return new ResponseEntity<>("Error usuario no encontrado",HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping ("/{userId}")
    public ResponseEntity<?> deleteUserById(@PathVariable(name = "userId") String userId){
        try {
            userService.removeUser(userId);
            return new ResponseEntity<>("Usuario eliminado",HttpStatus.OK);
        } catch (Exception e) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, e);
            return new ResponseEntity<>("Error usuario no encontrado",HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping
    public ResponseEntity<?> postNewUser(@PathVariable(name = "userId") String userId, @RequestBody User user){
        try {
            userService.createUser(user.getId(),user.getFullname(),user.getEmail(),user.getPassword());
            return new ResponseEntity<>("Usuario agregado",HttpStatus.OK);
        } catch (Exception e) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, e);
            return new ResponseEntity<>("Error usuario no encontrado",HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        try {
            return new ResponseEntity<>(userService.updateUser(user.getId(),user.getFullname(),user.getEmail(),user.getPassword()), HttpStatus.ACCEPTED);
        } catch (Exception ex) {

            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
