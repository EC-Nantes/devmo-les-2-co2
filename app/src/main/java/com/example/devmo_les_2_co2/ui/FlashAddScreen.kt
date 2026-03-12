package com.example.devmo_les_2_co2.ui


import android.app.Activity
import android.icu.text.NumberFormat
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.devmo_les_2_co2.R
import com.example.devmo_les_2_co2.ui.theme.Devmoles2co2Theme
import androidx.compose.material3.TextField
import java.util.Locale
import androidx.compose.ui.text.input.KeyboardType

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

        // Header with the menu and name of the vue
        // Header(modifier) // Justin


        // Area where tu put the emission
        FlashAddLayout(
            quantity = appUiState.currentQuantity,
            factor = appUiState.currentEmissionFactor,
            count = appUiState.currentCount,
            onQtyChange = { appViewModel.updateQuantity(it) },
            onFactorChange = { appViewModel.updateFactor(it) },
            onCountChange = { appViewModel.updateCount(it) },
            onKeyboardDone = { appViewModel.updateEmission() },
            modifier = Modifier
        )
        
        // Value of the emission
        EmissionStatus(score = appUiState.score, modifier = Modifier.padding(20.dp))

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
            fontSize = 16.sp
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
    onKeyboardDone: () -> Unit,
    modifier: Modifier = Modifier
) {
    val mediumPadding = 5.dp

    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(mediumPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(mediumPadding)
        ) {

            EditNumberField(R.string.quantity, quantity, "", onQtyChange, KeyboardType.Decimal)


            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.quantity)
                )


            }
            
            Text(
                text = stringResource(R.string.factor)
            )
            Text(
                text = stringResource(R.string.count)
            )

            OutlinedTextField(
                value = "0",
                singleLine = true,
                shape = shapes.large,
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = colorScheme.surface,
                    unfocusedContainerColor = colorScheme.surface,
                    disabledContainerColor = colorScheme.surface,
                ),
                onValueChange = onQtyChange,
                label = {   },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = { onKeyboardDone() }
                )
            )


            
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
    val mediumPadding = 5.dp

    Column(
        verticalArrangement = Arrangement.spacedBy(mediumPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(mediumPadding)
    ) {
        Text(text = stringResource(name))

        TextField(
            value = value,
            singleLine = true,
            modifier = modifier,
            onValueChange = onValueChanged,
            label = { Text(label) },
            keyboardOptions = KeyboardOptions(keyboardType = fieldType)
        )
    }
}




@Composable
fun EmissionStatus(score: Double, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
    ) {
        Text(
            text = stringResource(R.string.emission_unit_kg, score),
            modifier = Modifier.padding(8.dp)
        )

    }
}



@Preview()
@Composable
fun FlashAddPreview() {
    Devmoles2co2Theme {
        FlashAddScreen()
    }
}