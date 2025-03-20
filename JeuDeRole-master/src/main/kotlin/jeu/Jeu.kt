package jeu

import bouleFeu
import item.Sort
import personnage.Guerrier
import personnage.Mage
import personnage.Personnage
import personnage.Voleur
import projectionacide


class Jeu(monstres: List<Personnage>) {
    lateinit var joueur: Personnage
    var combats: MutableList<Combat> = mutableListOf()
    var score: Int = 0

    // Corps du constructeur
    init {
        // Lancement de la création du personage du joueur
        this.creerPersonnage()
        // Pour chaque monstre dans la liste de monstres
        for (unMonstre in monstres) {
            // On créer un combat
            val unCombat = Combat(this, unMonstre)
            combats.add(unCombat)
        }
        this.combats.shuffle() //le choix de l'adversaire est alétoire
    }

    fun lancerCombat() {
        for (unCombat in this.combats) {
            unCombat.executerCombat()
            // Mettre à jour le score en fonction du nombre de tours
            val tours = unCombat.nombreTours
            score += calculerScore(tours)
        }
        println("Score final du joueur: $score")
    }

    private fun calculerScore(tours: Int): Int {
        // Par exemple, vous pouvez attribuer plus de points pour moins de tours
        return 500 - tours * 10
    }

    /**
     *  Méthode pour créer le personnage du joueur en demandant les informations à l'utilisateur
     *
     */
    fun creerPersonnage(): Personnage {
        println("Création de votre personnage")

        // Demander le nom du personnage à l'utilisateur
        print("Entrez le nom de votre personnage: ")
        val nom = readLine() ?: ""

        var attaque = 0
        var defense = 0
        var endurance = 0
        var vitesse = 0
        var choix = 0
        var stats = 0


        // Demander à l'utilisateur de répartir 40 points entre attaque, défense, endurance et vitesse
        var pointsRestants = 40

        println("Vous avez 40 points à répartir entre l'attaque,la défense, la vitesse, l'endurance")
        println("Saissisez le chiffre adéquat pour répartir les points là où vous le souhaitez.")
        println("1. Attaque")
        println("2. Défense")
        println("3. Endurance")
        println("4. Vitesse")
        println("5. Ouvrir l'inventaire")
        println("6. Terminé")



        choix = readln().toInt()




        do {
            while (choix > 6) { // vérifie que l'utilisateur n'a pas saisi une valeure supérieure à 6 sinon il le refait saisir.
                println(
                    "Vous avez saisi un nombre trop grand." +
                            "Saissisez le chiffre adéquat pour répartir les points là où vous le souhaitez."
                )
                choix = readln().toInt()
            }

            if (choix == 1) { //
                println("stats:attaque")
                stats = readln().toInt()
                while (stats > pointsRestants) {
                    println(
                        "Vous avez saisi trop de points." +
                                "Resaissisez le nombre de points à affecter pour l'attaque"
                    )
                    stats = readln().toInt()
                }
                if (attaque > 0) {
                    pointsRestants += attaque
                }
                attaque = stats
                pointsRestants -= stats
                println(
                    "Avez-vous terminer ? " +
                            "Saissisez 6 si oui," +
                            "sinon saissisez une valeur entre 1 et 5 pour continuer." +
                            "Il vous reste $pointsRestants points"
                )
                choix = readln().toInt()
            } else if (choix == 2) {
                println("stats:défense")
                stats = readln().toInt()
                while (stats > pointsRestants) {
                    println(
                        "Vous avez saisi trop de points." +
                                "Resaissisez le nombre de points à affecter pour la défense:"
                    )
                    stats = readln().toInt()
                }
                if (defense > 0) {
                    pointsRestants += defense
                }
                defense += stats
                pointsRestants -= stats
                println(
                    "Avez-vous terminer ?" +
                            " Saissisez 6 si oui," +
                            " sinon saissisez une valeur entre 1 et 5 pour continuer." +
                            " Il vous reste $pointsRestants points"
                )
                choix = readln().toInt()
            } else if (choix == 3) {
                println("stats:Endurance")
                stats = readln().toInt()
                while (stats > pointsRestants) {
                    println(
                        "Vous avez saisi trop de points." +
                                "Resaissisez le nombre de points à affecter pour l'endurance:"
                    )
                    stats = readln().toInt()
                }
                if (endurance > 0) {
                    pointsRestants += endurance
                }
                endurance += stats
                pointsRestants -= stats
                println(
                    "Avez-vous terminer ?" +
                            " Saissisez 6 si oui," +
                            " sinon saissisez une valeur entre 1 et 5 pour continuer." +
                            " Il vous reste $pointsRestants points"
                )
                choix = readln().toInt()
            } else if (choix == 4) {
                println("stats:vitesse")
                stats = readln().toInt()
                while (stats > pointsRestants) {
                    println(
                        "Vous avez saisi trop de points." +
                                "Resaissisez le nombre de points à affecter pour la vitesse:"
                    )
                    stats = readln().toInt()
                }
                if (vitesse > 0) {
                    pointsRestants += vitesse
                }
                vitesse += stats
                pointsRestants -= stats
                println(
                    "Avez-vous terminer ?" +
                            "Saissisez 6 si oui," +
                            "sinon saissisez une valeur entre 1 et 5 pour continuer." +
                            "Il vous reste $pointsRestants points"
                )
                choix = readln().toInt()
            } else if (choix == 5) {
                println(
                    "Votre inventaire est vide pour l'instant. " +
                            "Saissisez une valeur entre 1 et 4 pour affecter des points sinon 6 pour Terminer "
                )
                choix = readln().toInt()
            }
            pointsRestants = 40 - (attaque + defense + vitesse + endurance)

        } while (choix in (1..5) || pointsRestants < 0)       // Calculer les points de vie maximum en fonction de l'endurance
        val pointsDeVieMax = 50 + endurance * 10

        // Saisie de la classe
        println("Choix de la classe, choisissez entre:")
        println("1.Guerrier")
        println("2.Voleur")
        println("3.Mage")
        var choixClasse = readln().toInt()
        while (choixClasse !in 1..3) { // Resaisie de la classe si le nombre entré est en dehors de 1 2 et 3.
            println("Choix de la classe, choisissez entre:")
            println("1.Guerrier")
            println("2.Voleur")
            println("3.Mage")
            var choixClasse = readln().toInt()
        }

        // Créer le personnage avec les valeurs saisies par l'utilisateur
        var hero = Personnage(
            nom,
            pointsDeVieMax,
            pointsDeVieMax,
            attaque,
            defense,
            endurance,
            vitesse,
            armePrincipal = null,
            armure = null,
            inventaire = mutableListOf()
        )
        if (choixClasse == 1)
            hero = Guerrier(
                nom,
                3000,
                3000,
                attaque,
                defense,
                endurance,
                vitesse,
                armePrincipal = null,
                armure = null,
                armeSecondaire = null
            )
        else if (choixClasse == 2)
            hero = Voleur(nom, 3000, 3000, attaque, defense, endurance, vitesse, armePrincipal = null, armure = null)
        else if (choixClasse == 3)
            hero = Mage(
                nom,
                3000,
                3000,
                attaque,
                defense,
                endurance,
                vitesse,
                armePrincipal = null,
                armure = null,
                grimmoire = mutableListOf<Sort>(projectionacide,bouleFeu)
            )
        // Valoriser l'attribut joueur avec le personnage créé et le retourner
        this.joueur = hero
        return hero
    }


}