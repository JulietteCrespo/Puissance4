/*
 * Juliette CRESPO et Jeanne RICHOU
 * 02/11/2020
 * Classe Joueur - SuperPuissance4
 */
package superpuissance_crespo_richou;

/**
 *
 * @author julie
 */
 /////////////// Attributs ///////////////
public class Joueur {
    String nom;
    String couleur;
    Jeton ListeJeton [] = new Jeton[21]; // Tableau de type Jeton 
    int nombreDesintegrateurs;
    int nombreJetonsRestant;
 /////////////////////////////////////////
    
/////////////// Méthodes ///////////////
// Méthodes qui permet de définir le Joueur
// selon son nom    
   public Joueur(String unNom){
       nom=unNom;   
   }
/////////////////////////////////////////
// Méthode qui permet de définir la couleur
// de notre Joueur 
   public void affecterCouleur(String uneCouleur){
       couleur=uneCouleur;
   }
/////////////////////////////////////////
// Méthode qui permet d'ajouter un jeton 
// à la listeJeton des Joueur
   public boolean ajouterJeton(Jeton unJeton){
       if (nombreJetonsRestant==21){ // si il reste plus de 21 on ne peut pas rajouter de jetons
           return false;
       }
       else{
          ListeJeton[nombreJetonsRestant-1]=unJeton;
          return true; 
       }
   }
/////////////////////////////////////////
    public void obtenirDesintegrateur(){
        nombreDesintegrateurs++; // incrémente le nombre de desintegrateur  
    }
/////////////////////////////////////////   
    public boolean utiliserDesintegrateur(){
        if (nombreDesintegrateurs==0){ // ne peux pas décrémenté si nombreDesintegrateurs=0
            return false;
        }
        else {
            nombreDesintegrateurs--; // décrémente 
            return true;
        }
        
    }
    
}
/////////////////////////////////////////