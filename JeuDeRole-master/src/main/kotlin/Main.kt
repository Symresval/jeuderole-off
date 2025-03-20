import item.*
import jeu.Jeu
import jeu.TirageDes
import personnage.Personnage

//instanciation des qualités des objets
val qualiteCommun = Qualite("commun", 0, "\u001B[32m")
val qualiteRare = Qualite("rare", 1, couleur = "\u001B[34m")
val qualiteEpic = Qualite("epic", 3, "\u001B[35m")
val qualiteLegendaire = Qualite("legendaire", 4, "\u001B[33m")


//Intantation des types d'armes
val EpeeLongue = TypeArme(1, 8, 2.0, 19)
val Lance = TypeArme(1, 8, 3.0, 20)
val Corps = TypeArme(1, 4, 2.0, 18)
val dague = TypeArme(1, 4, 3.0, 18)


//Intantation des types d'armures
val leger = TypeArmure(nom = "Léger", BonusType = 2)
val cuir = TypeArmure(nom = "Cuir", BonusType = 2)
val coteDemaille = TypeArmure(nom = "Cote de Maille", BonusType = 5)
val typeExtraLegendaire = TypeArmure(nom = "Berbèrie", BonusType = 6)


//instanciation des sorts d'attaque

val projectionacide = Sort("Sort de projection acide") { caster, cible ->
    run {
        val tirageDes = TirageDes(1, 10)
        var degat = tirageDes.lance()
        degat = maxOf(1, degat - cible.calculeDefense())
        cible.pointDeVie -= degat
        println("Le jet d'acide inflige $degat à ${cible.nom}")
    }
}

val bouleFeu = Sort("boule de feu") { caster, cible ->
    run {
        val tirageDes = TirageDes(3, 6)
        var degat = tirageDes.lance() + caster.attaque / 3

        degat = maxOf(1, degat - cible.calculeDefense())
        cible.pointDeVie -= degat
        println("Le jet d'acide inflige $degat à ${cible.nom}")
    }
}

val missileMagique = Sort("Missile magique") { caster, cible ->
    run {
        val tirageDes = TirageDes(1, 6)
        var compteur = 0
        while (compteur < caster.attaque / 2) {
            var degat = tirageDes.lance()

            degat = maxOf(1, degat - cible.calculeDefense())
            cible.pointDeVie -= degat
            println("Le jet d'acide inflige $degat à ${cible.nom}")
            ++compteur
        }

    }
}
val armeMagique = Sort("armeMagique ") { caster, cible ->
    run {
        val tirageDes = TirageDes(1, 20)
        var compteur = 0
        var qualite: Qualite
        var arme: Arme?
        var resultat = tirageDes.lance()
        if (resultat in 1..4) {
            qualite = qualiteCommun
            arme = (Arme("Caillou", "Vous obtenez un caillou dans votre inventaire", EpeeLongue, qualite, 3))
        } else if (resultat in 5..9) {
            qualite = qualiteRare
            arme = (Arme("Nokia 3310", "Vous obtenez un nokia 3310 dans votre inventaire", Lance, qualite, 5))
        } else if (resultat in 10..14) {
            qualite = qualiteEpic
            arme = (Arme("Epee de Guts", "Vous obtenez l'épée de GUTS dans votre inventaire", EpeeLongue, qualite, 12))
        } else {
            qualite = qualiteLegendaire
            arme = (Arme("Ryokusui", "Vous obtenez Ryokusui dans votre inventaire", Corps, qualite, 20))
        }
        cible.inventaire.add(arme)
        if (arme in cible.inventaire)
            println("${arme.nom} à était ajouté à l'inventaire")


    }
}

val armureMagique = Sort("armeMagique ") { caster, cible ->
    run {
        val tirageDes = TirageDes(1, 20)
        var qualite: Qualite
        var armure: Armure
        var resultat = tirageDes.lance()
        if (resultat in 1..4) {
            qualite = qualiteCommun
            armure = (Armure("Armure en cuir","Vous obtenez une armure en cuir dans votre inventaire",leger,qualite))
        } else if (resultat in 5..9) {
            qualite = qualiteRare
            armure = (Armure("Armure en or", "Vous obtenez une armure en or dans votre inventaire",cuir, qualite))
        } else if (resultat in 10..14) {0
            qualite = qualiteEpic
            armure = (Armure("Armure en fer", "Vous obtenez une armure en fer dans votre inventaire", coteDemaille, qualite))
        } else {
            qualite = qualiteLegendaire
            armure = (Armure("Armure en diamant", "Vous obtenez une armure en diamant dans votre inventaire", typeExtraLegendaire, qualite))
        }
        cible.inventaire.add(armure)
        if (armure in cible.inventaire)
            println("${armure.nom} à était ajouté à l'inventaire")


    }
}
//instanciation des sorts soin
val bienSoigner = Sort("bien soigné") { caster, cible ->
    run {
        val tirageDes = TirageDes(1, 6)
        var soin = tirageDes.lance() + caster.attaque / 2
        cible.pointDeVie += soin //calcule point de vie + potion
        if (cible.pointDeVie > cible.pointDeVieMax) {
            cible.pointDeVie = cible.pointDeVieMax

        }
        println("WOAW !!!!!! ${cible.nom} est bienSoigner de $soin point de vie ")
    }
}

