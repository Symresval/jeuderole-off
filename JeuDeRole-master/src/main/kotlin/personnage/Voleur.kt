package personnage

import item.Arme
import item.Armure
import item.Item


class Voleur(
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

    fun volerItem(cible: Personnage) {
        if (cible.inventaire.size > 0) { // si l'inventaire n'est pas vide
            val indexAlea =
                (0..cible.inventaire.lastIndex).random()//tire un nombre aléatoire entre 0 et le dernier index de l'inventaire de la cible

            val itemVoler =
                cible.inventaire.removeAt(indexAlea) // itemVoler à enlever l'objet et devient l'objet supprimer

            this.inventaire.add(itemVoler) //l'item volé apparait maintenant dans l'inventaire du joueur

            if (itemVoler == cible.armePrincipal) { //si l'item volé est une arme
                cible.armePrincipal = null
            } else if (itemVoler == cible.armure) {//si l'item volé est une armure
                cible.armure = null
            }
            println("Bravo, vous avez voler $itemVoler à un innocent")
        } else { //si l'inventaire est vide
            println("L'inventaire de l'adversaire est vide, tu ne peux rien voler lol ratio hurle,chiale,hulule,cri,miaule,aboie,tarpin faible ")
        }
    }
//    override fun toString(): String {
//        return "Voleur(${super.toString()}, grimmoire=$grimmoire)"
//    }
}




