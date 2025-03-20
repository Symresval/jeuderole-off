package item

import personnage.Personnage

class Potion constructor(nom: String, var soin: Int, description: String) : Item(nom, description) {


    override fun utiliser(cible: Personnage) {

    }

}