/*
 * ErrCommandeConcernee.java
 */
package exceptions;

/**
 * Class description goes here.
 *
 * @author Samuel Sicard
 * @version 0.9
 */
public class ErrCommandeConcernee extends Exception {

    public ErrCommandeConcernee() {
        super(ressources.txt.Textes_application.ERR_COMMANDE_CONCERNEE);
    }
}
