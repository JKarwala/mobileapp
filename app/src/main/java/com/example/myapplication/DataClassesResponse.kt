package com.example.myapplication

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

data class Osoba(
    val imie: String,
    val wiek: Int,
    val miasto: String
)

data class Telefon(
    val nazwa: String,
    val cena: Double,
    val specyfikacja: Specyfikacja
)

data class Specyfikacja(
    val procesor: String,
    val ram: String,
    val bateria: String
)

data class Owoce(
    val owoce: List<Owoc>
)

data class Owoc(
    val nazwa: String,
    val kolor: String
)

data class Drzewa(
    val drzewa: List<Drzewo>
)

data class Drzewo(
    val nazwa: String,
    val wysokosc: Int,
    val informacje: List<InformacjeDrzewa>
)

data class InformacjeDrzewa(
    val kraj: String,
    val powierzchnia: Int,
)

data class Samochody(
    val samochody: List<Samochod>
)

data class Samochod(
    val marka: String,
    val model: String,
    val rok: Int,
    val informacje: Informacje,
    val wyposazenie: List<String>
)

data class Informacje(
    val silnik: Silnik,
    val nadwozie: Nadwozie
)

data class Silnik(
    val typ: String,
    val pojemnosc: String,
    val moc: String
)

data class Nadwozie(
    val typ: String,
    val kolor: String
)
