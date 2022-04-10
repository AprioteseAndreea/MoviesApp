package com.example.moviesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;

import java.util.ArrayList;

public class AddMovieActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);
        Toolbar toolbar = findViewById(R.id.add_movie_toolbar);
        setSupportActionBar(toolbar);

        String[] hours = getResources().getStringArray(R.array.hours);
        String[] minutes = getResources().getStringArray(R.array.minutes);
        String[] years = getResources().getStringArray(R.array.years);
        String[] genres = getResources().getStringArray(R.array.genres);

        AutoCompleteTextView hoursAutoComplete = findViewById(R.id.autoComplete_hour);
        AutoCompleteTextView minutesAutoComplete = findViewById(R.id.autoComplete_minutes);
        AutoCompleteTextView yearAutoComplete = findViewById(R.id.autoComplete_year);

        MultiAutoCompleteTextView genreAutoComplete = findViewById(R.id.autoComplete_genre);

        ArrayAdapter hoursAdapter = new ArrayAdapter(this, R.layout.dropdown_item, hours);
        hoursAutoComplete.setAdapter(hoursAdapter);

        ArrayAdapter minutesAdapter = new ArrayAdapter(this, R.layout.dropdown_item, minutes);
        minutesAutoComplete.setAdapter(minutesAdapter);

        ArrayAdapter yearAdapter = new ArrayAdapter(this, R.layout.dropdown_item, years);
        yearAutoComplete.setAdapter(yearAdapter);

        ArrayAdapter genreAdapter = new ArrayAdapter(this, R.layout.dropdown_item, genres);
        genreAutoComplete.setAdapter(genreAdapter);

        genreAutoComplete.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());

    }
}