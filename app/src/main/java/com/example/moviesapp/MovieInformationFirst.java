package com.example.moviesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MovieInformationFirst extends AppCompatActivity {

    ImageView currentImage;
    TextView currentTitle;
    TextView currentDuration;
    TextView currentYear;
    Button moreInformationButton;
    Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_information_first);
        if (getIntent().getExtras() != null) {
            movie = (Movie) getIntent().getSerializableExtra("movie");
        }
        currentImage = findViewById(R.id.current_movie_image);
        currentTitle = findViewById(R.id.current_movie_title);
        currentDuration = findViewById(R.id.current_movie_duration);
        currentYear = findViewById(R.id.current_movie_year);
        moreInformationButton = findViewById(R.id.more_information);
        moreInformationButton.setOnClickListener(v -> {
            Intent myIntent = new Intent(v.getContext(), MovieInformationSecond.class);
            myIntent.putExtra("movie", movie);
            startActivity(myIntent);
        });
        String imageName = movie.getImage();
        imageName = imageName.substring(0, imageName.indexOf("."));

        int imageId = getBaseContext().getResources().getIdentifier(imageName, "drawable", getBaseContext().getPackageName());
        currentImage.setImageResource(imageId);
        currentTitle.setText(movie.getTitle());
        currentDuration.setText(movie.getDuration());
        currentYear.setText(movie.getYear());
    }
}