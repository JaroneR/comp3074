package com.example.jarone.restoguideproject;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class EditResto extends AppCompatActivity {
    EditText etName, etAddress, etDescription, etTags, etPhone;
    RatingBar rbRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_resto);

        final ResturantDbHelper dbHelper = new ResturantDbHelper(this);
        final SQLiteDatabase db = dbHelper.getWritableDatabase();

        final Intent i = getIntent();
        final int id = i.getIntExtra("id",0);
        String name = i.getStringExtra("name");
        String address = i.getStringExtra("address");
        final String phone = i.getStringExtra("phone");
        String description = i.getStringExtra("description");
        String tags = i.getStringExtra("tags");
        float rating = i.getFloatExtra("rating",0);

        etName = findViewById(R.id.etNameUpdate);
        etAddress = findViewById(R.id.etAddressUpdate);
        etPhone = findViewById(R.id.etPhoneUpdate);
        etDescription = findViewById(R.id.etDescUpdate);
        etTags = findViewById(R.id.etTagsUpdate);
        rbRating = findViewById(R.id.ratingBarUpdate);

        etName.setText(name);
        etAddress.setText(address);
        etPhone.setText(phone);
        etDescription.setText(description);
        etTags.setText(tags);
        rbRating.setRating(rating);

        findViewById(R.id.btnUpdate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                String address = etAddress.getText().toString();

                String phone = "0";
                if (etPhone.getText().toString().isEmpty()) {
                } else {
                    phone = etPhone.getText().toString();
                    if (!Pattern.compile("[0-9]{1,10}").matcher(phone).matches()) {
                        Toast.makeText(EditResto.this, "Invalid Phone Number", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }

                String description = etDescription.getText().toString();
                String tags = etTags.getText().toString();
                float rating = rbRating.getRating();

                if (!name.equals("") || !address.equals("") || !tags.equals("")) {
                    dbHelper.updatePost(db, new Resturant(name, address, phone, description, tags, rating), id);
                    Toast.makeText(EditResto.this, "Data was updated", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(EditResto.this, "Data was not updated", Toast.LENGTH_SHORT).show();
                }
                setResult(RESULT_OK);
                finish();
            }
        });

        findViewById(R.id.btnBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}