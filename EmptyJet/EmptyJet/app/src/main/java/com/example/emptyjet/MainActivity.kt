package com.example.emptyjet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.emptyjet.ui.theme.EmptyJetTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EmptyJetTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CalcVi()
                }
            }
        }
    }
}

@Composable
fun LiCo(n1: String, n2: String, n3: String, n4: String) {
        Row (
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ){
            ElevatedButton(onClick = { }) {
                Text(n1)
            }
            ElevatedButton(onClick = { }) {
                Text(n2)
            }
            ElevatedButton(onClick = { }) {
                Text(n3)
            }
            ElevatedButton(onClick = { }) {
                Text(n4)
            }
        }
    }

@Composable
fun CalcVi() {
    Column {
        LiCo("7", "8","9", "+")
        LiCo("4", "5","6", "-")
        LiCo("1", "2","3", "*")
        LiCo("C", "0","=", "/")

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EmptyJetTheme {
        CalcVi()
    }
}