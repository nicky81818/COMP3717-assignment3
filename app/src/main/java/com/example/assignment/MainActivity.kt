package com.example.assignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.assignment.data.Database
import com.example.assignment.data.EntryRepository
import com.example.assignment.data.QuoteRepository
import com.example.assignment.data.client

class MainActivity : ComponentActivity() {
    private val db by lazy { Database.getDatabase(applicationContext) }
    private val entryRepo by lazy { EntryRepository(db.entryDao()) }
    private val quoteRepo by lazy { QuoteRepository(client) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val entriesState:EntriesState = viewModel {
                EntriesState(entryRepo)
            }
            val quoteState = QuoteState(quoteRepo)
            LaunchedEffect(quoteState) {
                quoteState.getQuote()
            }
            MainContent(quoteState)
        }
    }
}

@Composable
fun MainContent(quoteState: QuoteState){
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { NavBar(navController) }
    ) { padding ->
        NavHost(
            navController,
            "home",
            modifier = Modifier.padding(padding)
        ) {
            composable("home"){
                Home(quoteState)
            }
            composable("add"){
                Add(navController)
            }
        }
    }
}