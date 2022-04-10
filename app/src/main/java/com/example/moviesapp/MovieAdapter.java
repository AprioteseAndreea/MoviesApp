package com.example.moviesapp;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MovieAdapter extends ArrayAdapter<Movie> {
    private final Context context;
    private final Movie[] movies;

    MovieAdapter(Context context, Movie[] movies) {
        super(context, R.layout.movie_cell, movies);

        this.context = context;
        this.movies = movies;
    }

    public View getView(int position, View view, ViewGroup group) {
        View customView = View.inflate(this.context, R.layout.movie_cell, null);

        ImageView movieImage = customView.findViewById(R.id.movie_image);
        TextView titleMovie = customView.findViewById(R.id.movie_name);
        TextView movieSubtitle = customView.findViewById(R.id.movie_subtitle);

        titleMovie.setText(this.movies[position].getTitle());
        movieSubtitle.setText(this.movies[position].getType());

        String imageName = this.movies[position].getImage();
        imageName = imageName.substring(0, imageName.indexOf("."));

        int imageId = this.context.getResources().getIdentifier(imageName, "drawable", this.context.getPackageName());
        movieImage.setImageResource(imageId);

        return customView;
    }
}
