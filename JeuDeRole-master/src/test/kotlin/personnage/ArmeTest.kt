package personnage

import item.Arme
import item.TypeArme
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions

import org.junit.jupiter.api.Assertions.*
import qualiteRare

class ArmeTest {

    @Test
    fun calculerDegats() {
        val armeTest = Arme("Legolas", "arc", Guandao, qualiteRare, 0)

        val resultat = armeTest.calculerDegats()

        println("Les degats subies sont de: $resultat")
        Assertions.assertTrue(resultat>=1)
        Assertions.assertNull("Legolas")
        Assertions.assertNull("arc")
        Assertions.assertTrue(Guandao is TypeArme)


    }
}