/*
 * Course: CS2852
 * Spring 2019
 * File header contains class Controller
 * Name: crossj
 * Created 3/22/2019
 */
package crossj;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * CS2852 Spring 2019
 * Class purpose: controls window fxml
 *
 * @author crossj
 * @version created on 3/22/2019 at 8:55 AM
 */
public class Controller {
    @FXML
    Label timeLabel;
    @FXML
    Label matchesLabel;
    @FXML
    Label strategyOption;
    @FXML
    TextField textField;
    @FXML
    TextArea textArea;
    @FXML
    TextArea helpTextArea;
    @FXML
    Menu strategyMenu;
    @FXML
    RadioMenuItem indexedArrayButton;
    @FXML
    RadioMenuItem iteratedArrayButton;
    @FXML
    RadioMenuItem indexedLinkedButton;
    @FXML
    RadioMenuItem iteratedLinkedButton;
    @FXML
    RadioMenuItem sortedButton;
    @FXML
    RadioMenuItem mapTreeButton;

    private List<String> currentList;
    private String fileName;
    private AutoCompleter autoCompleter;
    private static final int NEXTDIGIT = 10;
    private long initializeOperationTime;
    private long innerTime;
    private long innerTimePrint;

    /**
     * Opens a file from chosen file from fileChooser
     */
    public void open(){
        try {
            //user chooses file
            FileChooser file = new FileChooser();
            file.getExtensionFilters().add(
                    new FileChooser.ExtensionFilter("Database File", "*.txt", "*.csv"));

            String fileDefault = System.getProperty("user.dir") + File.separator;
            file.setInitialDirectory(new File(fileDefault));
            file.setTitle("Choose a File: ");
            File fileChosen = file.showOpenDialog(null);
            if (fileChosen != null) {
                //fileName is stored and is defaulted to an arrayList
                fileName = fileChosen.getPath();
                indexedArray();
                indexedArrayButton.setSelected(true);
                strategyMenu.setDisable(false);
            }
        } catch (IllegalArgumentException iae){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("File Error");
            alert.setContentText(iae.getMessage());
            alert.showAndWait();
        }
    }

