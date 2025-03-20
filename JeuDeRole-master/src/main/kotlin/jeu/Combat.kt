package jeu

import item.Bombe
import personnage.Mage
import personnage.Personnage
import personnage.Voleur

class Combat(
    val jeu :Jeu,
    val monstre: Personnage,
)

{
    var nombreTours: Int = 1

    // Méthode pour simuler un tour de combat du joueur
    fun tourDeJoueur() {

        println("\u001B[34m ---Tour de ${this.jeu.joueur.nom} (pv: ${this.jeu.joueur.pointDeVie}) ---")

        println("choissiez une action :")
        println("0.Attaquer")
        println("1.Passer")
        println("2.Boire Potion")
        println("3.Ouvrir inventaire")
        if (this.jeu.joueur is Voleur)
            println("4.Voler")
        if (this.jeu.joueur is Mage)
            println("5.Lancer un sort")


        val choix = readln().toInt() //saisir une valeur disponible

        when (choix) {
            0 -> {
                "attaquer"
                this.jeu.joueur.attaque(monstre)
                //lorsque la valeur est 0 l'action est attaquer
            }
            1 ->{
                "passer"
                this.jeu.joueur.passer(monstre)

                //l'action sera "defense"
            }
            // ajoutez d'autres actions

            2 ->{
                "boire potion"
                this.jeu.joueur.boirePotion()

            //l'action sera "boire une potion"
            }
            3 ->{
                "ouvrir inventaire"

                this.jeu.joueur.afficherInventaire()
                val selection:Int = readln().toInt()
                val objet= this.jeu.joueur.inventaire[selection]
                if (objet is Bombe){
                    objet.utiliser(monstre)
                }
                else{
                    objet.utiliser(this.jeu.joueur)
                }


            }
            4->{
                "Voler"
                val Voleur= this.jeu.joueur as Voleur
                Voleur.volerItem(monstre)

            }
            5->{
                "Lancer un sort"
                val Mage= this.jeu.joueur as Mage
                Mage.choisirEtLancerSort(this.jeu.joueur,this.monstre)
            }
            else -> "action invalide" //si une valeur ne fait pas parti des actions un message d'erreur sera affiché

        }


        println("\u001b[0m")
    }

    // Méthode pour simuler un tour de combat du monstre
    fun tourDeMonstre() {
        println("\u001B[31m---Tour de ${monstre.nom} (pv: ${monstre.pointDeVie}) ---")
        //choix d'action pour le monstre
        val num = TirageDes(1,100).lance()
        if (num< 70) {
            println("${monstre.nom} decide de d'attaquer")
            this.monstre.attaque(this.jeu.joueur)  }
        else if(this.monstre.avoirPotion() && this.monstre.pointDeVie < this.monstre.pointDeVieMax / 2 && num <=80) {
                this.monstre.boirePotion()
             }
        else{
                println("${monstre.nom} decide de passer son tour")
            }



   println("\u001b[0m")

    }

    // Méthode pour exécuter le combat complet
    fun executerCombat() {
        println("Début du combat : ${this.jeu.joueur.nom} vs ${monstre.nom}")
        //La vitesse indique qui commence
        var tourJoueur = this.jeu.joueur.vitesse >= this.monstre.vitesse
        //Tant que le joueur et le monstre sont vivants
        while (this.jeu.joueur.pointDeVie > 0 && monstre.pointDeVie > 0) {
            println("Tours de jeu : ${nombreTours}")
            if (tourJoueur) {
                tourDeJoueur()
            } else {
                tourDeMonstre()
            }
            nombreTours++
            tourJoueur = !tourJoueur // Alternance des tours entre le joueur et le monstre
            println("${this.jeu.joueur.nom}: ${this.jeu.joueur.pointDeVie} points de vie | ${monstre.nom}: ${monstre.pointDeVie} points de vie")
            println("")
        }

        if (this.jeu.joueur.pointDeVie <= 0) {
            println("Game over ! ${this.jeu.joueur.nom} a été vaincu !")
            println("Le combat recommence")

            this.jeu.joueur.pointDeVie = this.jeu.joueur.pointDeVieMax
            this.monstre.pointDeVie = this.monstre.pointDeVieMax
            this.executerCombat()
        } else {
            println("BRAVO ! ${monstre.nom} a été vaincu !")
            this.jeu.joueur.loot(this.monstre)
        }

    }
}