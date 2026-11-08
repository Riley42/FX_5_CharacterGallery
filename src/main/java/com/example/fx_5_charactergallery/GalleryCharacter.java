package com.example.fx_5_charactergallery;

public class GalleryCharacter
{
    private final String name;
    private final String title;
    private final String imagePath;
    private final String quote;

    /**
     * Creates a new character for the gallery.
     *
     * @param name      the character's name
     * @param title     a short role / title shown under the name
     * @param imagePath the resource path to the character's image (starts with "/")
     * @param quote     an iconic line from the character
     */
    public GalleryCharacter(String name, String title, String imagePath, String quote)
    {
        this.name = name;
        this.title = title;
        this.imagePath = imagePath;
        this.quote = quote;
    }

    public String getName()
    {
        return name;
    }

    public String getTitle()
    {
        return title;
    }

    public String getImagePath()
    {
        return imagePath;
    }

    public String getQuote()
    {
        return quote;
    }

    @Override
    public String toString()
    {
        return name;
    }
}
