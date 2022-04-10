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

    private ListView list = null;
    private MovieAdapter adapter = null;
    private Movie [] movies = null;
    private Toolbar toolbar;
    private Button addButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        list = findViewById(R.id.listView);
        addButton = findViewById(R.id.add_button);
        movies = (new XMLMovieData(this)).getMovies();
        adapter= new MovieAdapter(this, movies);
        list.setAdapter(adapter);
        list.setOnItemClickListener( new AdapterView.OnItemClickListener(){
                 @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                         Intent myIntent = new Intent(view.getContext(), MovieInformationFirst.class);
                         myIntent.putExtra("movie", movies[position]);
                         startActivity(myIntent);

                    }
                });
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), AddMovieActivity.class);
                startActivity(myIntent);
            }
        });
    }
}