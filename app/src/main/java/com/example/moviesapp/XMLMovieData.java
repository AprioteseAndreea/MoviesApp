package com.example.moviesapp;

import android.content.Context;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class XMLMovieData {
    private Context context;
    private Movie[] movies;

    public XMLMovieData(Context context){
        this.context = context;
        InputStream stream = this.context.getResources().openRawResource(R.raw.movies);

        DocumentBuilder builder = null;
        Document xmlTree = null;

        try{
            builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            xmlTree = builder.parse(stream);

        }catch(Exception e){

        }
        NodeList titleList = xmlTree.getElementsByTagName("title");
        NodeList yearList = xmlTree.getElementsByTagName("year");
        NodeList durationList = xmlTree.getElementsByTagName("duration");
        NodeList typeList = xmlTree.getElementsByTagName("type");
        NodeList descriptionList = xmlTree.getElementsByTagName("description");
        NodeList imageList = xmlTree.getElementsByTagName("image");
        NodeList urlList = xmlTree.getElementsByTagName("url");

        movies = new Movie[titleList.getLength()];

        for(int i=0;i<movies.length;i++){
            String title = titleList.item(i).getFirstChild().getNodeValue();
            String year = yearList.item(i).getFirstChild().getNodeValue();
            String duration = durationList.item(i).getFirstChild().getNodeValue();
            String type = typeList.item(i).getFirstChild().getNodeValue();
            String description = descriptionList.item(i).getFirstChild().getNodeValue();
            String image = imageList.item(i).getFirstChild().getNodeValue();
            String url = urlList.item(i).getFirstChild().getNodeValue();

            movies[i] = new Movie(title, year, duration, type, description, image, url);
        }
    }
    public Movie[] getMovies(){return movies;};
    public Movie getMovie(int position){return movies[position];}
}
