package com.example.agendasimple

import android.content.Context
import java.io.File

object Fichero {
  fun grabarFichero(context: Context,contacto: Contacto){
    val fichero = File(context.filesDir,"contactos.txt")
    fichero.appendText(text = "${contacto.nombre}\n${contacto.mail}\n${contacto.telefono}\n")
  }
  fun cargarFichero(context: Context): ArrayList<Contacto> {
    var contactos:ArrayList<Contacto> = ArrayList()
    val fichero = File(context.filesDir,"contactos.txt")
    if(fichero.exists()){
      val lista = fichero.readLines()
      var indice = 0
      while(indice < lista.size){
        contactos.add(Contacto(lista[indice],lista[indice + 1],lista[indice+2]))
        indice += 3
      }
    }
    return contactos
  }
}