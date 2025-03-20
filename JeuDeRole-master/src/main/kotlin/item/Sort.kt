package item

import personnage.Personnage

open class Sort(
    val nom: String,
    val effet: (Personnage, Personnage) -> Unit
) {


    override fun toString(): String {
        return "Sort(nom=$nom)"
    }
}