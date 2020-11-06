/*
 * Juliette CRESPO et Jeanne RICHOU
 * 02/11/2020
 * Classe Partie - SuperPuissance4
 */

package superpuissance_crespo_richou;
import java.util.Random; // permet d'utiliser la fonction random
import java.util.Scanner;
/**
 *
 * @author julie
 */

/////////////// Attributs ///////////////
public class Partie {
    Joueur [] ListesJoueur = new Joueur[2];
    // Jeton [] ListeJetons = new Jeton [21];
    Joueur joueurCourant; // c'est le joueur qui est en train de jouer dans la partie
  
/////////////////////////////////////////
    
    
/////////////// Méthodes ///////////////     
    public void attribuerCouleursAuxJoueurs(){
        // on define aleatoirement la couleur du joueur 
        Random r = new Random();
        int n = 1+ r.nextInt(2); // on nombre aléatoire entre 1 et 2
        if (n==1){
           ListesJoueur[0].couleur="Rouge";
           ListesJoueur[1].couleur="Jaune"; 
        }
        else {
           ListesJoueur[0].couleur="Rouge";
           ListesJoueur[1].couleur="Jaune"; 
        }   
    }
/////////////////////////////////////////       
    public Grille initialiserPartie(){
        // 1- on créer la grille
        Grille newGrille = new Grille();
        //jjjjjjjjjjjjjjj
        
                
        // 2 - on vide la grille
        newGrille.viderGrille();
        // 3 - on place les trous noir et désintégrateur 
        // ------------ on défine 3 trous noirs au assard
        Random r = new Random();
        int ligneT = r.nextInt(6);
        int colonneT =r.nextInt(7);
        for (int k=0; k<3;k++){
           newGrille.placerTrouNoir(ligneT,colonneT);   
        }
        // ------------ on défine 2 trous noirs au assard qui cache des désintégrateurs 
        int ligneTD =r.nextInt(6);
        int colonneTD =r.nextInt(7);
        for (int k=0; k<2;k++){
           if (newGrille.placerTrouNoir(ligneTD,colonneTD)==true && newGrille.placerDesintegrateur(ligneTD, colonneTD)==true ){
               newGrille.placerTrouNoir(ligneTD,colonneTD );
               newGrille.placerDesintegrateur(ligneTD, colonneTD); 
           }
           else{
               k--; // si il y un deja un desintegrateur ou un troue noir on ne fait rien et on retir au hasrd une cellule
           }
        }
        // ------------ de la meme maniére de présedament, on défine 3 désintégrateurs
        int ligneD =r.nextInt(6);
        int colonneD =r.nextInt(7);
        for (int k=0; k<3;k++){
           if (newGrille.placerTrouNoir(ligneD,colonneD)==true && newGrille.placerDesintegrateur(ligneD, colonneD)==true ){
               newGrille.placerDesintegrateur(ligneD, colonneD); 
           }
           else{
               k--;
           }
        }
        // 4 - On créer les Jetons  
        Jeton JetonRouge =new Jeton("Rouge");
        Jeton JetonJaune = new Jeton("Jaune");
        // 5 - On remplie le tableau des joueurs avec les jetons de la meme couleur
        for (int j=0; j<2; j++){ // de boucle pour les deux joueurs
           for (int i =0; i<21; i++){
              if ("Rouge".equals(ListesJoueur[j].couleur)){
                 ListesJoueur[j].ajouterJeton(JetonRouge); // on ajoute les jetons au tableau
                }
                else {
                  ListesJoueur[j].ajouterJeton(JetonJaune);
                }
           } 
        }
        return newGrille;  
    }

////////////////////////////////////////    

