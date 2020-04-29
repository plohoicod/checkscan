package org.example.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.example.entity.User;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class FirebaseService {

    //функция для создания или редактировния существующих элементов коллекции users
    public String saveUserDetails(User user) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("users").document(user.getName()).set(user);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    //функция получения данных пользователя
    public User getUserDetails(String name) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection("users").document(name);
        ApiFuture<DocumentSnapshot> future = documentReference.get();

        DocumentSnapshot document = future.get();

        User user = null;

        if(document.exists()) {
            user = document.toObject(User.class);
        }

        return user;
    }

    //функция удаления пользователя
    public String deleteUser(String name) {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        dbFirestore.collection("users").document(name).delete();
        return name + "deleted";
    }

    //функция получения имен всех корневых коллекций
    public String listCollections() {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        Iterable<CollectionReference> future = dbFirestore.listCollections();

        String result = "";
        for (CollectionReference col: future) {
            result += col.getId() + '\n';
        }
        return result;
    }

    //функция получения из конкретной коллекции всех документов и их данных
    public String getAllDocumentsInCollection(String name) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = dbFirestore.collection(name).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        String result = "";
        for (QueryDocumentSnapshot document : documents) {
            result += document.getId() + " => " + document.getData().toString() + '\n';
        }
        return result;
    }
}
