package com.example.desafio_jetpackcompose;

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MeuApp()
        }
    }
}

@Composable
fun MeuApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {

        composable("login") {
            TelaLogin(navController)
        }

        composable("boasvindas/{nome}") { backStackEntry ->
            val nome = backStackEntry.arguments?.getString("nome")
            TelaBoasVindas(navController, nome)
        }

        composable("configuracoes") {
            TelaConfiguracoes(navController)
        }
    }
}

@Composable
fun TelaLogin(navController: NavController) {
    var nome by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Digite seu nome:")

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = nome,
            onValueChange = { novoTexto -> nome = novoTexto },
            label = { Text("Nome") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (nome.isNotBlank()) {
                    navController.navigate("boasvindas/$nome")
                }
            }
        ) {
            Text("Entrar")
        }
    }
}

@Composable
fun TelaBoasVindas(navController: NavController, nome: String?) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Bem-vindo, $nome!")

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { navController.navigate("configuracoes") }) {
            Text("Ir para Configurações")
        }
    }
}

@Composable
fun TelaConfiguracoes(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Configurações do App")

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { navController.popBackStack() }) {
            Text("Voltar")
        }
    }
}

