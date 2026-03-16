package com.example.devmo_les_2_co2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.example.devmo_les_2_co2.ui.theme.Devmoles2co2Theme
import com.example.devmo_les_2_co2.ui.FlashAddScreen
import androidx.compose.material3.Surface

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Devmoles2co2Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    FlashAddScreen()
                }
            }
        }
    }
}

// @Preview(showBackground = true)
// @Composable
// fun GreetingPreview() {
//     Devmoles2co2Theme {
//         Greeting("Android")
//     }
// }