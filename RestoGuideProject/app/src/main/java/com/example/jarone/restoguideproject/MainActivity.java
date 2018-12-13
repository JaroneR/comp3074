package com.example.jarone.restoguideproject;

import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<String> listItem;
    private ArrayAdapter<String> adapter;

    Button home, search, about, add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, AddResto.class);
                startActivity(i);
            }
        });

        findViewById(R.id.btnAbout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, About.class);
                startActivity(i);
            }
        });




        final ResturantDbHelper dbHelper = new ResturantDbHelper(this);
        final SQLiteDatabase db = dbHelper.getWritableDatabase();

        listItem = new ArrayList<>();
        listView = findViewById(R.id.listview);
        long rows = DatabaseUtils.queryNumEntries(db, ResturantContract.PostEntry.TABLE_NAME);
        if (rows > 0) {
            Cursor cursor = db.rawQuery(String.format("select * from %s", ResturantContract.PostEntry.TABLE_NAME), null);
            if (cursor.moveToFirst()) {
                while (!cursor.isAfterLast()) {
                    int index = cursor.getInt(0);

                    listItem.add(dbHelper.getPost(db, index).getName());
                    cursor.moveToNext();
                }
            }
            cursor.close();
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listItem);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = listView.getItemAtPosition(position).toString();
                Intent i = new Intent(view.getContext(), ViewResto.class);
                i.putExtra("resturant", dbHelper.getPost(db, ((TextView)view).getText().toString()));
                startActivityForResult(i, CODE);
            }
        });
    }
    public static final int CODE = 1;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == CODE) {
            if (resultCode == RESULT_OK)
                this.recreate();
        }
    }
}
