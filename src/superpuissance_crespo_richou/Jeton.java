/*
 * Juliette CRESPO et Jeanne RICHOU
 * 02/11/2020
 * Classe Jeton - SuperPuissance4
 */

package superpuissance_crespo_richou;
/**
 *
 * @author julie
 */


 /////////////// Attributs ///////////////
public class Jeton {
    String couleur;
 /////////////////////////////////////////

/////////////// Méthodes ///////////////
    
 // Méthode qui permet de déclarer unJeton 
 // sur une ligne  
    
 public Jeton (String uneCouleur){
       couleur=uneCouleur;   
   }
/////////////////////////////////////////
 
 // Méthodes qui permet de renvoier la couleur 
 //du jeton
 public String lireCouleur(){
     return couleur;
 }

///////////////////////////////////////// 
 // Méthode String qui permet d'afficher 
 // la couleur du jeton 
 
 @Override
 public String toString(){
     if  ("Rouge".equals(couleur)){ // fonction eguals permet de remplacer ==
         return "\u001B[41m o \u001B[40m";
     }
     return  "\u001B[43m o \u001B[40m";
 }
  
}
 /////////////////////////////////////////