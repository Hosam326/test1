package com.example.testmcc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText name , phone , address ;
    Button save ;
    FirebaseFirestore db =FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name=findViewById(R.id.name);
        phone=findViewById(R.id.phone);
        address=findViewById(R.id.address);


    }
    public void save(View v){
        String nameTXT = name.getText().toString();
        String phoneTXT = phone.getText().toString();
        String addressTXT = address.getText().toString();

        Map<String,Object> contact = new HashMap<>();
        contact.put("name",nameTXT);
        contact.put("phone",phoneTXT);
        contact.put("address" , addressTXT);

        db.collection("contacts")
                .add(contact)
        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Log.d("Tag","Data Added successfully TO DB :");
            }
        })
        .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("Tag","failed to add to DB"+e);

            }
        });



    }
}