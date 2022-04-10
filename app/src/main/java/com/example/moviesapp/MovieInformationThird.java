package com.example.moviesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class MovieInformationThird extends AppCompatActivity {

    Movie movie;
    WebView webView;

    Button refreshButton;
    Button backButton;
    Button forwardButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_information_third);
        if(getIntent().getExtras()!=null){
            movie = (Movie) getIntent().getSerializableExtra("movie");
        }
        webView = findViewById(R.id.webview);
        refreshButton = findViewById(R.id.refresh_button);
        backButton = findViewById(R.id.back_button);
        forwardButton = findViewById(R.id.forward_button);

        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(movie.getUrl());

        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.reload();
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(webView.canGoBack()) webView.goBack();
            }
        });
        forwardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(webView.canGoForward()){
                    webView.goForward();
                }
            }
        });
    }
}