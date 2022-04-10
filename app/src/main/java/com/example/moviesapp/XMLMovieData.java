package com.example.moviesapp;

import android.content.Context;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import nu.xom.Serializer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;


public class XMLMovieData {
    private final Movie[] movies;

    public XMLMovieData(Context context) {
        InputStream stream = context.getResources().openRawResource(R.raw.movies);

        DocumentBuilder builder;
        Document xmlTree = null;

        try {
            builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            xmlTree = builder.parse(stream);

        } catch (Exception ignored) {

        }
        assert xmlTree != null;
        NodeList titleList = xmlTree.getElementsByTagName("title");
        NodeList yearList = xmlTree.getElementsByTagName("year");
        NodeList durationList = xmlTree.getElementsByTagName("duration");
        NodeList typeList = xmlTree.getElementsByTagName("type");
        NodeList descriptionList = xmlTree.getElementsByTagName("description");
        NodeList imageList = xmlTree.getElementsByTagName("image");
        NodeList urlList = xmlTree.getElementsByTagName("url");
        movies = new Movie[titleList.getLength()];

        for (int i = 0; i < movies.length; i++) {
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

    public Movie[] getMovies() {
        return movies;
    }

    public Movie getMovie(int position) {
        return movies[position];
    }

    public void addMovie(Movie movie) {
        Movie[] newMovies = new Movie[movies.length + 1];
        for (int i = 0; i < movies.length + 1; i++) {

            if (i == movies.length) {
                newMovies[movies.length] = new Movie(movie.getTitle(), movie.getYear(),
                        movie.getDuration(), movie.getType(), movie.getDescription(), movie.getImage(), movie.getUrl());
            } else {
                newMovies[i] = new Movie(movies[i].getTitle(),
                        movies[i].getYear(),
                        movies[i].getDuration(),
                        movies[i].getType(), movies[i].getDescription(),
                        movies[i].getImage(),
                        movies[i].getUrl());
            }
        }

        //convert the new string into xml
        XMLWriter(newMovies);

    }
    public void XMLWriter(Movie[] currentMovies)  {
        try{
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();

            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

            Document document = documentBuilder.newDocument();

            // root element
            Element root = document.createElement("dataroot");
            document.appendChild(root);

            // employee element

            for(int i = 0;i<currentMovies.length;i++){
                Element movie = document.createElement("movie");

                Element title = document.createElement("title");
                title.appendChild(document.createTextNode(currentMovies[i].getTitle()));
                movie.appendChild(title);

                Element year = document.createElement("year");
                title.appendChild(document.createTextNode(currentMovies[i].getYear()));
                movie.appendChild(year);

                Element duration = document.createElement("duration");
                title.appendChild(document.createTextNode(currentMovies[i].getDuration()));
                movie.appendChild(duration);

                Element type = document.createElement("type");
                title.appendChild(document.createTextNode(currentMovies[i].getType()));
                movie.appendChild(type);

                Element description = document.createElement("description");
                title.appendChild(document.createTextNode(currentMovies[i].getDescription()));
                movie.appendChild(description);

                Element image = document.createElement("image");
                title.appendChild(document.createTextNode(currentMovies[i].getImage()));
                movie.appendChild(image);

                Element url = document.createElement("url");
                title.appendChild(document.createTextNode(currentMovies[i].getUrl()));
                movie.appendChild(url);

            }
            File file = new File("src/main/res/raw/movies.xml");


            // get a file output stream ready
            FileOutputStream fileOutputStream = new FileOutputStream(file);

            // use the serializer class to write it all
            Serializer serializer = new Serializer(fileOutputStream, "UTF-8");
            serializer.setIndent(3);
            serializer.write((nu.xom.Document) document);

        } catch (ParserConfigurationException | IOException pce) {
            pce.printStackTrace();
        }
    }


}
