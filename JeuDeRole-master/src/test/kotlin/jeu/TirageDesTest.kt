package jeu

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class TirageDesTest {
    // Test unitaire pour le tirage de Des
    @Test
    fun lance() {
        // Création d'un objet TirageDes pour simuler un lancé de dés 3d9
        val des = TirageDes(3, 9)

        // Utilisation de la méthode lance() pour obtenir le résultat du lancé
        val resultat = des.lance()
        println(" result = $resultat")
        // Le résultat doit être compris entre 3 (minimum possible avec 3 dés) et 27 (maximum possible avec 3 dés à 9 faces)
        // valeur minimum = 3*1
        // valeur maximum = 3*9 =27
        Assertions.assertTrue(resultat >= 3)
        Assertions.assertTrue(resultat <= 27)
        // ou
        //Assertions.assertTrue(resultat in (3..27))

        Assertions.assertEquals("","")
        Assertions.assertNotEquals("a",'a')
        Assertions.assertNotNull("machin")

    }
}