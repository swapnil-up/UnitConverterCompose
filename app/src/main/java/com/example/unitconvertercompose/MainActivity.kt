package com.example.unitconvertercompose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unitconvertercompose.ui.theme.UnitConverterComposeTheme
import kotlin.math.roundToInt

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
                    UnitConverter()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UnitConverter(){
    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }
    var inputUnit by remember { mutableStateOf("Meter") }
    var outputUnit by remember { mutableStateOf("Meter") }
    var inputConversion by remember { mutableStateOf(false) }
    var outputConversion by remember { mutableStateOf(false) }
    var conversionFactor = remember{ mutableStateOf(1.0) }
    var oconversionFactor = remember{ mutableStateOf(1.0) }

    fun convertInput(){
        var inputValueDouble=inputValue.toDoubleOrNull()?:0.0
        var outputValueDouble=(inputValueDouble*100.0*conversionFactor.value/oconversionFactor.value).roundToInt()/100.0
        outputValue=outputValueDouble.toString()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Unit Converter",modifier= Modifier.padding(16.dp),
            style = MaterialTheme.typography.headlineLarge)
        OutlinedTextField(value = inputValue, onValueChange = {
            inputValue=it
            convertInput()
            } )
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Box{
                Button(onClick = { inputConversion=true }) {
                    Text(text = inputUnit)
                    Icon(Icons.Default.ArrowDropDown,
                        contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = inputConversion, onDismissRequest = { inputConversion=false }) {
                    DropdownMenuItem(text ={
                        Text(text = "Millimeter")},
                        onClick = {
                            inputConversion=false
                            inputUnit="Millimeter"
                            conversionFactor.value=0.001
                            convertInput()
                        })
                    DropdownMenuItem(text ={
                        Text(text = "Meter")},
                        onClick = { inputConversion=false
                            inputUnit="Meter"
                            conversionFactor.value=1.0
                            convertInput()
                        })
                    DropdownMenuItem(text ={
                        Text(text = "Feet")},
                        onClick = { inputConversion=false
                            inputUnit="Feet"
                            conversionFactor.value=0.3048
                            convertInput()
                        })
                    DropdownMenuItem(text ={
                        Text(text = "centimeter")},
                        onClick = { inputConversion=false
                            inputUnit="centimeter"
                            conversionFactor.value=0.01
                            convertInput()
                        })
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Box{
                Button(onClick = { outputConversion=true }) {
                    Text(text = outputUnit)
                    Icon(Icons.Default.ArrowDropDown,
                        contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = outputConversion, onDismissRequest = { outputConversion=false }) {
                    DropdownMenuItem(text ={
                        Text(text = "Millimeter")},
                        onClick = { outputConversion=false
                            outputUnit="Millimeter"
                            oconversionFactor.value=0.001
                            convertInput()
                        })
                    DropdownMenuItem(text ={
                        Text(text = "Meter")},
                        onClick = { outputConversion=false
                            outputUnit="Meter"
                            oconversionFactor.value=1.0
                            convertInput()
                        })
                    DropdownMenuItem(text ={
                        Text(text = "Feet")},
                        onClick = { outputConversion=false
                            outputUnit="Feet"
                            oconversionFactor.value=0.3048
                            convertInput()
                        })
                    DropdownMenuItem(text ={
                        Text(text = "Centimeter")},
                        onClick = { outputConversion=false
                            outputUnit="Centimeter"
                            oconversionFactor.value=0.01
                            convertInput()
                        })
                }
            }
        }
        Text("Results is $outputValue $outputUnit",
            style = MaterialTheme.typography.headlineMedium)
    }
}

@Preview(showBackground = true)
@Composable
fun UnitConverterPreview(){
    UnitConverter()
}