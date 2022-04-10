package com.example.moviesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MovieInformationSecond extends AppCompatActivity {

    ImageView currentImage;
    TextView currentTitle;
    TextView currentDuration;
    TextView currentYear;
    TextView currentDescription;
    Button imdbButton;
    Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_information_second);

        if(getIntent().getExtras()!=null){
            movie = (Movie) getIntent().getSerializableExtra("movie");
        }
        currentImage = findViewById(R.id.movie_image);
        currentTitle = findViewById(R.id.movie_title);
        currentDuration = findViewById(R.id.movie_duration);
        currentYear = findViewById(R.id.movie_year);
        currentDescription = findViewById(R.id.movie_description);
        imdbButton = findViewById(R.id.imdb_button);

        currentDescription.setMovementMethod(new ScrollingMovementMethod());
        imdbButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             Intent myIntent = new Intent(v.getContext(), MovieInformationThird.class);
             myIntent.putExtra("movie", movie);
             startActivity(myIntent);
            }});
        String imageName = movie.getImage();
        imageName = imageName.substring(0, imageName.indexOf("."));

        int imageId = getBaseContext().getResources().getIdentifier(imageName,"drawable",getBaseContext().getPackageName());
        currentImage.setImageResource(imageId);
        currentTitle.setText(movie.getTitle());
        currentDuration.setText(movie.getDuration());
        currentYear.setText(movie.getYear());
        currentDescription.setText(movie.getDescription());
    }
}