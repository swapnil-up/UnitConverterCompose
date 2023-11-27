package com.example.pirategame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.example.pirategame.ui.theme.UnitConverterComposeTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PirateGame()
                }
            }
        }
    }
}

@Composable
fun PirateGame(){
    val treasuresFound=remember{ mutableStateOf(0) }
    val hp=remember{ mutableStateOf(5) }
    val direction= remember { mutableStateOf("North") }
    Column {
        if (hp.value<=0) gameover()
        Text(text = "Treasures found: ${treasuresFound.value}")
        Text(text = "HP left after crashing into storms: ${hp.value}")
        Text(text = "You're heading towards" +direction.value)
        Button(onClick = {
            direction.value="North"
            if (Random.nextBoolean()) treasuresFound.value++
            else hp.value--
        }) {
            Text(text = "Sail North")
        }
        Button(onClick = {
            direction.value="South"
            if (Random.nextBoolean()) treasuresFound.value++
            else hp.value--
        }) {
            Text(text = "Sail South")
        }
        Button(onClick = {
            direction.value="East"
            if (Random.nextBoolean()) treasuresFound.value++
            else hp.value--
        }) {
            Text(text = "Sail East")
        }
        Button(onClick = {
            direction.value="West"
            if (Random.nextBoolean()) treasuresFound.value++
            else hp.value--
        }) {
            Text(text = "Sail West")
        }
    }
}

@Composable
fun gameover(){
    Text(text = "GAME OVER",
        modifier=Modifier.fillMaxSize(),
        fontSize = 32.sp
    )
}