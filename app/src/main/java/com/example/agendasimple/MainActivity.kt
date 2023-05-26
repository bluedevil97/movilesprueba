@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.agendasimple

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.agendasimple.ui.theme.AgendaSimpleTheme

class MainActivity : ComponentActivity() {

  val contactos = Fichero.cargarFichero(baseContext)


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      AgendaSimpleTheme {
        // A surface container using the 'background' color from the theme
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
          AgendaComponente(baseContext,contactos)
        }
      }
    }
  }
}

@Composable
fun AgendaComponente(context: Context, contactos: ArrayList<Contacto>){
  var nombre by remember { mutableStateOf("")}
  var mail by remember { mutableStateOf("")}
  var telefono by remember { mutableStateOf("")}

  Column( horizontalAlignment = Alignment.Start
  ,modifier = Modifier.padding(10.dp)
  , verticalArrangement = Arrangement.SpaceEvenly) {
    OutlinedTextField(value = nombre
      , onValueChange ={nombre= it}
      , label = { Text(text = "Nombre de Contacto"
        , modifier = Modifier
          .fillMaxWidth()
          .padding(5.dp))})

    OutlinedTextField(value =mail
      , onValueChange = {mail=it}
    , label = { Text(text = "email"
    , modifier = Modifier
          .fillMaxWidth()
          .padding(5.dp))})

    OutlinedTextField(value =telefono
      , onValueChange = {telefono=it}
      , label = { Text(text = "teléfono"
        , modifier = Modifier
          .fillMaxWidth()
          .padding(5.dp))})

    Button(onClick = {
      val nuevoContacto = Contacto(nombre,mail,telefono)
      Fichero.grabarFichero(context,nuevoContacto)
      Toast.makeText(context,"Fichero Actualizado", Toast.LENGTH_SHORT).show()
    }) {
      Text(text = "Agregar Contacto"
        , modifier = Modifier.fillMaxWidth())
    }
    LazyColumn(){
      itemsIndexed(contactos){indice,contacto ->
        ContactoItem(context = context, indice = indice, contacto = contacto)        
      }
    }
  }
}

@Composable
fun ContactoItem(context: Context,indice:Int,contacto:Contacto){
  Text(text = contacto.nombre)
  Text(text = contacto.mail)
  Text(text = contacto.telefono)
  Divider(modifier = Modifier
    .fillMaxWidth()
    .width(4.dp)
  ,color = Color.Gray
  )
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
  AgendaSimpleTheme() {

  }
}