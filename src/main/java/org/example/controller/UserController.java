package org.example.controller;

import org.example.entity.User;
import org.example.service.FirebaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import java.util.concurrent.ExecutionException;


//(данный класс был сделан для тестирования)
//контроллер для обработки запросов связанных с редактированием коллекции users
@RestController
public class UserController {
    @Autowired
    FirebaseService firebaseService;

    //обработка запроса получения информации полльзователя
    @GetMapping("/getUserDetails")
    public User getUser(@RequestHeader() String name) throws InterruptedException, ExecutionException {
        return firebaseService.getUserDetails(name);
    }

    //обработка запроса создания полльзователя
    @PostMapping("/createUser")
    public String createUser(@RequestBody User person) throws InterruptedException, ExecutionException {
        return firebaseService.saveUserDetails(person);
    }

    //обработка запроса редактирования полльзователя
    @PutMapping("/updateUser")
    public String changeUser(@RequestBody User person) throws InterruptedException, ExecutionException {
        return firebaseService.saveUserDetails(person);
    }

    //обработка запроса удаления полльзователя
    @DeleteMapping("/deleteUser")
    public String deleteUser(@RequestHeader String name) throws ExecutionException, InterruptedException {
        return firebaseService.deleteUser(name);
    }
}