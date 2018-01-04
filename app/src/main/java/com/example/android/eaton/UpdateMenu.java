package com.example.android.eaton;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;
import java.util.List;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class UpdateMenu extends AppCompatActivity {

    private ImageButton mfoodImage;
    private static final int GALLREQ = 1;
    EditText mID, mname, mprice;
    Button mInsert;
    private Uri uri = null;
    private String name;

    // Storage reference to Firebase
    private StorageReference storageReference = FirebaseStorage.getInstance().getReference("Item Pics");

    // Database reference to Firebase
    FirebaseDatabase myRef = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_menu);

        mID = (EditText) findViewById(R.id.ID_text);
        mname = (EditText) findViewById(R.id.name_text);
        mprice = (EditText) findViewById(R.id.price_text);
        mInsert = (Button) findViewById(R.id.insert);
//        storagereference = FirebaseStorage.getInstance().getReference("Item");





        Button toStart = (Button) findViewById(R.id.previous);
        toStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goStart = new Intent(UpdateMenu.this, MainActivity.class);
                startActivity(goStart);
            }
        });}


        // On Insert Click Data Store in Firebase:


    public void imageButtonClicked(View view){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, GALLREQ);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == GALLREQ && resultCode == RESULT_OK){
            uri = data.getData();
            mfoodImage = (ImageButton) findViewById(R.id.image_button);
            mfoodImage.setImageURI(uri);
        }
    }



    public void onRadioButtonClicked(View view) {

        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.pizza_check:
                if (checked){
                    addItem("Pizza");
                }
                break;
            case R.id.burger_check:
                if (checked) {
                    addItem("Burger");
                }
                break;
            case R.id.drinks_check:
                if (checked){
                    addItem("Drinks");
                }
                break;
            case R.id.dessert_check:
                if (checked) {
                    addItem("Dessert");
                }
                break;
        }



        // Firebase insert ends here

    }


    public void addItem(String category) {
         final String abc = category;

         //String id = mID.getText().toString().trim();
         //String name = mname.getText().toString().trim();
         //String price = mprice.getText().toString().trim();

       // if (!TextUtils.isEmpty(id) && !TextUtils.isEmpty(name) && !TextUtils.isEmpty(price)){
            Button insert = (Button) findViewById(R.id.insert);
            insert.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    StorageReference filepath = storageReference.child(uri.getLastPathSegment());
                    filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            String id = mID.getText().toString().trim();
                            String name = mname.getText().toString().trim();
                            String price = mprice.getText().toString().trim();
                            Uri downloadurl = taskSnapshot.getDownloadUrl();
                            String imageURI = downloadurl.toString();
                            final MenuItem itemClass = new MenuItem(id, name, price, imageURI);
                            Toast.makeText(UpdateMenu.this, "Item Added", Toast.LENGTH_LONG).show();
                            final DatabaseReference newItem = myRef.getReference(abc);
                            newItem.push().setValue(itemClass);
                        }
                    });

                }
            });

   // else
            //Toast.makeText(UpdateMenu.this, "Fill all fields", Toast.LENGTH_LONG).show();



    }




}
