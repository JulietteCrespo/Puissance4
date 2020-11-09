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
           ListesJoueur[1].couleur="Rouge";
           ListesJoueur[0].couleur="Jaune"; 
        }   
    }
/////////////////////////////////////////       
    public Grille initialiserPartie(){
        // 1- on créer la grille
        Grille newGrille = new Grille(); 
        // 2 - on vide la grille
        newGrille.viderGrille();
        // 3 - on place les trous noir et désintégrateur 
        // ------------ on défine 3 trous noirs au assard
        Random r = new Random();
        
        for (int k=0; k<3;k++){
           int ligneT = r.nextInt(6);
           int colonneT =r.nextInt(7);
           if (newGrille.placerTrouNoir(ligneT,colonneT)==true){
            newGrille.placerTrouNoir(ligneT,colonneT); 
           }
           
        }
        
        // ------------ on défine 2 trous noirs au assard qui cache des désintégrateurs 
        
        for (int k=0; k<2;k++){
            int ligneTD =r.nextInt(6);
            int colonneTD =r.nextInt(7);
           if (newGrille.grille[ligneTD][colonneTD].trouNoir==false && newGrille.grille[ligneTD][colonneTD].desintegrateur==false ){
               
               newGrille.placerTrouNoir(ligneTD,colonneTD );
               newGrille.placerDesintegrateur(ligneTD, colonneTD);
              
           }
           else{
               k--; // si il y un deja un desintegrateur ou un troue noir on ne fait rien et on retir au hasrd une cellule
           }
        }
        // ------------ de la meme maniére de présedament, on défine 3 désintégrateur
        for (int k=0; k<3;k++){
            int ligneD =r.nextInt(6);
            int colonneD =r.nextInt(7);
           if (newGrille.grille[ligneD][colonneD].trouNoir==false && newGrille.grille[ligneD][colonneD].desintegrateur==false){
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
        for(int k=0;k<2;k++){ // pour les deux joueurs
           for (int i =0; i<21; i++){
           if ("Rouge".equals(ListesJoueur[k].couleur)){
                 ListesJoueur[k].ListeJeton[i]= JetonRouge;// on ajoute les jetons au tableau
                 //System.out.println(ListesJoueur[k].couleur);
                }
                else {
                  ListesJoueur[k].ListeJeton[i]= JetonJaune;
                  //System.out.println(ListesJoueur[k].couleur);
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
     System.out.println( joueurCourant.nom + " -> c'est à toi commencer ! " );
     int action1;
     //-------- Affiche grille
     GrilleJeu.afficherGrilleSurConsole();
     
     // 6- On commence a jouer
     while (GrilleJeu.etreRemplie()==false && GrilleJeu.etreGagnantePourJoueur(ListesJoueur[1])==false && GrilleJeu.etreGagnantePourJoueur(ListesJoueur[0])==false){ 
         
         //----- on consiede qu'il toujours des jetons
         //-------- On demande au personnenage on il veut faire 
         boolean findeboucle=false;
         int action;
         
         
             action=menu();
             if ( action==1){ 
                
                 findeboucle=true;
                 //---- on demande ou il veut jouer
                 System.out.println( joueurCourant.nom + " où veut tu jouer ? " );
                 System.out.println( " colonne : " );
                 int colonne=sc.nextInt();
                 // vérifie si la saisie est vrai
                 while (colonne>7 || colonne<0){
                     System.out.println( "Erreur de saisie veillez resaisir :" );
                     colonne=sc.nextInt();
                 }
                 // vérifie si la conne est pleine
                 int newcolonne=colonne-1;
                 boolean resultat;
                 resultat=GrilleJeu.colonneRemplie(newcolonne);
                 if (resultat==true){
                     while(resultat){
                         System.out.println( "La colonne est pleine veillez resaisir :" );
                         newcolonne=sc.nextInt()-1;
                          resultat=GrilleJeu.colonneRemplie(newcolonne);
                        }
                 }
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
                 if (joueurCourant == ListesJoueur[0]){
                     joueurCourant = ListesJoueur[1];
                    }
                 else{
                     joueurCourant = ListesJoueur[0];
                    }
                 // l'autre joueur peux joueur
                 //-------- Affiche grille avec le changement
             
                 GrilleJeu.afficherGrilleSurConsole();
                }
             else if (action==2){// Choisi de retiré un jeton
                 
                 int colonne;
                 int ligne;
                 System.out.println( joueurCourant.nom + " Quel jeton voulez-vous récupérer ? " );
                 System.out.println( " colonne : " );
                 System.out.println( " ligne : " );
                 colonne=sc.nextInt()-1;
                 ligne=sc.nextInt()-1;
                 while (colonne>7 || colonne<0 || ligne<0 || ligne>6 ){
                     System.out.println( "Erreur de saisie veillez resaisir :" );
                     System.out.println( " colonne : " );
                     System.out.println( " ligne : " );
                     colonne=sc.nextInt()-1;
                     ligne=sc.nextInt()-1;
                 }
                 
                 // on verifie si il peut recuperer le jeton en question
                 while(GrilleJeu.grille[ligne][colonne].jetonCourant==null){
                     System.out.println( "Erreur: Il n'y a pas de jeton veillez resaisir " );
                     System.out.println( " colonne : " );
                     System.out.println( " ligne : " );
                     colonne=sc.nextInt()-1;
                     ligne=sc.nextInt()-1; 
                 }
                 // on vérifie que se soit de la bonne couleur
                 while(!GrilleJeu.grille[ligne][colonne].jetonCourant.couleur.equals(joueurCourant.couleur)){
                    System.out.println( "Erreur: Vous ne pouvez pas récuperer ce jeton " );
                     System.out.println( " colonne : " );
                     System.out.println( " ligne : " );
                     colonne=sc.nextInt()-1;
                     ligne=sc.nextInt()-1;  
                 }
                
                 
                 GrilleJeu.grille[ligne][colonne].jetonCourant=null;
                 GrilleJeu.tasserColonne(colonne); //on tasse la grille
                 
                 joueurCourant.nombreJetonsRestant++;//on lui ajoute le jeton retirer
                 joueurCourant.ListeJeton[joueurCourant.nombreJetonsRestant-1]=GrilleJeu.grille[ligne][colonne].jetonCourant;
                 // c'est au tour de l'autre joueur
                 if (joueurCourant == ListesJoueur[1]){
                     joueurCourant = ListesJoueur[0];
                    }
                 else{
                     joueurCourant = ListesJoueur[1];
                    }
                 //-------- Affiche grille avec le changement
                 GrilleJeu.afficherGrilleSurConsole();
                 }
             else if ( action==3 && joueurCourant.nombreDesintegrateurs==0 ){
                 System.out.println( joueurCourant.nom + " Vous ne pouvez pas faire cette action car vous n'avais pas de désintégrateur veuillez faire une autre action" ); 
                if (joueurCourant == ListesJoueur[1]){
                     joueurCourant = ListesJoueur[1];
                    }
                 else{
                     joueurCourant = ListesJoueur[0];
                    } 
             }
             else if ( action==3 && joueurCourant.nombreDesintegrateurs>0 ){  // désintégration
                 
                 int colonne;
                 int ligne;
                 System.out.println( joueurCourant.nom + " Quel jeton voulez-vous désintégrer ? " );
                 System.out.println( " colonne : " );
                 System.out.println( " ligne : " );
                 colonne=sc.nextInt()-1;
                 ligne=sc.nextInt()-1;
                 while (colonne>7 || colonne<0 || ligne<0 || ligne>6 ){
                     System.out.println( "Erreur de saisie veillez resaisir :" );
                     System.out.println( " colonne : " );
                     System.out.println( " ligne : " );
                     colonne=sc.nextInt()-1;
                     ligne=sc.nextInt()-1;
                 }
                 // on verifie si le jeton n'est pas nul
                 while(GrilleJeu.grille[ligne][colonne].jetonCourant==null){
                     System.out.println( "Erreur: Il n'y a pas de jeton veillez resaisir " );
                     System.out.println( " colonne : " );
                     System.out.println( " ligne : " );
                     colonne=sc.nextInt()-1;
                     ligne=sc.nextInt()-1; 
                 }
                 GrilleJeu.grille[ligne][colonne].jetonCourant=null;
                 GrilleJeu.tasserColonne(colonne);
                 joueurCourant.nombreDesintegrateurs--;
                  // c'est au tour de l'autre joueur
                 if (joueurCourant == ListesJoueur[1]){
                     joueurCourant = ListesJoueur[0];
                    }
                 else{
                     joueurCourant = ListesJoueur[1];
                    }
                 //-------- Affiche grille avec le changement
                 GrilleJeu.afficherGrilleSurConsole();
                 
                 }
         }
     
       
        
}
    
 public int menu(){ // permet de demander au joeur se qu'il veut faire
     Scanner sc = new Scanner (System.in);
     System.out.println( joueurCourant.nom + " que veut tu faire ? " );
     System.out.println( " 1 - Jouer\n 2 - Récuperer un jeton\n 3 - Désintégrer " );
     int action=sc.nextInt();
     while (action>3 || action<1){
         System.out.println( "Erreur de saisie veillez resaisir :" );
         action=sc.nextInt();
     }
     return action;
 }    
   
}
/////////////////////////////////////////