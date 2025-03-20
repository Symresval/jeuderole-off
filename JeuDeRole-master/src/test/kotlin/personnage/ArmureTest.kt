package personnage

import item.Armure
import org.junit.Test
import org.junit.jupiter.api.Assertions
import qualiteCommun

class ArmureTest {

    @Test
    fun calculProtection () {
        val armure1= Armure("","",typeBase, qualiteCommun)
        var result= armure1.calculProtection()
        Assertions.assertEquals(2, result)
    }
}