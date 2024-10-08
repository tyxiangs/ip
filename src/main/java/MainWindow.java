import dave.Dave;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Controller for the main GUI.
 * This class is responsible for handling user input, interacting with the Duke instance (called Dave),
 * and updating the user interface.
 */
public class MainWindow extends AnchorPane {

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox dialogContainer;

    @FXML
    private TextField userInput;

    @FXML
    private Button sendButton;

    private Dave dave;

    private Image userImage = new Image(this.getClass().getResourceAsStream("userImage.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("dukeImage.png"));

    private Stage stage; // Store the Stage instance for closing the window

    /**
     * Initializes the MainWindow by binding the scroll pane's vertical value to the dialog container's height
     * and setting the scroll behavior.
     * Ensures the scroll pane adapts to the height and automatically scrolls down when new content is added.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        scrollPane.setFitToWidth(true);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        dialogContainer.setFillWidth(true);
    }

    /**
     * Injects the Duke (Dave) instance into the controller.
     * This allows the MainWindow to interact with the Duke instance for task management.
     *
     * @param d The Dave instance that handles tasks and processes user commands.
     */
    public void setDave(Dave d) {
        dave = d;
    }

    /**
     * Injects the Stage into the controller.
     * This allows the MainWindow to control the Stage, such as closing the window when the "bye" command is entered.
     *
     * @param stage The Stage instance representing the main window.
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Handles user input by creating dialog boxes for both the user's input and Dave's response.
     * Adds the dialog boxes to the dialog container and clears the user input field after processing.
     * This method is triggered when the user clicks the "Send" button or presses enter.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = dave.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();

        // Check if the input is "bye" and close the stage
        if (input.equals("bye")) {
            // Create a pause before closing the window
            PauseTransition delay = new PauseTransition(Duration.seconds(0.75)); // Adjust the delay as needed
            delay.setOnFinished(event -> stage.close()); // Close the stage after the delay
            delay.play(); // Start the delay
        }
    }

    /**
     * Displays a welcome message and lists the available commands to the user when the application starts.
     * This message is displayed in a dialog box within the GUI.
     */
    public void showWelcomeMessage() {
        String welcomeMessage = "Welcome to Dave!\n"
                + "Here are the missions we accept:\n"
                + "- todo <task description>: Add a ToDo task\n"
                + "- deadline <task description> /by <date> <time>: Add a Deadline task\n"
                + "- event <task description> /from <start date> <start time> /to <end time>: Add an Event task\n"
                + "- list: View all tasks\n"
                + "- mark <task number>: Mark a task as done\n"
                + "- unmark <task number>: Unmark a task\n"
                + "- delete <task number>: Delete a task\n"
                + "- find <keyword>: Find tasks that match the keyword\n"
                + "- bye: Exit the program\n"
                + "- reminder all: Check undone task\n"
                + "- reminder overdue: Check undone overdue task\n";

        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(welcomeMessage, dukeImage)
        );
    }
}
