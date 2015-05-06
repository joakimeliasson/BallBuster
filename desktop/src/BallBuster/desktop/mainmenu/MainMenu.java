package BallBuster.desktop.mainmenu;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 * Created by Matthias on 2015-05-06.
 */
public class MainMenu extends GridPane implements Initializable {


    //FXML variables

    @FXML
    ComboBox<String> mapComboBox;
    @FXML
    Button playerOneUpKey;
    @FXML
    Button playerOneDownKey;
    @FXML
    Button playerOneLeftKey;
    @FXML
    Button playerOneRightKey;
    @FXML
    Button playerOneMagnetKey;
    @FXML
    Button playerOnePowerupKey;
    @FXML
    Button playerTwoUpKey;
    @FXML
    Button playerTwoDownKey;
    @FXML
    Button playerTwoLeftKey;
    @FXML
    Button playerTwoRightKey;
    @FXML
    Button playerTwoMagnetKey;
    @FXML
    Button playerTwoPowerupKey;
    @FXML
    ComboBox<String> playerOneSkinBox;
    @FXML
    ComboBox<String> playerTwoSkinBox;

    //

    Button source;
    Boolean rebindActive = false;
    List<String> skinList;






    private final double em = Math.rint(new Text("").getLayoutBounds().getHeight());
    private final double TITLE_SIZE = 10 * em;

    private Stage context;

    public MainMenu(Stage context) {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("OldMainMenu.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.getStylesheets().add(this.getClass().getResource("OldMainMenu.css").toExternalForm());

        this.context = context;

    }//Constructor


    private void rebindPressed(MouseEvent e){
        if(e.getSource().getClass() == Button.class){
            source = (Button)e.getSource();
            rebindActive = true;
        }
    }

    private void doRebind(KeyEvent e){
        source.setText(e.getCharacter());
        source = null;
        rebindActive = false;
    }

    private void readSkins(){
        //TODO MAKE FILE READER FOR SKINS
        File skinFile = new File("");
        int nbrOfSkins;
        try {
            Scanner skinScanner = new Scanner(skinFile);

        } catch(FileNotFoundException f){

        }
    }

    public List<String> getBinds(){
        List<String> bindList = new LinkedList<String>();
        bindList.add(playerOneUpKey.getText()+":");
        bindList.add(playerOneDownKey.getText()+":");
        bindList.add(playerOneLeftKey.getText()+":");
        bindList.add(playerOneRightKey.getText()+":");
        bindList.add(playerOneMagnetKey.getText()+":");
        bindList.add(playerTwoUpKey.getText()+":");
        bindList.add(playerTwoDownKey.getText()+":");
        bindList.add(playerTwoLeftKey.getText()+":");
        bindList.add(playerTwoRightKey.getText()+":");
        bindList.add(playerTwoMagnetKey.getText()+":");
        return bindList;
    }


    /**
     * Actions
     */


    //.....................




    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

}
