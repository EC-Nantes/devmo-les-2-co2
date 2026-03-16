package com.example.devmo_les_2_co2.ui

import android.view.LayoutInflater
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.devmo_les_2_co2.R
import com.example.devmo_les_2_co2.ui.theme.Devmoles2co2Theme

@Composable
fun FlashAddScreen(appViewModel: AppViewModel = viewModel()) {
    val appUiState by appViewModel.uiState.collectAsState()
    val mediumPadding = 16.dp

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .safeDrawingPadding(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Le Header XML
        AndroidView(
            factory = { context ->
                LayoutInflater.from(context).inflate(R.layout.header_ajout_flash, null)
            },
            modifier = Modifier.fillMaxWidth()
        )

        Column(
            modifier = Modifier
                .padding(mediumPadding)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            
            // List des boutons d'accès rapide
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                ShortcutButton(
                    onClick = { appViewModel.changeEmission(1.5, 5.3, "Voyage") },
                    iconRes = R.drawable.wallet_travel,
                    contentDescription = "Voyage"
                )

                ShortcutButton(
                    onClick = { appViewModel.changeEmission(5.0, 9.0, "Pain") },
                    iconRes = R.drawable.baguette,
                    contentDescription = "Pain"
                )

                ShortcutButton(
                    onClick = { appViewModel.changeEmission(0.5, 9.0, "Course") },
                    iconRes = R.drawable.cart_variant,
                    contentDescription = "Course"
                )
            }

            // Nom de l'émission actuelle
            EmissionName(appUiState.name, modifier = Modifier.fillMaxWidth())

            // Formulaire de saisie
            FlashAddLayout(
                quantity = appViewModel.userQuantity,
                factor = appViewModel.userFactor,
                count = appViewModel.userCount,
                onQtyChange = { appViewModel.updateQuantity(it) },
                onFactorChange = { appViewModel.updateFactor(it) },
                onCountChange = { appViewModel.updateCount(it) },
                modifier = Modifier.fillMaxWidth()
            )

            // Score de l'émission en cours
            EmissionStatus(
                label = "Émission actuelle",
                score = appViewModel.userScore, 
                modifier = Modifier.fillMaxWidth()
            )

            // Bouton Ajouter
            OutlinedButton(
                onClick = { appViewModel.addEmission() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(R.string.add),
                    fontSize = 16.sp
                )
            }

            // log pour les tests
            if (appUiState.currentInfo.isNotEmpty()) {
                Text(
                    text = appUiState.currentInfo,
                    fontSize = 12.sp,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Start
                )
            }

            // Pour les tests
            EmissionStatus(
                label = "Score Total",
                score = appUiState.totalScore, 
                modifier = Modifier.fillMaxWidth(),
                containerColor = colorScheme.primaryContainer
            )
        }
    }
}

@Composable
fun ShortcutButton(onClick: () -> Unit, iconRes: Int, contentDescription: String) {
    Button(onClick = onClick, modifier = Modifier.padding(4.dp)) {
        Image(
            painter = painterResource(iconRes),
            contentDescription = contentDescription,
            modifier = Modifier.padding(4.dp)
        )
    }
}

@Composable
fun FlashAddLayout(
    quantity: String,
    factor: String,
    count: String,
    onQtyChange: (String) -> Unit,
    onFactorChange: (String) -> Unit,
    onCountChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(16.dp)
        ) {
            EditNumberField(R.string.quantity, quantity, "Quantité", onQtyChange, Modifier.fillMaxWidth(), KeyboardType.Decimal)
            EditNumberField(R.string.factor, factor, "Facteur", onFactorChange, Modifier.fillMaxWidth(), KeyboardType.Decimal)
            EditNumberField(R.string.count, count, "Nombre", onCountChange, Modifier.fillMaxWidth(), KeyboardType.Number)
        }
    }
}

@Composable
fun EditNumberField(
    name: Int,
    value: String,
    label: String,
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
    fieldType: KeyboardType = KeyboardType.Text
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Text(text = stringResource(name), modifier = Modifier.weight(1f))
        TextField(
            value = value,
            singleLine = true,
            modifier = Modifier.weight(2f),
            onValueChange = onValueChanged,
            label = { Text(label) },
            keyboardOptions = KeyboardOptions(keyboardType = fieldType)
        )
    }
}

@Composable
fun EmissionStatus(
    label: String, 
    score: Double, 
    modifier: Modifier = Modifier,
    containerColor: androidx.compose.ui.graphics.Color = colorScheme.secondaryContainer
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = containerColor)
    ) {
        Column(modifier = Modifier.padding(12.dp).fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = label, style = typography.labelSmall)
            Text(
                text = stringResource(R.string.emission_unit_kg, score),
                style = typography.headlineSmall,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun EmissionName(name: String, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        Text(
            text = name,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(12.dp).fillMaxWidth(),
            style = typography.titleMedium
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FlashAddPreview() {
    Devmoles2co2Theme {
        FlashAddScreen()
    }
}
