package item

import personnage.Personnage


class Armure(
    nom: String,
    description: String,
    val type: TypeArmure,
    val qualite: Qualite,
) : Item(nom, description) {

    fun calculProtection(): Int {
        val protection = this.qualite.bonusQualite + this.type.BonusType // variable à potentiel changement
        println(protection)
        return protection
    }


    override fun utiliser(cible: Personnage) {
        println("$nom est utilisée contre ${cible.nom}.")
        // Ici, nous n'avons pas besoin de renvoyer de résultat, nous avons simplement effectué une action.
    }

}







