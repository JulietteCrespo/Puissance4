/*
 * Juliette CRESPO et Jeanne RICHOU
 * 02/11/2020
 * Classe Cellule - SuperPuissance4
 */

package superpuissance_crespo_richou;
/**
 *
 * @author julie
 */

/////////////// Attributs ///////////////
public class Cellule {
     Jeton jetonCourant;
     boolean trouNoir;
     boolean desintegrateur;
/////////////////////////////////////////

/////////////// Méthodes /////////////// 
// Innitialise les valeurs de Cellule
public Cellule() {
    jetonCourant=null;
    trouNoir = false;
    desintegrateur=false;
}
/////////////////////////////////////////
public boolean affecterJeton(Jeton unjeton){
    // avec le jeton si il n'existe pas deja
    if (jetonCourant==null){
        jetonCourant=unjeton;
        return true;
    }
    else {
        return false;
    }
}
/////////////////////////////////////////
 public Jeton recupererJeton(){
     Jeton retourjeton = jetonCourant;// on récupére la jetonCourant pour le retouné avant de le supprimer
     jetonCourant= null; 
    return retourjeton;
    } 
 /////////////////////////////////////////
 public boolean supprimerJeton(){
     if (jetonCourant==null){ // on supprime le jeton si c'est possible
         System.out.println("Il n'y avait pas de jeton ici...");
         return false;
     }
     else {
         jetonCourant = null;
         System.out.println("Le jeton a été désintégré !");
         return true;
     }
 }
///////////////////////////////////////// 
 public boolean placerTrouNoir(){
     if (trouNoir == true){ // on place une trou noir si il n'y en a pas
         return false;
     }
     else{
         trouNoir=true;
         return true;
     }
 }
///////////////////////////////////////// 
//  meme que placerTrouNoir
 public boolean placerDesintegrateur(){
     if (desintegrateur == true){
         return false;
     }
     else{
         desintegrateur=true;
         return true;
     }   
     
 }
/////////////////////////////////////////
 public boolean presenceTrouNoir(){
     return trouNoir;
 }
/////////////////////////////////////////    
                
  public boolean presenceDesintegrateur(){
     return desintegrateur;        
 }
/////////////////////////////////////////  
  public String lireCouleurDuJeton(){
      if (jetonCourant==null){ //si le jeton est null il n'as donc pas couleur donc on renvoie vide
          return "vide";
      }
      return jetonCourant.couleur; //renvoie la couleur du jeton
  }
/////////////////////////////////////////  
  public boolean recupererDesintegrateur(){
      // une fois le desintegrateur utilise on le passe a false
      if ( desintegrateur==true){
          desintegrateur=false;
          return true;
      }
      else{
          return false;
      }   
  }
 ///////////////////////////////////////// 
  public boolean activerTrouNoir(){
      if (trouNoir==false){
          return false;
      }
      else{
          jetonCourant= null;// si on active le trou noir on supprime la valeur du jeton
          trouNoir=false;
          System.out.println("Le trou noir a détruit le jeton !");
          return true;
      }
  }
  

/////////////////////////////////////////
  // nous permet de savor si il y a un jeton
  public boolean presenceJeton(){
      return jetonCourant!= null;
  }
  /////////////////////////////////////////
  public Jeton voirJeton() {
        return jetonCourant;
    }

}
/////////////////////////////////////////

