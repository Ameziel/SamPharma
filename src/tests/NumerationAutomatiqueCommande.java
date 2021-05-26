/*
 * NumerationAutomatiqueCommande.java
 */
package tests;

/**
 * Classe de test pour la numeration d'une commande.
 * Une commande doit toujours etre composee de 3 chiffres, de 001 a 999.
 * En effet des zero places avant ne sont pas comptes comme des chiffres significatifs
 * et donc pas pris en compte par un affiche standard de "system.out.print".
 *
 * @author Samuel Sicard
 * @version 0.9
 */
public class NumerationAutomatiqueCommande {

    public static void main (String[] args) {
         int num = 001;

        System.out.println(num);
    }

}
