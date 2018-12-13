package com.example.jarone.restoguideproject;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import java.util.regex.Pattern;

public class AddResto extends AppCompatActivity {
    Button add, back;
    EditText nameField, addressField, phoneField, descField, tagsField;
    RatingBar rating_bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_resto);

        final ResturantDbHelper dbHelper = new ResturantDbHelper(this);
        final SQLiteDatabase db = dbHelper.getWritableDatabase();

        back = findViewById(R.id.btnBack);
        add = findViewById(R.id.btnAdd2);
        nameField = findViewById(R.id.etNameUpdate);
        addressField = findViewById(R.id.etAddressUpdate);
        phoneField = findViewById(R.id.etPhoneUpdate);
        descField = findViewById(R.id.etDescUpdate);
        tagsField = findViewById(R.id.etTagsUpdate);
        rating_bar = findViewById(R.id.ratingBarUpdate);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phonenumber = "0";
                if(phoneField.getText().toString().isEmpty()) { }
                else {
                    phonenumber = phoneField.getText().toString();
                    if (!Pattern.compile("[0-9]{1,10}").matcher(phonenumber).matches()) {
                        Toast.makeText(AddResto.this, "Invalid Phone Number", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }

                String name = nameField.getText().toString();
                String address = addressField.getText().toString();
                String description = descField.getText().toString();
                String tags = tagsField.getText().toString();
                float rating = rating_bar.getRating();
                if(!name.equals("") || !address.equals("") || !tags.equals("")){
                    dbHelper.addPost(db, new Resturant(name, address, phonenumber, description, tags, rating));
                    Toast.makeText(AddResto.this, "Data added", Toast.LENGTH_SHORT).show();
                    nameField.setText("");
                    addressField.setText("");
                    phoneField.setText("");
                    descField.setText("");
                    tagsField.setText("");
                    rating_bar.setRating(0);
                } else {
                    Toast.makeText(AddResto.this, "Data not added", Toast.LENGTH_SHORT).show();
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AddResto.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

}