fun main() {

//Instantation des armes
    val main = Arme(
        nom = "Tes propres main",
        description = "Tu connais l'expression on fait avec ce que l'on a sous la main... bah t'as rien lol ratio",
        type = Corps,
        qualité = qualiteCommun,
        degats = 2
    )

    val dague = Arme(
        nom = "Double Dague",
        description = "Comme Bonnie&Clyde, ils sont unis dans la mort",
        type = dague,
        qualité = qualiteEpic,
        degats = 7 * 2
    )

    val excalibruh = Arme(
        nom = "excalibruh",
        description = "A cause de droits d'auteur, on peut pas le dire",
        type = EpeeLongue,
        qualité = qualiteEpic,
        degats = 20
    )

    val Guandao = Arme(
        nom = "Guandao",
        description = "",
        type = Lance,
        qualité = qualiteLegendaire,
        degats = 35
    )

    val claquette = Arme(
        nom = "Babouche traditionnelle",
        description = "Fonction 1 : Arme de destruction massive , Fonction 2 : Claquette ",
        type = Corps,
        qualité = qualiteRare,
        degats = 5 * 2
    )


    // Instantation des armures
    val voile = Armure(
        nom = " Voile Sacréééééééééééé",
        description = "oui c'est leger un voile pour une armure mais lui il est Sacré avec 12 é",
        type = leger,
        qualite = qualiteCommun
    )

    val lourd = Armure( //armure de madame
        nom = " l'armure du berbère EXXXTTTRême",
        description = "Le berbère originel ⵣ (il se transmet de berbère en berbère)",
        type = typeExtraLegendaire,
        qualite = qualiteLegendaire,
    )

    val Tong = Armure(
        nom = "Tong",
        description = "Fonction 1 : Claquette, Fonction 2 : Arme de destruction massive",
        type = leger,
        qualite = qualiteRare
    )

    val lacosteTn = Armure(
        nom = "lacoste Tn",
        description = "Excuse nous la caillé",
        type = cuir,
        qualite = qualiteLegendaire,
    )

    //Instantation des potions
    val yop = Potion(
        nom = "Yop",
        soin = 20,
        description = "PREND MOI UN YOP!!! (ces dernieres paroles...)",
    )

    val eaudejaval = Potion(
        nom = "Eau de Javel",
        soin = -3,
        description = "Avant je les lavais avec de l'eau..."
    )
    val jd = Potion(
        nom = "Jack-Daniel",
        soin = 30,
        description = "Produit par Studio Daniel",
    )
    val dolypran = Potion(
        nom = "Douliipr-âne",
        soin = 20,
        description = "C'est le goût fraise, pas le 1000",
    )
    //Instantation des Bombes

    val bomboclaat = Bombe(
        nom = "BOMBOCLAAAAAT",
        nombreDeDes = 4,
        maxDe = 6,
        description = "Avant cette explosion, laisser moi vous parler de Nord VP~~~",
    )

    val creeper = Bombe(
        nom = "Creeper",
        nombreDeDes = 2,
        maxDe = 8,
        description = "Ooh man~"
    )

    val petard = Bombe(
        nom = "Petard",
        nombreDeDes = 2,
        maxDe = 5,
        description = "On va rendre justice à personne avec ces dégats -_-",
    )

    val petardDeGuerre = Bombe(
        nom = "Le Petard de Guerre",
        nombreDeDes = 5,
        maxDe = 6,
        description = "JUSTICE POUR ADAMA VRAIMENT",
    )

    //Instantiation des monstres
    val Slime = Personnage( //Inspiré de Armure animée
        "Slipmane le slime",
        pointDeVie = 33,
        pointDeVieMax = 33,
        attaque = 8,
        defense = 15,
        endurance = 8,
        vitesse = 6,
        armePrincipal = null,
        armure = null,
        inventaire = mutableListOf()
    )

    val djin = Personnage( //monstre de madame,Inspiré de Élémentaire du feu
        "DjJnoun",
        pointDeVie = 102,
        pointDeVieMax = 102,
        attaque = 12,
        defense = 10,
        endurance = 8,
        vitesse = 16,
        armePrincipal = main,
        armure = null,
        inventaire = mutableListOf()
    )


    val khouna = Personnage(
        //monstre de madame, Inspiré de Gelée ocre
        nom = "Roi~Khouna",
        pointDeVie = 45,
        pointDeVieMax = 45,
        attaque = 9,
        defense = 14,
        endurance = 7,
        vitesse = 10,
        armePrincipal = null,
        armure = null,
        inventaire = mutableListOf(),
    )

    val Maman = Personnage( //monstre de madame, Inspiré de Kraken
        nom = "khalti hasna",
        pointDeVie = 472,
        pointDeVieMax = 472,
        attaque = 18,
        defense = 15,
        endurance = 22,
        vitesse = 14,
        armePrincipal = claquette,
        armure = Tong,
        inventaire = mutableListOf(bomboclaat ,)
    )

    val eggman = Personnage( //Inspiré de Homme-lezard
        "Eggman",
        pointDeVie = 22,
        pointDeVieMax = 22,
        attaque = 11,
        defense = 13,
        endurance = 11,
        vitesse = 11,
        armePrincipal = excalibruh,
        armure = null,
        inventaire = mutableListOf(Guandao, voile)
    )



    // TODO Intermission 1 Ajouter d'autres monstres
    //On ajoute les monstres a la liste de monstres du jeu
    val jeu = Jeu(listOf(Maman, Slime,eggman, djin, khouna))

    //Lancement du jeu
    jeu.lancerCombat()
}