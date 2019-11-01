package com.project.balmer.dnd.repository;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class FirebaseUtil {

    private static FirebaseUtil firebaseUtil;
    private static FirebaseFirestore db;
    private static DocumentReference dr;

    private FirebaseUtil(){}

    public static void openReference(String ref){
        if (firebaseUtil == null){
            firebaseUtil = new FirebaseUtil();
            db = FirebaseFirestore.getInstance();
        }

        dr = db.document(ref);

    }
}
