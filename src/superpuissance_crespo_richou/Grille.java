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
   /////////////////////////////////////////  
   public int ajouterJetonDansColonne(Jeton unJeton, int colonne) { //renvoie vrai si le jeton est mis dans la colonne
        int ligne;
        boolean resultat = false;
        int i = 5;
        while (!resultat && i > -1) {
            if (!grille[i][colonne].presenceJeton()) {
                grille[i][colonne].affecterJeton(unJeton);
                resultat = true;
            } else {
                i--;
            }
        }
        ligne = i;
        return ligne;
    }
/////////////////////////////////////////   
    public  boolean etreRemplie() { //Renvoie vrai quand la grille est pleine
        boolean check = true;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                if (!grille[i][j].presenceJeton()) {
                    check = false;
                }
            }
        }
        return check;
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
                  System.out.print(" T "); 
              }
              else if (grille[i][j].desintegrateur == true){
                  System.out.print(" D ");
              }
              else if (grille[i][j].jetonCourant ==null) {
                  System.out.print("   ");   
              }
              else {
                  System.out.print(grille[i][j].jetonCourant); //on peut faire ca car on a creer une méthode string au paravent
              }
           } 
           System.out.println("\u001B[47m " + (i+1) + " \u001B[40m");
       }
       for (int k=0;k<7;k++){
           System.out.print("\u001B[47m "+(k+1)+" \u001B[40m");
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

    boolean etreGagnantePourJoueur(Joueur unJoueur) {
        boolean verifie = false;
        boolean verifie2 = false;
        int compteur = 0;
        int i = 5;
        int j;
        String coul = unJoueur.couleur;
        // 1 - LIGNE : on verifie si il y a des lignes gagnantes
        while ((i > -1) && !verifie) { //Change de ligne quand toute la ligne a été vériié
            j = 0;
            while ((j < 4) && !verifie && !verifie2) { //Fait avancer sur la ligne
                for (int k = 0; k < 4; k++) { //Vérifie si il n'y a pas 4 jetons en ligne d'affilés
                    if (grille[i][j + k].voirJeton() != null) {

                        if (grille[i][j + k].voirJeton().lireCouleur().equals(coul)) { // vérifie si le jeton de la cellule ciblée a la meme couleur que le joueur
                            compteur++; //incrémente le compteur si c'est la bonne couleur

                        } else {
                            verifie2=true;
                            
                        }
                    }                  
                }
                if (compteur < 4) {
                        compteur = 0; // Le reset si il n'est pas arrivé à 4
                        verifie2=false;
                    } else {
                        verifie = true;
                        System.out.println( unJoueur.nom + "  tu as gagné ! " );
                    }
                j++;
            }
            i--;
        }
        // 2 - COLONNE : on verifie si il y a des colonnes gagnantes
        j = 0;
        verifie2=false;
        while ((j < 7) && !verifie) { //Change de colone quand toute la colonne a été vériié
            i = 0;
            while ((i < 3) && !verifie && !verifie2) { //Fait avancer sur la colonne
                for (int k = 0; k < 4; k++) { //Vérifie si il n'y a pas 4 jetons en colonne d'affilés
                    if (grille[i + k][j].voirJeton() != null) {

                        if (grille[i + k][j].voirJeton().lireCouleur().equals(coul)) { // vérifie si le jeton de la cellule ciblée a la meme couleur que le joueur 
                            compteur++; //incrémente le compteur si c'est la bonne couleur

                        } else {
                            verifie2=true;
                        }
                    }
                }
                if (compteur < 4) {
                    compteur = 0; // Le reset si il n'est pas arrivé à 4
                    verifie2=false;
                } else {
                    verifie = true;
                    System.out.println( unJoueur.nom + "  tu as gagné ! " );
                    
                }
                i++;
            }
            j++;
        }
        // 3 - DIAGONALE A PENTE POSITIVE : on verifie si il y a des diagonales a pente positive gagnante 
        i = 0;
        verifie2=false;
        while ((i < 3) && !verifie) { //Change de ligne quand toute la ligne a été vériié
            j = 0;
            while ((j < 4) && !verifie && !verifie2) { //Fait avancer sur la ligne
                for (int k = 0; k < 4; k++) { //Vérifie si il n'y a pas 4 jetons en ligne d'affilés
                    if (grille[i + k][j + k].voirJeton() != null) {
                        if (grille[i + k][j + k].voirJeton().lireCouleur().equals(coul)) { // vérifie si le jeton de la cellule ciblée a la meme couleur que le joueur 
                            compteur++; //incrémente le compteur si c'est la bonne couleur

                        } else {
                            verifie2=true;
                        }
                    }
                }
                if (compteur < 4) {
                    compteur = 0; // Le reset si il n'est pas arrivé à 4
                    verifie2=false;
                } else {
                    verifie = true;
                    System.out.println( unJoueur.nom + "  tu as gagné ! " );
                }
                j++;
            }
            i++;
        }
        // 4 - DIAGONALE A PENTE NEGATIVE : on verifie si il y a des diagonales a pente negative gagnant  
        i = 0;
        verifie2=false;
        while ((i < 3) && !verifie) { //Change de ligne quand toute la ligne a été vériié
            j = 6;
            while ((j > 4) && !verifie && !verifie2) { //Fait avancer sur la ligne
                for (int k = 0; k < 4; k++) { //Vérifie si il n'y a pas 4 jetons en ligne d'affilés
                    if (grille[i + k][j - k].voirJeton() != null) {
                        if (grille[i + k][j - k].voirJeton().lireCouleur().equals(coul)) { // vérifie si le jeton de la cellule ciblée a la meme couleur que le joueur 
                            compteur++; //incrémente le compteur si c'est la bonne couleur

                        } else {
                            verifie2=true;
                        }
                    }
                }
                if (compteur < 4) {
                    compteur = 0; // Le reset si il n'est pas arrivé à 4
                    verifie2=false;
                } else {
                    verifie = true;
                    System.out.println( unJoueur.nom + "  tu as gagné ! " );
                }
                j--;
            }
            i++;
        }
        
        return verifie;
    }
/////////////////////////////////////////    
    public void tasserGrille(int ligne, int colonne){
        for (int i= ligne; i> 0; i--){
            if (i==0){ // comme on tasse les ligne l derniere ligne sera forcement null
                grille[i][colonne].jetonCourant = null;
            }
            else{ //sinon on decale d'une ligne
                grille[i][colonne].jetonCourant = grille[i+1][colonne].jetonCourant;
                grille[i][colonne].jetonCourant = null;
            }
        }
    } 
 /////////////////////////////////////////   
    public boolean colonneRemplie( int colonne){
       if (grille[0][colonne].jetonCourant!=null){ 
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
    
 /////////////////////////////////////////   
     public void tasserColonne(int colonne){ //tasse les colonne
       // tasse quand il y a une case null
       // (decale tout les cases 
        for(int i=5; i>0;i--){
           if(grille[i][colonne].jetonCourant==null && grille[i-1][colonne].jetonCourant!=null){
               grille[i][colonne].jetonCourant=grille[i-1][colonne].jetonCourant;
               grille[i-1][colonne].jetonCourant=null;
           } 
        }
        
    }
}
/////////////////////////////////////////