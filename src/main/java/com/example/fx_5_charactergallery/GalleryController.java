package com.example.fx_5_charactergallery;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class GalleryController
{
    @FXML private BorderPane rootPane;
    @FXML private ComboBox<String> titleComboBox;
    @FXML private Button prevButton, nextButton, clearButton;

    // small previous image | big main image | small next image
    @FXML private ImageView prevImageView, mainImageView, nextImageView;
    @FXML private Label nameLabel, titleLabel, quoteLabel, counterLabel;

    // thumbnails
    @FXML private VBox thumbCard0, thumbCard1, thumbCard2, thumbCard3, thumbCard4;
    @FXML private ImageView thumbImage0, thumbImage1, thumbImage2, thumbImage3, thumbImage4;
    @FXML private Label thumbLabel0, thumbLabel1, thumbLabel2, thumbLabel3, thumbLabel4;

    private static final String WELCOME_IMAGE = "/images/welcome.png";
    private static final String MISSING_IMAGE = "/images/missing.png";

    private static final String SELECTED_CLASS = "thumb-selected";

    private final Map<String, List<GalleryCharacter>> gallery = new LinkedHashMap<>();

    private List<VBox> thumbCards;
    private List<ImageView> thumbImages;
    private List<Label> thumbLabels;

    private List<GalleryCharacter> currentCharacters = new ArrayList<>();
    private List<Image> currentImages = new ArrayList<>();
    private int currentIndex = 0;

    public void initialize()
    {
        thumbCards = List.of(thumbCard0, thumbCard1, thumbCard2, thumbCard3, thumbCard4);
        thumbImages = List.of(thumbImage0, thumbImage1, thumbImage2, thumbImage3, thumbImage4);
        thumbLabels = List.of(thumbLabel0, thumbLabel1, thumbLabel2, thumbLabel3, thumbLabel4);

        loadGalleryData();
        titleComboBox.getItems().addAll(gallery.keySet());
        resetGallery();
    }

    private void loadGalleryData()
    {
        // ---------- The Lord of the Rings ----------
        String lotr = "The Lord of the Rings";
        List<GalleryCharacter> fellowship = new ArrayList<>();

        fellowship.add(new GalleryCharacter("Gandalf", "The Grey Pilgrim",
                "/images/lotr/gandalf.png", "A wizard is never late."));
        fellowship.add(new GalleryCharacter("Frodo Baggins", "Ring-bearer",
                "/images/lotr/frodo.png", "I will take the Ring, though I do not know the way."));
        fellowship.add(new GalleryCharacter("Samwise Gamgee", "Gardener of Bag End",
                "/images/lotr/samwise.png", "I can't carry it for you, but I can carry you!"));
        fellowship.add(new GalleryCharacter("Aragorn", "King of Gondor",
                "/images/lotr/aragorn.png", "For Frodo."));
        fellowship.add(new GalleryCharacter("Gollum", "Sméagol",
                "/images/lotr/gollum.png", "My precious."));

        gallery.put(lotr, fellowship);

        // ---------- Patrick Stewart: A Tribute ----------
        String stewart = "Patrick Stewart: A Tribute";
        List<GalleryCharacter> roles = new ArrayList<>();

        roles.add(new GalleryCharacter("Jean-Luc Picard", "Star Trek: The Next Generation",
                "/images/stewart/picard.png", "Make it so."));
        roles.add(new GalleryCharacter("Charles Xavier", "X-Men",
                "/images/stewart/xavier.png", "Farewell, my children... and good luck."));
        roles.add(new GalleryCharacter("Macbeth", "Macbeth (2010)",
                "/images/stewart/macbeth.png", "Is this a dagger which I see before me?"));
        roles.add(new GalleryCharacter("Captain Ahab", "Moby Dick (1998)",
                "/images/stewart/ahab.png", "From hell's heart I stab at thee."));
        roles.add(new GalleryCharacter("Ebenezer Scrooge", "A Christmas Carol (1999)",
                "/images/stewart/scrooge.png", "Bah! Humbug!"));

        gallery.put(stewart, roles);

        // ---------- World of Warcraft: Alliance ----------
        String alliance = "World of Warcraft: Alliance";
        List<GalleryCharacter> allianceLeaders = new ArrayList<>();

        allianceLeaders.add(new GalleryCharacter("Varian Wrynn", "King of Stormwind",
                "/images/alliance/varian.png", "For the Alliance!"));
        allianceLeaders.add(new GalleryCharacter("Magni Bronzebeard", "King of Ironforge",
                "/images/alliance/magni.png", "Feel the fury of the mountain!"));
        allianceLeaders.add(new GalleryCharacter("Gelbin Mekkatorque", "High Tinker of the Gnomes",
                "/images/alliance/mekkatorque.png", "Gnomeregan forever!"));
        allianceLeaders.add(new GalleryCharacter("Tyrande Whisperwind", "High Priestess of Elune",
                "/images/alliance/tyrande.png", "Elune be with you."));
        allianceLeaders.add(new GalleryCharacter("Prophet Velen", "Leader of the Draenei",
                "/images/alliance/velen.png", "The Light will guide us."));

        gallery.put(alliance, allianceLeaders);

        // ---------- World of Warcraft: Horde ----------
        String horde = "World of Warcraft: Horde";
        List<GalleryCharacter> hordeLeaders = new ArrayList<>();

        hordeLeaders.add(new GalleryCharacter("Thrall", "Warchief of the Horde",
                "/images/horde/thrall.png", "Lok'tar ogar!"));
        hordeLeaders.add(new GalleryCharacter("Cairne Bloodhoof", "High Chieftain of the Tauren",
                "/images/horde/cairne.png", "You greenskins fight with both savagery and valor. I am intrigued."));
        hordeLeaders.add(new GalleryCharacter("Sylvanas Windrunner", "The Banshee Queen",
                "/images/horde/sylvanas.png", "Not a Banshee Queen... Not a Ranger-General... But always a Windrunner."));
        hordeLeaders.add(new GalleryCharacter("Vol'jin", "Chieftain of the Darkspear",
                "/images/horde/voljin.png", "I be Vol'jin, son of Sen'jin."));
        hordeLeaders.add(new GalleryCharacter("Lor'themar Theron", "Regent Lord of Quel'Thalas",
                "/images/horde/lorthemar.png", "Glory to the Sin'dorei."));

        gallery.put(horde, hordeLeaders);
    }

    @FXML
    public void handleTitleSelected(ActionEvent event)
    {
        String title = titleComboBox.getValue();

        if (title == null) return;

        currentCharacters = gallery.get(title);
        currentImages = loadImages(currentCharacters);

        // Fill the 5 fixed thumbnails with this title's heroes
        for (int i = 0; i < thumbCards.size(); i++)
        {
            if (i < currentCharacters.size())
            {
                thumbImages.get(i).setImage(currentImages.get(i));
                thumbLabels.get(i).setText(currentCharacters.get(i).getName());
                thumbCards.get(i).setVisible(true);
            }
            else
            {
                // A title with fewer than 5 heroes just hides the extra cards
                thumbCards.get(i).setVisible(false);
            }
        }

        setNavigationDisabled(false);
        showCharacter(0);
    }

    @FXML
    public void handleThumbnailClick(MouseEvent event)
    {
        if (currentCharacters.isEmpty()) return;

        Node clickedCard = (Node) event.getSource();
        int index = thumbCards.indexOf(clickedCard);

        if (index >= 0 && index < currentCharacters.size())
        {
            showCharacter(index);
        }
    }

    @FXML
    public void handlePrevious(ActionEvent event)
    {
        goToPrevious();
    }
    @FXML
    public void handleNext(ActionEvent event)
    {
        goToNext();
    }
    @FXML
    public void handlePrevCardClick(MouseEvent event)
    {
        goToPrevious();
    }
    @FXML
    public void handleNextCardClick(MouseEvent event)
    {
        goToNext();
    }

    private void goToPrevious()
    {
        if (!currentCharacters.isEmpty())
        {
            showCharacter(wrapIndex(currentIndex - 1));
        }
    }
    private void goToNext()
    {
        if (!currentCharacters.isEmpty())
        {
            showCharacter(wrapIndex(currentIndex + 1));
        }
    }
    private int wrapIndex(int index)
    {
        int size = currentCharacters.size();
        return (index + size) % size;
    }

    private void showCharacter(int index)
    {
        currentIndex = index;
        GalleryCharacter hero = currentCharacters.get(index);

        // Big center image + the hero's info and quote underneath
        mainImageView.setImage(currentImages.get(index));
        nameLabel.setText(hero.getName());
        titleLabel.setText(hero.getTitle());
        quoteLabel.setText("\" + hero.getQuote() + \"");
        counterLabel.setText((index + 1) + " of " + currentCharacters.size());

        // Small side images: the heroes before and after
        prevImageView.setImage(currentImages.get(wrapIndex(index - 1)));
        nextImageView.setImage(currentImages.get(wrapIndex(index + 1)));

        highlightThumbnail(index);
    }

    /** Moves the "selected" style class to the thumbnail at this index. */
    private void highlightThumbnail(int index)
    {
        for (VBox thumbCard : thumbCards) {
            thumbCard.getStyleClass().remove(SELECTED_CLASS);
        }
        thumbCards.get(index).getStyleClass().add(SELECTED_CLASS);
    }

    private List<Image> loadImages(List<GalleryCharacter> characters)
    {
        List<Image> images = new ArrayList<>();
        List<String> missing = new ArrayList<>();

        for (GalleryCharacter hero : characters)
        {
            URL url = getClass().getResource(hero.getImagePath());

            if (url == null)
            {
                missing.add(hero.getImagePath());
                url = getClass().getResource(MISSING_IMAGE);
            }

            images.add(new Image(url.toExternalForm()));
        }

        if (!missing.isEmpty())
        {
            Utils.showInformativeAlert(Alert.AlertType.ERROR, "Image Not Found",
                    "These images could not be found:\n\n" + String.join("\n", missing)
                            + "\n\nCheck the file names and the resources/images folder.");
        }

        return images;
    }

    @FXML
    public void handleClear(ActionEvent event)
    {
        if (titleComboBox.getValue() == null)
        {
            Utils.showInformativeAlert(Alert.AlertType.INFORMATION, "Nothing to Clear",
                    "The halls are already empty. Choose a hall first!");
            return;
        }

        Optional<ButtonType> result = Utils.showConfirmationAlert("Clear Gallery",
                "Leave the " + titleComboBox.getValue() + " hall and clear the gallery?");

        if (result.isPresent() && result.get().getButtonData() == ButtonBar.ButtonData.OK_DONE)
        {
            resetGallery();
        }
    }

    private void resetGallery()
    {
        titleComboBox.setValue(null);

        currentCharacters = new ArrayList<>();
        currentImages = new ArrayList<>();
        currentIndex = 0;

        mainImageView.setImage(new Image(getClass().getResource(WELCOME_IMAGE).toExternalForm()));
        prevImageView.setImage(null);
        nextImageView.setImage(null);

        nameLabel.setText("Welcome to the Hall of Heroes");
        titleLabel.setText("Choose a hall above to begin");
        quoteLabel.setText("");
        counterLabel.setText("");

        for (int i = 0; i < thumbCards.size(); i++)
        {
            thumbImages.get(i).setImage(null);
            thumbLabels.get(i).setText("");
            thumbCards.get(i).getStyleClass().remove(SELECTED_CLASS);
            thumbCards.get(i).setVisible(true);
        }

        setNavigationDisabled(true);
    }

    private void setNavigationDisabled(boolean disabled)
    {
        prevButton.setDisable(disabled);
        nextButton.setDisable(disabled);
    }
}