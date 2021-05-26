/*
 * ErrObjetInexistant.java
 */
package exceptions;

/**
 * Class description goes here.
 *
 * @author Samuel Sicard
 * @version 0.9
 */
public class ErrObjetInexistant extends Exception {

    public ErrObjetInexistant() {
        super(ressources.txt.Textes_application.ERR_OBJET_INEXISTANT);
    }
}
