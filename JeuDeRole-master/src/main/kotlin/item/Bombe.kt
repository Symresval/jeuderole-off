package item

import jeu.TirageDes
import personnage.Personnage

class Bombe constructor(
    nom: String,
    var nombreDeDes: Int,
    var maxDe: Int,
    description: String) :
    Item(nom, description) {

    override fun utiliser(cible: Personnage) {
        val des = TirageDes(nombreDeDes, maxDe)
        val degats = des.lance()

        val protectionCible = cible.calculeDefense()
        val degatsApresProtection = maxOf(degats - protectionCible, 1)

        cible.pointDeVie -= degatsApresProtection

        println("La bombe a fait $degatsApresProtection points de dégâts à la cible.")
    }

    override fun toString(): String {
        return "Bombe(${super.toString()}, nombreDeDes=$nombreDeDes)"
    }
}

