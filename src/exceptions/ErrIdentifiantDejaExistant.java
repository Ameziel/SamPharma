/*
 * ErrIdentifiantDejaExistant.java
 */
package exceptions;

/**
 * Class description goes here.
 *
 * @author Samuel Sicard
 * @version 0.9
 */
public class ErrIdentifiantDejaExistant extends Exception {

    public ErrIdentifiantDejaExistant () {
        super(ressources.txt.Textes_application.ERR_CONTENU_BOITE_IDENTIFIANT_EXISTANT);
    }
}
