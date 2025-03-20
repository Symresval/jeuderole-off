package item

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test


import personnage.Personnage

class BombeTest {

    @Test
    fun utiliser(personnage: Personnage) {
        val bombeTest = Bombe(nom = "" , description = "", nombreDeDes = 3, maxDe = 8)
        val jnoun = Personnage(
            "DjJnoun",
            pointDeVie = 20,
            pointDeVieMax = 216,
            attaque = 200,
            defense = 140,
            endurance = 90 ,
            vitesse =  110,null,null
        )
        bombeTest.utiliser(jnoun)
        var degat=20-jnoun.pointDeVie
        Assertions.assertEquals(1,degat)
    }
}