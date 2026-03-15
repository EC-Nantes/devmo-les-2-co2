package com.example.devmo_les_2_co2.ui


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.devmo_les_2_co2.R
import com.example.devmo_les_2_co2.ui.theme.Devmoles2co2Theme
import androidx.compose.material3.TextField
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.foundation.layout.Row
<<<<<<< HEAD
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.Image
=======
import androidx.compose.ui.viewinterop.AndroidView
import android.view.LayoutInflater
>>>>>>> 0ceec9c8f56bd74eb95c07200d35de41b701c242

@Composable
fun FlashAddScreen(appViewModel: AppViewModel = viewModel()) {
    val appUiState by appViewModel.uiState.collectAsState()
    val mediumPadding = 10.dp

    Column(
        modifier = Modifier
            .statusBarsPadding()
            .verticalScroll(rememberScrollState())
            .safeDrawingPadding()
            .padding(mediumPadding),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        // Quick icons
        // Dans l'idéal, il y aurait une liste et on mettrait les 3/4 premiers icones
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(mediumPadding).fillMaxWidth()
        ) {
            Button(
                onClick = { appViewModel.changeEmission(1.5, 5.3, "Voyage") }
            ) {
                Image(
                    painter = painterResource(R.drawable.wallet_travel),
                    contentDescription = "Voyage"
                )
            }

            Button(
                onClick = { appViewModel.changeEmission(5.0, 9.0, "Pain") }
            ) {
                Image(
                    painter = painterResource(R.drawable.baguette),
                    contentDescription = "Pain"
                )
            }

            Button(
                onClick = { appViewModel.changeEmission(0.5, 9.0, "Course") }
            ) {
                Image(
                    painter = painterResource(R.drawable.cart_variant),
                    contentDescription = "Course"
                )
            }
        }

        // Show the name of the emission
        EmissionName(appUiState.name, modifier = Modifier.padding(10.dp).fillMaxWidth())

        // Area where tu put the emission
        FlashAddLayout(
            quantity = appViewModel.userQuantity,
            factor = appViewModel.userFactor,
            count = appViewModel.userCount,
            onQtyChange = { appViewModel.updateQuantity(it) },
            onFactorChange = { appViewModel.updateFactor(it) },
            onCountChange = { appViewModel.updateCount(it) },
            modifier = Modifier
        )
        
        // Value of the emission
        EmissionStatus(score = appViewModel.userScore, modifier = Modifier.padding(20.dp))

        // Affichage du Header XML
        AndroidView(
            factory = { context ->
                LayoutInflater.from(context).inflate(R.layout.header_ajout_flash, null)
            },
            modifier = Modifier.fillMaxWidth()
        )

        Column(
            modifier = Modifier.padding(mediumPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Area where to put the emission
            FlashAddLayout(
                quantity = appViewModel.userQuantity,
                factor = appViewModel.userFactor,
                count = appViewModel.userCount,
                onQtyChange = { appViewModel.updateQuantity(it) },
                onFactorChange = { appViewModel.updateFactor(it) },
                onCountChange = { appViewModel.updateCount(it) },
                modifier = Modifier.fillMaxWidth()
            )

            // Value of the emission
            EmissionStatus(score = appViewModel.userScore, modifier = Modifier.padding(vertical = 16.dp))

            // Button to add the emission
            OutlinedButton(
                onClick = { appViewModel.addEmission() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(R.string.add),
                    fontSize = 16.sp
                )
            }

            // For the tests: show what has been added
            Text(
                text = appUiState.currentInfo,
                fontSize = 14.sp,
                modifier = Modifier.padding(top = 16.dp),
                textAlign = TextAlign.Start
            )
        }
<<<<<<< HEAD

        // For the tests: show what has been added
        Text(
            text = appUiState.currentInfo,
            fontSize = 16.sp
        )

        // For tests: total emission
        EmissionStatus(score = appUiState.totalScore, modifier = Modifier.padding(20.dp))
=======
>>>>>>> 0ceec9c8f56bd74eb95c07200d35de41b701c242
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
    val spacing = 12.dp

    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(spacing),
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
        Text(
            text = stringResource(name),
            modifier = Modifier.weight(1f),
            style = typography.bodyLarge
        )

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
fun EmissionStatus(score: Double, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = colorScheme.secondaryContainer)
    ) {
        Text(
            text = stringResource(R.string.emission_unit_kg, score),
            modifier = Modifier.padding(16.dp),
            style = typography.headlineSmall,
            color = colorScheme.onSecondaryContainer
        )

    }
}

@Composable
fun EmissionName(name: String, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
    ) {
        Text(
            text = name,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(8.dp)
        )

    }
}



<<<<<<< HEAD
@Preview
=======
@Preview(showBackground = true)
>>>>>>> 0ceec9c8f56bd74eb95c07200d35de41b701c242
@Composable
fun FlashAddPreview() {
    Devmoles2co2Theme {
        FlashAddScreen()
    }
}