/*
 * Juliette CRESPO et Jeanne RICHOU
 * 02/11/2020
 * Classe Grille - SuperPuissance4
 */

package superpuissance_crespo_richou;
/**
 *
 * @author julie
 */

/////////////// Attributs ///////////////
public class Grille {
    Cellule grille[][]= new Cellule[6][7];
/////////////////////////////////////////
    
/////////////// Méthodes /////////////// 
 // initialisation de grille
  Grille(){
      for (int i=0;i<6;i++){ // Double boucle imbriquer qui nous permet de parcourrir tout le tableau
           for(int j=0;j<7;j++){
               grille[i][j]= new Cellule();
            }  
        }
    }
    
   public int  ajouterJetonDansColonne(Jeton unJeton, int nbcolonne){
       int i;
       for (i=5;i>=0;i--){ // on fait une boucle inversé car pour un puissance4 le premier jeton se retrouvera a la derniére ligne de notre grille
           if (grille[i][nbcolonne].jetonCourant==null){ // verifie si on peut ajouter le jeton si la grille est null 
               grille[i][nbcolonne].jetonCourant=unJeton;
               return i;
           }   
       }
       return i;
   }
/////////////////////////////////////////   
   public boolean etreRemplie(){
       int i;
       int j;
       for (i=0;i<6;i++){ // Double boucle imbriquer qui nous permet de parcourrir tout le tableau
           for(j=0;j<7;j++){
              if (grille[i][j]==null) { // il si il y a une cellule null la grille n'est pas vide
                  return false;
              }
           }
       }
       return true;
   }
/////////////////////////////////////////   
   public void 	viderGrille(){
       int i;
       int j;
       for (i=0;i<6;i++){
           for(j=0;j<7;j++){ // double boucle qui permet de mettre les cellules a null
               // on mets grille[i][j] a null
            
                grille[i][j].jetonCourant=null; 
                grille[i][j].trouNoir=false;
                grille[i][j].desintegrateur=false;  
           }
       }
   }
/////////////////////////////////////////     
   public void afficherGrilleSurConsole() {
       int i;
       int j;
       for (i=0;i<6;i++){
           for(j=0;j<7;j++){  
              if (grille[i][j].trouNoir == true){ //on affiche d'abord les trou noir car un trou noir peu caché un desintegrateur
                  System.out.print(" T"); 
              }
              else if (grille[i][j].desintegrateur == true){
                  System.out.print(" D");
              }
              else if (grille[i][j].jetonCourant ==null) {
                  System.out.print(" N");   
              }
              else {
                  System.out.print(grille[i][j].jetonCourant); //on peut faire ca car on a creer une méthode string au paravent
              }
           } 
           System.out.println(" "+(i+1));
       }
       for (int k=0;k<7;k++){
           System.out.print(" "+(k+1));
       }
       System.out.println();
   }
/////////////////////////////////////////   
   public boolean celluleOccupee(int i,	int j){
       if ( grille[i][j].jetonCourant==null){
           return false;
       }
       else {
           return true;
       }
   }
/////////////////////////////////////////   
   public String lireCouleurDuJeton(int i, int j){
       return grille[i][j].jetonCourant.couleur;
   }
/////////////////////////////////////////   
    public boolean etreGagnantePourJoueur(Joueur unJoueur){
       String couleurG= unJoueur.couleur; //on défini la couleur du joueur gagnant
       // 1 - LIGNE : on verifie si il y a des lignes gagnantes
        for (int i=0;i<4;i++){ // pas besoin n'alller jusqu'a la ligne 6 car on verifie deja 
           for(int j=0;j<7;j++){
              if (grille[i][j].jetonCourant.couleur==grille[i+1][j].jetonCourant.couleur && grille[i][j].jetonCourant.couleur==grille[i+2][j].jetonCourant.couleur && grille[i][j].jetonCourant.couleur==grille[i+3][j].jetonCourant.couleur ) {
                   if (grille[i][j].jetonCourant.couleur==couleurG){
                    return true;   
                   }
                }
            }
       } 
        // 2 - COLONNE : on verifie si il y a des colonnes gagnantes
        for (int i=0;i<6;i++){
           for(int j=0;j<3;j++){
              if (grille[i][j].jetonCourant.couleur==grille[i][j+1].jetonCourant.couleur && grille[i][j].jetonCourant.couleur==grille[i][j+2].jetonCourant.couleur && grille[i][j].jetonCourant.couleur==grille[i][j+3].jetonCourant.couleur ) {
                  if (grille[i][j].jetonCourant.couleur==couleurG){
                    return true;   
                   }
                }
            }
       } 
        // 3 - DIAGONALE A PENTE POSITIVE : on verifie si il y a des diagonales a pente positive gagnante   
        for (int i=0;i<3;i++){
           for(int j=0;j<4;j++){
              if (grille[i][j].jetonCourant.couleur==grille[i+1][j+1].jetonCourant.couleur && grille[i][j].jetonCourant.couleur==grille[i+2][j+2].jetonCourant.couleur && grille[i][j].jetonCourant.couleur==grille[i+3][j+3].jetonCourant.couleur ) {
                  if (grille[i][j].jetonCourant.couleur==couleurG){
                    return true;   
                   }
                }
            }
       }  
        // 4 - DIAGONALE A PENTE NEGATIVE : on verifie si il y a des diagonales a pente negative gagnant  
      for (int i=6;i>3;i--){
           for(int j=7;j>4;j--){
              if (grille[i][j].jetonCourant.couleur==grille[i-1][j-1].jetonCourant.couleur && grille[i][j].jetonCourant.couleur==grille[i-2][j-2].jetonCourant.couleur && grille[i][j].jetonCourant.couleur==grille[i-3][j-3].jetonCourant.couleur ) {
                  if (grille[i][j].jetonCourant.couleur==couleurG){
                    return true;   
                   }
                }
            }
       }    
    return false;  
    }
/////////////////////////////////////////    
    public void tasserGrille(int ligne, int colonne){
        for (int i= ligne; i< 6; i++){
            if (i==5){ // comme on tasse les ligne l derniere ligne sera forcement null
                grille[i][colonne].jetonCourant = null;
            }
            else{ //sinon on decale d'une ligne
                grille[i][colonne].jetonCourant = grille[i+1][colonne].jetonCourant;
            }
        }
    } 
 /////////////////////////////////////////   
    public boolean colonneRemplie( int colonne){
       int c; // on creer un compteur
       c=0;
       for (int i=0;i<6;i++){
           if (grille[i][colonne]==null){
               return false;
                }
            else {
               c++; // si la cellule n'est pas vide on ajoute au compteur
             }
        }
       if (c==6){ // si c=6 alors la ligne est remplie 
        return true;
        }
    return false;   
    }
/////////////////////////////////////////     
    public boolean placerTrouNoir(int i,int j ){
        if (grille[i][j].trouNoir == true ){
            return false;
        }
        else{
            grille[i][j].trouNoir = true;
            return true;
        }           
    }
/////////////////////////////////////////    
    public boolean placerDesintegrateur(int i,int j){
        if (grille[i][j].desintegrateur == true ){
            return false;
        }
        else{
            grille[i][j].desintegrateur = true;
            return true;
        }     
    }
/////////////////////////////////////////    
    public boolean supprimerJeton(int i, int j){
        if (grille[i][j].jetonCourant == null ){
            return false;
        }
        else{
            grille[i][j].jetonCourant = null;
            return true;
        }   
    }
/////////////////////////////////////////    
    public Jeton recupererJeton(int i, int j){
        Jeton recupJeton = grille[i][j].recupererJeton();
        grille[i][j].supprimerJeton();
        return recupJeton;
        
    }   
}
/////////////////////////////////////////