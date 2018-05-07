package com.example.jacco.restaurant;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity implements MenuRequest.Callback {

    String category;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Intent intent = getIntent();
        category = (String) intent.getSerializableExtra("clicked_entry");
    }

    @Override
    public void gotMenu(ArrayList<MenuItem> menuItems) {

        ArrayAdapter<String> adapter = new MenuAdapter(this, menuItems);

        ListView listview = findViewById(R.id.listview);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new ListClickListener());
    }

    @Override
    public void gotMenuError(String message) {

        Context context = getApplicationContext();

        // print message
        int duration = Toast.LENGTH_LONG;
        Toast.makeText(context, message, duration).show();
    }

    private class ListClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            String category = (String) adapterView.getItemAtPosition(i);

            Intent intent = new Intent(MenuActivity.this, CategoriesActivity.class);
            intent.putExtra("clicked_entry", category);
            startActivity(intent);
        }
    }
}
