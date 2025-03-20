package personnage


import item.Arme
import item.Armure
import item.Item
import item.Sort

class Mage( //on definit la class Sage pour l`utiliser comme classe dans jeu
    nom: String,
    pointDeVie: Int,
    pointDeVieMax: Int,
    attaque: Int,
    defense: Int,
    endurance: Int,
    vitesse: Int,
    armePrincipal: Arme?,
    armure: Armure?,
    inventaire: MutableList<Item> = mutableListOf(),
    private val grimmoire:MutableList<Sort> = mutableListOf() ) :Personnage(nom, pointDeVie, pointDeVieMax, attaque, defense, endurance, vitesse, armePrincipal, armure, inventaire) {


    fun afficheGrimoire() {
        println("Inventaire du Grimoire")
        for (i in 0 until this.grimmoire.size) {
            println("${i+1}.${this.grimmoire[i]}") //on parcours le grimoire pour afficher le sort "i"
        }
    }

    fun choisirEtLancerSort(jeu: Personnage, monstre: Personnage) {
        if (grimmoire.isEmpty()) {
            println("Le grimoire est vide.")
            return
        }

        do {
            afficheGrimoire()

            print("Saisir un sort (0 pour annuler) : ")
            val x = readln().toInt()

            if (x != 0) {
                if (x in 0 until grimmoire.size) {
                    val sort = grimmoire[x]

                    var choix: Int
                    do {
                        print("Choisir la cible (0 pour le joueur, 1 pour le monstre) : ")
                        choix = readLine()?.toIntOrNull() ?: -1

                        when (choix) {
                            0 -> {
                                sort.effet(this, this)
                                println("Le sort est lancé sur le joueur.")
                            }

                            1 -> {
                                sort.effet(this, monstre)
                                println("Le sort est lancé sur le monstre.")
                            }

                            else -> println("Choix de cible invalide.")
                        }
                    } while (choix !in 0..1)
                } else {
                    println("Choix de sort invalide.")
                }
            } else {
                println("Lancement du sort annulé.")
            }
        } while (x != 0)
    }


    override fun toString(): String {
        return "Mage(${super.toString()}, grimmoire=$grimmoire)"
    }

}










