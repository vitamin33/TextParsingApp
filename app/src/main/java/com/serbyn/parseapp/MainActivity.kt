package com.serbyn.parseapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextField
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.serbyn.parseapp.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewModel = MainViewModel()
                    CurrencyChooserFields(viewModel)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        Greeting("Android")
    }
}

@Composable
fun CurrencyChooserFields(viewModel: MainViewModel) {
    val enteredValue = remember {
        mutableStateOf("")
    }

    val state: String by viewModel.uiState.collectAsStateLifecycleAware()


    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = 7.dp,
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(4.dp)
                    )
            ) {
                Text(
                    text = "Please enter string for parsing:",
                    fontSize = 20.sp
                )
            }
            OutlinedTextField(
                value = enteredValue.value,
                modifier = Modifier
                    .padding(16.dp)
                    .defaultMinSize(
                        minHeight = 100.dp
                    ),
                onValueChange = { enteredValue.value = it},
            )

            Button(
                modifier = Modifier
                    .padding(16.dp),
                onClick = { viewModel.parseString(enteredValue.value) }) {
                Text(text = "Parse", fontSize = 16.sp)
            }

            Text(
                modifier = Modifier
                    .padding(16.dp),
                fontSize = 18.sp,
                text = "Result:"
            )
            Spacer(modifier = Modifier.size(16.dp))
            Text(
                modifier = Modifier
                    .padding(16.dp),
                fontSize = 18.sp,
                text = state
            )
        }
    }
}