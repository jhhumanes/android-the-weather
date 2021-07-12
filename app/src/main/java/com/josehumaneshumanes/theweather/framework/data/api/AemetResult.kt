package com.josehumaneshumanes.theweather.framework.data.api

import com.google.gson.annotations.SerializedName

data class AemetCity(
    val altitud: String,
    val capital: String,
    val destacada: String,
    val id: String,
    @SerializedName("id_old") val idOld: String,
    val latitud: String,
    @SerializedName("latitud_dec") val latitudDec: String,
    val longitud: String,
    @SerializedName("longitud_dec") val longitudDec: String,
    val nombre: String,
    @SerializedName("num_hab") val numHab: String,
    val url: String,
    @SerializedName("zona_comarcal") val zonaComarcal: String
)
