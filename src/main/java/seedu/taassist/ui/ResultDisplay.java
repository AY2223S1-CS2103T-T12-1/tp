package seedu.taassist.ui;

import static java.util.Objects.requireNonNull;

import java.util.Optional;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;

/**
 * A ui for the status bar that is displayed at the header of the application.
 */
public class ResultDisplay extends UiPart<Region> {

    private static final String FXML = "ResultDisplay.fxml";
    private static final String FOCUS_LABEL_FORMAT = "Focus: [%s]";

    @FXML
    private TextArea resultDisplay;

    @FXML
    private Label focusLabel;

    public ResultDisplay() {
        super(FXML);
    }

    public void setFeedbackToUser(String feedbackToUser) {
        requireNonNull(feedbackToUser);
        resultDisplay.setText(feedbackToUser);
    }

    public void setFocusLabel(Optional<String> changedFocus) {
        boolean isFocusChanged = changedFocus != null;
        if (!isFocusChanged) {
            return;
        }

        boolean isFocusCleared = changedFocus.isEmpty();
        if (isFocusCleared) {
            focusLabel.setText("");
        } else {
            focusLabel.setText(String.format(FOCUS_LABEL_FORMAT, changedFocus.get()));
        }
    }
}
