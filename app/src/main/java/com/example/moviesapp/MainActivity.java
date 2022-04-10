package com.example.moviesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private Movie[] movies = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ListView list = findViewById(R.id.listView);
        Button addButton = findViewById(R.id.add_button);
        movies = (new XMLMovieData(this)).getMovies();
        MovieAdapter adapter = new MovieAdapter(this, movies);
        list.setAdapter(adapter);
        list.setOnItemClickListener((parent, view, position, id) -> {
            Intent myIntent = new Intent(view.getContext(), MovieInformationFirst.class);
            myIntent.putExtra("movie", movies[position]);
            startActivity(myIntent);

        });
        addButton.setOnClickListener(v -> {
            Intent myIntent = new Intent(v.getContext(), AddMovieActivity.class);
            startActivity(myIntent);
        });
    }
}