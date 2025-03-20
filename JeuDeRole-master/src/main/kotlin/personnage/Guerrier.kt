package personnage

import item.Arme
import item.Armure
import item.Item

class Guerrier constructor(
    nom: String,
    pointDeVie: Int,
    pointDeVieMax: Int,
    attaque: Int,
    defense: Int,
    endurance: Int,
    vitesse: Int,
    armePrincipal: Arme?,
    var armeSecondaire: Arme?,
    armure: Armure?,
    inventaire: MutableList<Item> = mutableListOf(),
) : Personnage(
    nom,
    pointDeVie,
    pointDeVieMax,
    attaque,
    defense,
    endurance,
    vitesse,
    armePrincipal,
    armure,
    inventaire
) {
//    fun afficherarmes() {
//
//    }

    override fun equipe(armeA: Arme) {
        println("$nom, vous avez deux armes, saisissez 1 ou 2 pour choisir laquelle de ces armes sera l'arme équipée.:")
        println("1. ${armePrincipal?.nom}")
        println("2. ${armeSecondaire?.nom}")
        val choix2 = readln().toInt()

        if (choix2 == 1) {
            super.equipe(armeA)
        } else if (choix2 == 2 && armeA in this.inventaire) {
            this.armeSecondaire = armeA
        }
    }

    override fun attaque(adversaire: Personnage) {//Attaque arme secondaire
        super.attaque(adversaire)
        if (armeSecondaire != null) {
            var degats = this.armeSecondaire!!.calculerDegats() + this.attaque - adversaire.defense
            if (degats > 1) {
                adversaire.pointDeVie -= degats
                println("$nom attaque ${adversaire.nom} avec une attaque de base et inflige $degats points de dégâts.")
            } else {
                degats = 1
                adversaire.pointDeVie -= degats
                println("$nom attaque ${adversaire.nom} avec une attaque de base et inflige $degats points de dégâts.")
            }
        } else {
            if (this.attaque > 0) {
                var degats = (this.attaque / 2) - adversaire.defense
                if (degats > 1) {
                    adversaire.pointDeVie -= degats
                    println("$nom attaque ${adversaire.nom} avec une attaque de base et inflige $degats points de dégâts.")
                } else {
                    degats = 1
                    adversaire.pointDeVie -= degats
                    println("$nom attaque ${adversaire.nom} avec une attaque de base et inflige $degats points de dégâts.")
                }
            }
        }

    }
//   override fun toString(): String {
//       return "Guerrier(${super.toString()}, armeSecondaire =$armeSecondaire)"
//   }


}