    public void debuterPartie(){
     // 1- Création des Joueurs :
     Joueur newJoueur1 = new Joueur("Joueur1");
     Joueur newJoueur2 = new Joueur("Joueur2");
     ListesJoueur[0]=newJoueur1; // on definie listeJoueur avec nos nouveaux joueur 
     ListesJoueur[1]=newJoueur2;
     
     Scanner sc = new Scanner (System.in);
     System.out.println( " Entrer le spseudo du JOUEUR-1 : "); //ulisiteur saisie un speuso
     String Joueur1 =sc.nextLine(); // creer alors un premier joueur
     System.out.println(" Entrer le spseudo du JOUEUR-2 : ");
     String Joueur2 =sc.nextLine();
     ListesJoueur[0].nom=Joueur1; // on definie listeJoueur avec nos nouveaux joueur 
     ListesJoueur[1].nom=Joueur2;
     
     // 2- Définie les couleurs aux joueurs
     
     attribuerCouleursAuxJoueurs();
     System.out.println( ListesJoueur[0].nom + " est de couleur " + ListesJoueur[0].couleur);
     System.out.println( ListesJoueur[1].nom + " est de couleur " + ListesJoueur[1].couleur);
     
     // 3 - On attribue 21 jetons a chaque joueur
     ListesJoueur[0].nombreJetonsRestant=21;
     ListesJoueur[1].nombreJetonsRestant=21;
     
     // 4- On députer la partie
     System.out.println("---------------------------------------");
     Grille GrilleJeu ; // on recupére notre grille de jeu 
     GrilleJeu=initialiserPartie();
     
     //5- Quel joueur peux commencer ?
     Random r = new Random();
     int n = 1+ r.nextInt(2);
     if (n==1){
         joueurCourant = ListesJoueur[0];
     }
     else{
         joueurCourant = ListesJoueur[1]; 
     }
     System.out.println( joueurCourant.nom + " c'est à toi commencer ! " );
     int action1;
     // 6- On commence a jouer
     do{ 
         //-------- Affiche grille
         GrilleJeu.afficherGrilleSurConsole();
         //----- on consiede qu'il toujours des jetons
         //-------- On demande au personnenage on il veut faire 
         boolean findeboucle=false;
         do{
             
             System.out.println( joueurCourant.nom + " où veut tu faire ? " );
             System.out.println( " 1 - Jouer\n 2 - Récuperer un jeton\n 3 - Désintégrer " );
             String action=sc.nextLine();
             
             action1=Integer.parseInt(action);
         
             if (1==action1){ // Choisi de joueur
                 findeboucle=true;
                 int newcolonne;
                 do {                
                     //---- on demande ou il veut jouer
                     System.out.println( joueurCourant.nom + " où veut tu jouer ? " );
                     System.out.println( " colonne : " );
                     String colonne=sc.nextLine();
                     
                     newcolonne=Integer.parseInt(colonne);// on converti colonne en int
                     //on regarde si la colonne est pleine  
                    }while(GrilleJeu.colonneRemplie(newcolonne)==true);
                 // la collonne n'est pas pleine on peu ajouter le jeton
                 int newligne;//on recuper la ligne viser
                 newligne=GrilleJeu.ajouterJetonDansColonne(joueurCourant.ListeJeton[joueurCourant.nombreJetonsRestant-1], newcolonne);
                 joueurCourant.nombreJetonsRestant--; //on lui enlever donc le jeton
                 
                 // on verifie si il y a un trou noir ou/et un desintégrateur
                 if (GrilleJeu.grille[newligne][newcolonne].trouNoir==true && GrilleJeu.grille[newligne][newcolonne].desintegrateur==true){
                     GrilleJeu.grille[newligne][newcolonne].activerTrouNoir();
                     GrilleJeu.grille[newligne][newcolonne].recupererDesintegrateur();
                     joueurCourant.nombreDesintegrateurs++;
                 }
                 else if (GrilleJeu.grille[newligne][newcolonne].trouNoir==true){
                     GrilleJeu.grille[newligne][newcolonne].activerTrouNoir();
                 }
                 else if (GrilleJeu.grille[newligne][newcolonne].desintegrateur==true){
                     GrilleJeu.grille[newligne][newcolonne].recupererDesintegrateur();
                     joueurCourant.nombreDesintegrateurs++; 
                 }
                 
                 // on change de joueur
                 if (joueurCourant == ListesJoueur[1]){
                     joueurCourant = ListesJoueur[2];
                    }
                 else{
                     joueurCourant = ListesJoueur[1];
                    }
                 // l'autre joueur peux joueur
                }
             else if (action1==2){// Choisi de retiré un jeton
                 findeboucle=true;
                 int newcolonne;
                 int newligne;
                 do {
                 System.out.println( joueurCourant.nom + " quel jeton veux tu retirer ? " );
                 System.out.println( " colonne : " );
                 System.out.println( " ligne : " );
                 String colonne=sc.nextLine();
                 String ligne=sc.nextLine();
                 
                 newcolonne=Integer.parseInt(colonne);// on converti colonne en int
                 
                 newligne=Integer.parseInt(ligne);
                 }while(!GrilleJeu.grille[newligne][newcolonne].jetonCourant.couleur.equals(joueurCourant.couleur));
                 GrilleJeu.tasserGrille(newligne, newcolonne); //on tasse la grille
                 joueurCourant.nombreJetonsRestant++;//on lui ajoute le jeton retirer
                 joueurCourant.ListeJeton[joueurCourant.nombreJetonsRestant-1]=GrilleJeu.grille[newligne][newcolonne].jetonCourant;
                 // c'est au tour de l'autre joueur
                 if (joueurCourant == ListesJoueur[1]){
                     joueurCourant = ListesJoueur[2];
                    }
                 else{
                     joueurCourant = ListesJoueur[1];
                    }
                 
                 }
             else if (action1==3 && joueurCourant.nombreDesintegrateurs>0 ){  // désintégration
                 findeboucle=true;
                 int newcolonne;
                 int newligne;
                 do {
                 System.out.println( joueurCourant.nom + " quel jeton veux tu désintéger ? " );
                 System.out.println( " colonne : " );
                 System.out.println( " ligne : " );
                 String colonne=sc.nextLine();
                 String ligne=sc.nextLine();
                 
                 newcolonne=Integer.parseInt(colonne);// on converti colonne en int
                 
                 newligne=Integer.parseInt(ligne);
                 }while(GrilleJeu.grille[newligne][newcolonne].jetonCourant==null);
                 GrilleJeu.tasserGrille(newligne, newcolonne);
                 joueurCourant.nombreDesintegrateurs--;
                  // c'est au tour de l'autre joueur
                 if (joueurCourant == ListesJoueur[1]){
                     joueurCourant = ListesJoueur[2];
                    }
                 else{
                     joueurCourant = ListesJoueur[1];
                    }
                 
                 
                 }
             }while (findeboucle=!true);
         
         
         }while (GrilleJeu.etreRemplie()==false || GrilleJeu.etreGagnantePourJoueur(ListesJoueur[1]) || GrilleJeu.etreGagnantePourJoueur(ListesJoueur[2]));
     
        
        
}
}
/////////////////////////////////////////