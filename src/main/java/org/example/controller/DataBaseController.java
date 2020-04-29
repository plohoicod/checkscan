package org.example.controller;

import org.example.service.FirebaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import java.util.concurrent.ExecutionException;

//контроллер для обработки запросов связанных с таблицами
@RestController
public class DataBaseController {
    @Autowired
    FirebaseService firebaseService;

    //обработка запроса для получения названия всех коллекций в БД
    @GetMapping("/getAllCollections")
    public String getAllCoolections() {
        return firebaseService.listCollections();
    }

    //обработка запроса для получения всех документов в коллекции
    @GetMapping("/getAllDocumentsInCollection")
    public String getAllDocumentsInCollection(@RequestHeader() String name) throws InterruptedException, ExecutionException {
        return firebaseService.getAllDocumentsInCollection(name);
    }
}
