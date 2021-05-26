/*
 * MyAlert.java
 */
package vues;

import javafx.event.EventTarget;
import javafx.scene.control.Alert;

/**
 * Class description goes here.
 *
 * @author Samuel Sicard
 * @version 0.9
 */
public class MyAlert extends Alert implements EventTarget {


    public MyAlert(Alert.AlertType arg_type, String arg_titre, String arg_entete) {
        super(arg_type);
        super.setTitle(arg_titre);
        super.setHeaderText(arg_entete);
    }

    public void afficherAlert() {
        super.showAndWait();
    }
}



