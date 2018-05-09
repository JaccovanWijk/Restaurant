package com.example.jacco.restaurant;

/**
 * Created by Jacco on 7-5-2018.
 * begin screen 
 */

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class CategoriesActivity extends AppCompatActivity implements CategoriesRequest.Callback{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        CategoriesRequest request = new CategoriesRequest(this);
        request.getCategories(this);
    }

    @Override
    public void gotCategories(ArrayList<String> categories) {

        ArrayAdapter<String> adapter = new CategoriesAdapter(this, categories);

        ListView listview = findViewById(R.id.listview);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new ListClickListener());
    }

    @Override
    public void gotCategoriesError(String message) {

        Context context = getApplicationContext();

        // print message
        int duration = Toast.LENGTH_LONG;
        Toast.makeText(context, message, duration).show();
    }

    private class ListClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            String category = (String) adapterView.getItemAtPosition(i);

            Intent intent = new Intent(CategoriesActivity.this, MenuActivity.class);
            intent.putExtra("clicked_entry", category);
            startActivity(intent);
        }
    }
}
