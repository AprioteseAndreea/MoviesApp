package com.example.moviesapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.MultiAutoCompleteTextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Objects;

public class AddMovieActivity extends AppCompatActivity {
    private static final int RESULT_LOAD_IMAGE = 1;
    private TextInputEditText textInputEditText;
    private TextInputEditText nameInputEditText;
    private TextInputEditText descriptionInputEditText;
    private TextInputEditText linkInputEditText;

    private String hour, minute, year;
    private String movieGenres;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);
        Toolbar toolbar = findViewById(R.id.add_movie_toolbar);
        Button saveButton = findViewById(R.id.save_button);
        setSupportActionBar(toolbar);

        String[] hours = getResources().getStringArray(R.array.hours);
        String[] minutes = getResources().getStringArray(R.array.minutes);
        String[] years = getResources().getStringArray(R.array.years);
        String[] genres = getResources().getStringArray(R.array.genres);

        XMLMovieData xmlMovieData = new XMLMovieData(this);


        AutoCompleteTextView hoursAutoComplete = findViewById(R.id.autoComplete_hour);
        AutoCompleteTextView minutesAutoComplete = findViewById(R.id.autoComplete_minutes);
        AutoCompleteTextView yearAutoComplete = findViewById(R.id.autoComplete_year);

        MultiAutoCompleteTextView genreAutoComplete = findViewById(R.id.autoComplete_genre);

        textInputEditText = findViewById(R.id.photo_url);
        nameInputEditText = findViewById(R.id.name_text);
        descriptionInputEditText = findViewById(R.id.description_text);
        linkInputEditText = findViewById(R.id.link_text);
        textInputEditText.setEnabled(false);

        TextInputLayout photoInput = findViewById(R.id.photo_input);
        photoInput.setStartIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Movie newMovie = new Movie(
                        nameInputEditText.getText().toString(),
                        year, hour + ":" + minute, movieGenres,descriptionInputEditText.getText().toString(),
                        textInputEditText.getText().toString(),linkInputEditText.getText().toString()
                        );

                xmlMovieData.addMovie(newMovie);
                finish();
            }
        });
        ArrayAdapter hoursAdapter = new ArrayAdapter(this, R.layout.dropdown_item, hours);
        hoursAutoComplete.setAdapter(hoursAdapter);
        hoursAutoComplete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                hour = hoursAdapter.getItem(position).toString();
            }
        });

        ArrayAdapter minutesAdapter = new ArrayAdapter(this, R.layout.dropdown_item, minutes);
        minutesAutoComplete.setAdapter(minutesAdapter);
        minutesAutoComplete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                minute = minutesAdapter.getItem(position).toString();
            }
        });

        ArrayAdapter yearAdapter = new ArrayAdapter(this, R.layout.dropdown_item, years);
        yearAutoComplete.setAdapter(yearAdapter);
        yearAutoComplete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                year = yearAdapter.getItem(position).toString();
            }
        });
        ArrayAdapter genreAdapter = new ArrayAdapter(this, R.layout.dropdown_item, genres);
        genreAutoComplete.setAdapter(genreAdapter);
        genreAutoComplete.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        genreAutoComplete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                movieGenres += genreAdapter.getItem(position).toString() + " ";
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case RESULT_LOAD_IMAGE:
                if (resultCode == RESULT_OK) {
                    textInputEditText.setEnabled(true);
                    Uri selectedImage = data.getData();
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};
                    Cursor cursor = this.getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String picturePath = cursor.getString(columnIndex);
                    cursor.close();
                    textInputEditText.setText(picturePath);

                }
        }
    }
}