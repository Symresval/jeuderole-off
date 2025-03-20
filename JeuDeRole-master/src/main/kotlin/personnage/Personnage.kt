package personnage

import item.*


open class Personnage(
    val nom: String,
    var pointDeVie: Int,
    val pointDeVieMax: Int,
    var attaque: Int,
    var defense: Int,
    var endurance: Int,
    var vitesse: Int,
    var armePrincipal: Arme?,
    var armure: Armure?,
    var inventaire: MutableList<Item> = mutableListOf(),

    ) {

    fun avoirPotion(): Boolean {
        for (element in this.inventaire) { // si il y a une potion on retourne vrai sinon faux
            if (element is Potion) {
                return true
            }
        }
        return false
    }

    fun avoirbombe(): Boolean {
        for (element in this.inventaire) { // si il y a une bombe on retourne vrai sinon faux
            if (element is Bombe) {
                return true
            }
        }
        return false
    }

    fun boirePotion() {
        if (avoirPotion() == true) {
            var iPremierePotion = -1
            for (i in 0..this.inventaire.lastIndex) { //parcours l'inventaire pour recuperer un item

                if (this.inventaire[i] is Potion) { // verifie si l'item est une potion

                    val potion: Potion = this.inventaire[i] as Potion
                    iPremierePotion = potion.soin

                    this.inventaire.remove(potion)
                    break

                }
            }


            pointDeVie += iPremierePotion  //calcule point de vie + potion
            if (pointDeVie > pointDeVieMax) {  //
                this.pointDeVie = pointDeVieMax

            }

            println("Vous avez $iPremierePotion de point de santé avec cette potion.")

        }

    }

    fun calculeDefense(): Int {
        //TODO Mission 4.2
        var result = this.defense / 2;
        if (armure != null) {
            result + this.armure!!.calculProtection()
        }
        return result;
    }

    open fun equipe(armureA: Armure) {

        if (armureA in this.inventaire && armureA is Armure)
            this.armure = armureA
        println("$nom équipe ${armureA.nom}")


    }

    open fun equipe(armeA: Arme) {
        if (armeA in this.inventaire && armeA is Arme)
            this.armePrincipal = armeA
        println("$nom équipe ${armeA.nom}")
    }


    // Méthode pour attaquer un adversaire
    open fun attaque(adversaire: Personnage) {
        //TODO Mission 4.1
        if (armePrincipal != null) { //Si une arme est équipée, on additionne ses dégats aux dégats totaux.
            var degats = this.armePrincipal!!.calculerDegats() + (this.attaque / 2)
            degats -= adversaire.calculeDefense()
            if (degats <= 0) {
                degats = 1
            }
            adversaire.pointDeVie -= degats
            println("$nom attaque ${adversaire.nom} avec son arme ${armePrincipal} et inflige $degats points de dégâts.")
        } else { //Si une arme n'est pas équipée alors un 1 point de dégats sera ingligée
            var degats = this.attaque / 2
            degats -= adversaire.calculeDefense()
            if (degats <= 0) {
                degats = 1
            }
            println("$nom attaque ${adversaire.nom} avec une attaque de base et inflige $degats points de dégâts.")
        }
    }

    // Méthode pour passer le tour
    fun passer(adversaire: Personnage) {

        println("${this.nom} passe son tour")

    }

    fun afficherInventaire() {
        println("Inventaire de $nom")
        if (!this.inventaire.isEmpty()) {
            println("Selectionnez un item")
            for (i in 0 until this.inventaire.size) {
                println("$i.${this.inventaire[i]}")
            }
        } else {
            println("l'inventaire est vide")
        }
    }

    fun loot(adversaire: Personnage) { //cette méthode a pour but de récupérer les armes de l'adversaire battu
        if (adversaire.pointDeVie <= 0) {
            armePrincipal == null
            armure == null
            this.inventaire.addAll(adversaire.inventaire)
            adversaire.inventaire = mutableListOf() // parcours l'inventaire et récupère

        }
    }


    override fun toString(): String {
        return "$nom (PV: $pointDeVie/$pointDeVieMax, Attaque: $attaque, Défense: $defense, Endurance: $endurance, Vitesse: $vitesse)"
    }


}