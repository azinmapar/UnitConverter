package com.example.unitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter()
                }
            }
        }
    }
}



@Composable
fun UnitConverter() {
    var fromMenuState by remember { mutableStateOf(false) }
    var toMenuState by remember { mutableStateOf(false) }
    var fromUnit by remember { mutableStateOf("m") }
    var fromValue by remember { mutableStateOf("") }
    var toUnit by remember { mutableStateOf("m") }
    var toValue by remember { mutableStateOf("") }
    val fromConversionFactor = remember {
        mutableDoubleStateOf(1.0)
    }
    val toConversionFactor = remember {
        mutableDoubleStateOf(1.0)
    }

    val customTextStyle = TextStyle(
        fontFamily = FontFamily.Monospace,
        color = Color.Red,
        fontSize = 32.sp,
    )

    fun convert() {
        // * ?: -> if it is null
        val inputAsDouble = fromValue.toDoubleOrNull() ?: 0.0
        val result = (inputAsDouble * fromConversionFactor.doubleValue * 100.0 /
                toConversionFactor.doubleValue).roundToInt() / 100.0
        toValue = result.toString()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    )
    {
        Text(text = "Unit Converter",
            style = customTextStyle,)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = fromValue,
            onValueChange = {
                fromValue = it
                convert()
            },
            label = { Text(text = "Enter Value") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Box {
                Button(
                    onClick = {
                        fromMenuState = !fromMenuState
                    },
                ) {
                    Text(text = fromUnit)
                    Icon(
                        Icons.Default.ArrowDropDown, contentDescription = "DropDown Arrow",
                    )
                }
                DropdownMenu(
                    expanded = fromMenuState,
                    onDismissRequest = { fromMenuState = false }) {
                    DropdownMenuItem(
                        text = {
                            Text(text = "Centimeters")
                        },
                        onClick = {
                            fromUnit = "cm"
                            fromMenuState = false
                            fromConversionFactor.doubleValue = 0.01
                            convert()
                        },
                    )
                    DropdownMenuItem(
                        text = {
                            Text(text = "Meters")
                        },
                        onClick = {
                            fromUnit = "m"
                            fromMenuState = false
                            fromConversionFactor.doubleValue = 1.0
                            convert()
                        },
                    )
                    DropdownMenuItem(
                        text = {
                            Text(text = "Millimeters")
                        },
                        onClick = {
                            fromUnit = "mm"
                            fromMenuState = false
                            fromConversionFactor.doubleValue = 0.001
                            convert()
                        },
                    )
                    DropdownMenuItem(
                        text = {
                            Text(text = "Feet")
                        },
                        onClick = {
                            fromUnit = "f"
                            fromMenuState = false
                            fromConversionFactor.doubleValue = 0.3048
                            convert()
                        },
                    )
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Box {
                Button(
                    onClick = {
                        toMenuState = !toMenuState
                    },
                ) {
                    Text(text = toUnit)
                    Icon(
                        Icons.Default.ArrowDropDown, contentDescription = "DropDown Arrow",
                    )
                }
                DropdownMenu(expanded = toMenuState, onDismissRequest = { toMenuState = false }) {
                    DropdownMenuItem(
                        text = {
                            Text(text = "Centimeters")
                        },
                        onClick = {
                            toUnit = "cm"
                            toMenuState = false
                            toConversionFactor.doubleValue = 0.01
                            convert()
                        },
                    )
                    DropdownMenuItem(
                        text = {
                            Text(text = "Meters")
                        },
                        onClick = {
                            toUnit = "m"
                            toMenuState = false
                            toConversionFactor.doubleValue = 1.0
                            convert()
                        },
                    )
                    DropdownMenuItem(
                        text = {
                            Text(text = "Millimeters")
                        },
                        onClick = {
                            toUnit = "mm"
                            toMenuState = false
                            toConversionFactor.doubleValue = 0.001
                            convert()
                        },
                    )
                    DropdownMenuItem(
                        text = {
                            Text(text = "Feet")
                        },
                        onClick = {
                            toUnit = "f"
                            toMenuState = false
                            toConversionFactor.doubleValue = 0.3048
                            convert()
                        },
                    )
                }

            } // * Works as a placeholder and its children are stack on top of each other

        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Result: ${toValue + toUnit}", style = MaterialTheme.typography.headlineMedium)
    }
}


@Preview(showBackground = true)
@Composable
fun UnitConverterPreview() {
    UnitConverter()
}