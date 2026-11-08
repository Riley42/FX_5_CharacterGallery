package com.example.fx_5_charactergallery;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.Optional;

/**
 * Utils
 *
 * This class contains helper (utility) methods that can be reused
 * throughout the program.
 *
 * Instead of rewriting the same code multiple times,
 * we put it here and call it whenever needed.
 */

public class Utils
{
    // These store the mouse position when clicking the window
    // Used for dragging the window around the screen
    private static double screenX = 0;
    private static double screenY = 0;

    /**
     * Creates a styled JavaFX Alert.
     *
     * This method sets up the alert but DOES NOT show it.
     *
     * @param type The type of alert (INFORMATION, ERROR, CONFIRMATION, etc.)
     * @param title The title of the alert window
     * @param content The message shown inside the alert
     * @return A configured Alert object
     */
    public static Alert createAlert(Alert.AlertType type, String title, String content)
    {
        // Create a new alert of the given type
        Alert alert = new Alert(type);

        // Apply custom CSS styling to the alert
        // (alert.css must sit in the same package folder as this class, under resources)
        alert.getDialogPane().getStylesheets().add(Utils.class.getResource("alert.css").toExternalForm());

        // Set the title at the top of the window
        alert.setTitle(title);

        // Remove the default header (keeps UI cleaner)
        alert.setHeaderText(null);

        // Set the main message inside the alert
        alert.setContentText(content);
        return alert;
    }

    /**
     * Creates and shows an alert that only needs an OK button.
     *
     * This is used for:
     * - success messages
     * - error messages
     * - general information
     *
     * @param type The type of alert
     * @param title The title of the alert
     * @param content The message inside the alert
     */
    public static void showInformativeAlert(Alert.AlertType type, String title, String content)
    {
        // Create the alert using the helper method
        Alert alert = createAlert(type, title, content);

        // Show the alert and wait for the user to click OK
        alert.showAndWait();
    }

    /**
     * Shows a confirmation alert with Yes and No buttons.
     *
     * This is used when the user needs to make a decision.
     *
     * @param title The title of the alert
     * @param content The message inside the alert
     * @return The button the user clicked (Yes or No)
     */
    public static Optional<ButtonType> showConfirmationAlert(String title, String content)
    {
        // Create a confirmation alert
        Alert alert = createAlert(Alert.AlertType.CONFIRMATION, title, content);

        // Create custom buttons
        ButtonType yesButton = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

        // Replace default buttons with our custom ones
        alert.getButtonTypes().setAll(yesButton, noButton);

        // Show the alert and return the user's choice
        return alert.showAndWait();
    }

    /**
     * Allows a window (stage) to be dragged using the mouse.
     *
     * This is useful for custom-styled windows that do not have
     * the default title bar.
     *
     * @param scene The scene where mouse events are detected
     * @param stage The stage (window) that will move
     */
    public static void SetMovableWindow(Scene scene, Stage stage)
    {
        // When the mouse is pressed:
        // store the position where the user clicked
        scene.setOnMousePressed((MouseEvent event) -> {
            screenX = event.getSceneX();
            screenY = event.getSceneY();
        });

        // When the mouse is dragged:
        // move the window based on the mouse movement
        scene.setOnMouseDragged((MouseEvent event) -> {

            // Update window position
            stage.setX(event.getScreenX() - screenX);
            stage.setY(event.getScreenY() - screenY);

            // Slight transparency effect while dragging
            stage.setOpacity(.8);
        });

        // When the mouse is released:
        // restore the window opacity
        scene.setOnMouseReleased((MouseEvent event) -> {
            stage.setOpacity(1);
        });
    }
}
