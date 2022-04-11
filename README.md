# MoviesApp
Technical Contribution :

In the first activity, I have in the toolbar, on the right side, a button that has an icon (plus icon), and if we click on it a new activity will open. In this activity we can add a new movie by completing or selecting some data related to the movie we want to add, such as: name, duration - hours, duration-minutes, year, genre, description, IMDB link and photo url.
For these I used TextInputLayout together with TextInputEditText, AutoCompleteTextView and MultiAutoCompleteTextView.
For the duration-hour, duration-minutes, year and genre inputs we can select a value from a predefined list that we can find in the string.xml file and to convert them we use an ArrayAdapter. In the photo field it is read only but if we click on the camera icon the photo gallery will open from which we can select an image.