    /**
     * Searches through the list from the specified strategy choice
     */
    public void search(){
        try {
            long beforeTime = System.nanoTime();
            currentList = autoCompleter.allThatBeginWith(textField.getText());
            timeLabel.setText(format(System.nanoTime() - beforeTime));
            textArea.setText(print());
            matchesLabel.setText("" + currentList.size());

        } catch (IllegalStateException ise) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("File Error");
            alert.setContentText(ise.getMessage());
            alert.showAndWait();
        }
    }

    /**
     * Initializes a indexed ArrayList
     */
    public void indexedArray() {
        long beforeTime = System.nanoTime();
        //sets all other options to not selected
        indexedLinkedButton.setSelected(false);
        iteratedArrayButton.setSelected(false);
        iteratedLinkedButton.setSelected(false);
        sortedButton.setSelected(false);
        mapTreeButton.setSelected(false);

        try {
            autoCompleter = new IndexedArray();
            autoCompleter.initialize(fileName);
            helpTextArea.setText(autoCompleter.strategyDescription());
            textField.setEditable(true);
            strategyOption.setText("Indexed ArrayList");
            textField.setText("");
            initializeOperationTime = autoCompleter.getLastOperationTime() +
            System.nanoTime() - beforeTime;
            search();
            timeLabel.setText(format(innerTime + initializeOperationTime));

        } catch (IOException ioe){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("File Error");
            alert.setContentText("File could not be read");
            alert.showAndWait();
        }
    }

    /**
     * Initializes a indexed LinkedList
     */
    public void indexedLinked() {
        long beforeTime = System.nanoTime();
        //sets all other options to not selected
        indexedArrayButton.setSelected(false);
        iteratedArrayButton.setSelected(false);
        iteratedLinkedButton.setSelected(false);
        sortedButton.setSelected(false);
        mapTreeButton.setSelected(false);

        try {
            autoCompleter = new IndexedLinked();
            autoCompleter.initialize(fileName);
            helpTextArea.setText(autoCompleter.strategyDescription());
            textField.setEditable(true);
            strategyOption.setText("Indexed LinkedList");
            textField.setText("");
            initializeOperationTime = autoCompleter.getLastOperationTime() +
                    System.nanoTime() - beforeTime;
            search();
            timeLabel.setText(format(innerTime + initializeOperationTime));

        } catch (IOException ioe){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("File Error");
            alert.setContentText("File could not be read");
            alert.showAndWait();
        }
    }

    /**
     * Initializes a iterated ArrayList
     */
    public void iteratedArray() {
        long beforeTime = System.nanoTime();
        //sets all other options to not selected
        indexedLinkedButton.setSelected(false);
        indexedArrayButton.setSelected(false);
        iteratedLinkedButton.setSelected(false);
        sortedButton.setSelected(false);
        mapTreeButton.setSelected(false);

        try {
            autoCompleter = new IteratedArray();
            autoCompleter.initialize(fileName);
            textField.setEditable(true);
            helpTextArea.setText(autoCompleter.strategyDescription());
            strategyOption.setText("Iterated ArrayList");
            textField.setText("");
            initializeOperationTime = autoCompleter.getLastOperationTime() +
                    System.nanoTime() - beforeTime;
            search();
            timeLabel.setText(format(innerTime + initializeOperationTime));

        } catch (IOException ioe){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("File Error");
            alert.setContentText("File could not be read");
            alert.showAndWait();
        }

    }

    /**
     * Initializes a iterated LinkedList
     */
    public void iteratedLinked() {
        long beforeTime = System.nanoTime();
        //sets all other options to not selected
        indexedLinkedButton.setSelected(false);
        iteratedArrayButton.setSelected(false);
        indexedArrayButton.setSelected(false);
        sortedButton.setSelected(false);
        mapTreeButton.setSelected(false);

        try {
            autoCompleter = new IteratedLinked();
            autoCompleter.initialize(fileName);
            textField.setEditable(true);
            helpTextArea.setText(autoCompleter.strategyDescription());
            strategyOption.setText("Iterated LinkedList");
            textField.setText("");
            initializeOperationTime = autoCompleter.getLastOperationTime() +
                    System.nanoTime() - beforeTime;
            search();
            timeLabel.setText(format(innerTime + initializeOperationTime));

        } catch (IOException ioe){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("File Error");
            alert.setContentText("File could not be read");
            alert.showAndWait();
        }
    }

    /**
     * initializes a TreeOfMaps
     */
    public void mapTree() {
        long beforeTime = System.nanoTime();
        //sets all other options to not selected
        indexedLinkedButton.setSelected(false);
        indexedArrayButton.setSelected(false);
        iteratedLinkedButton.setSelected(false);
        iteratedArrayButton.setSelected(false);
        sortedButton.setSelected(false);


        try {
            autoCompleter = new TreeOfMapsAuto();
            autoCompleter.initialize(fileName);
            textField.setEditable(true);
            helpTextArea.setText(autoCompleter.strategyDescription());
            strategyOption.setText("Tree Of Maps");
            textField.setText("");
            initializeOperationTime = autoCompleter.getLastOperationTime() +
                    System.nanoTime() - beforeTime;
            search();
            timeLabel.setText(format(innerTime + initializeOperationTime));

        } catch (IOException ioe){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("File Error");
            alert.setContentText("File could not be read");
            alert.showAndWait();
        }

    }

    /**
     * Initializes a iterated LinkedList
     */
    public void sortedArray() {
        long beforeTime = System.nanoTime();
        //sets all other options to not selected
        indexedLinkedButton.setSelected(false);
        iteratedArrayButton.setSelected(false);
        indexedArrayButton.setSelected(false);
        sortedButton.setSelected(false);
        mapTreeButton.setSelected(false);

        try {
            autoCompleter = new SortedArray();
            autoCompleter.initialize(fileName);
            textField.setEditable(true);
            helpTextArea.setText(autoCompleter.strategyDescription());
            strategyOption.setText("Sorted ArrayList");
            textField.setText("");
            initializeOperationTime = autoCompleter.getLastOperationTime() +
                    System.nanoTime() - beforeTime;
            search();
            timeLabel.setText(format(innerTime + initializeOperationTime));

        } catch (IOException ioe){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("File Error");
            alert.setContentText("File could not be read");
            alert.showAndWait();
        }
    }

    /**
     * Formats the operation time into a specified format
     * @param number number wanted formatted
     * @return returns formatted number into a String
     */
    private String format(long number){
        //calculations that separates number into each sub category respectively
        long minute = TimeUnit.NANOSECONDS.toMinutes(number);
        long second = TimeUnit.NANOSECONDS.toSeconds(number) - TimeUnit.MINUTES.toSeconds(minute);
        long milli = TimeUnit.NANOSECONDS.toMillis(number) - TimeUnit.SECONDS.toMillis(second);
        long micro = TimeUnit.NANOSECONDS.toMicros(number) - TimeUnit.MILLISECONDS.toMicros(milli);

        String formattedNumber;

        if(minute!=0||second!=0){
            String stringSSS = "" + milli;
            if(stringSSS.length()>3){
                int placesAfterThreeDigits = stringSSS.substring(0, stringSSS.length()-3).length();
                stringSSS = "" + Math.round(
                        milli/(Math.pow(NEXTDIGIT, placesAfterThreeDigits)));
            } else {
                DecimalFormat decimalFormat1 = new DecimalFormat("000");
                stringSSS = decimalFormat1.format(milli);
            }
            DecimalFormat decimalFormat = new DecimalFormat("00");
            formattedNumber = (decimalFormat.format(minute) + ":"
                    + decimalFormat.format(second) + "." + stringSSS);
        } else if (milli!=0){
            formattedNumber = milli + " Milliseconds";
        } else if (micro!=0){
            formattedNumber = micro + " MicroSeconds";
        } else {
            formattedNumber = number + " NanoSeconds";
        }

        return formattedNumber;
    }

    /**
     * prints out the list
     * @return returns a formatted String
     */
    private String print(){
        long beforeTime = System.nanoTime();
        StringBuilder stringBuilder = new StringBuilder();
        for(Object element : currentList){
            stringBuilder.append(element + "\n");
        }
        innerTimePrint = System.nanoTime() - beforeTime;
        return stringBuilder.toString();
    }

}
