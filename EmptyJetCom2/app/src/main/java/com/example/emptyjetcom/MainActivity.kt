package com.example.emptyjetcom

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.emptyjetcom.ui.theme.EmptyJetComTheme
import androidx.compose.runtime.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       enableEdgeToEdge()
        setContent {
            EmptyJetComTheme {
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
fun LiCo(n1: String, n2: String, n3: String, n4: String, onClick: (String) -> Unit) {
    Row (
        modifier = Modifier
            .padding(top = 10.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ){
        ElevatedButton(onClick = { onClick(n1)},
            colors = ButtonDefaults.elevatedButtonColors(containerColor = Color(0xFFBB86FC))
            ) {
            Text(
                text = n1,
                color = Color.White,
                fontWeight = FontWeight.SemiBold
            )
        }
        ElevatedButton(onClick = { onClick(n2)},
            colors = ButtonDefaults.elevatedButtonColors(containerColor = Color(0xFFBB86FC))
        ) {
            Text(
                text = n2,
                color = Color.White,
                fontWeight = FontWeight.SemiBold
            )
        }
        ElevatedButton(onClick = { onClick(n3)},
            colors = ButtonDefaults.elevatedButtonColors(containerColor = Color(0xFFBB86FC))
        ) {
            Text(
                text = n3,
                color = Color.White,
                fontWeight = FontWeight.SemiBold
            )
        }
        ElevatedButton(onClick = { onClick(n4)},
            colors = ButtonDefaults.elevatedButtonColors(containerColor = Color(0xFFBB86FC))
        ) {
            Text(
                text = n4,
                color = Color.White,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Composable
fun CalcVi() {
    var input by remember { mutableStateOf("") }

    val onButtonClick: (String) -> Unit = { value ->
        when (value) {
            "C" -> input = ""
            "=" -> try {
                input = evalExpression(input).toString()
            } catch (e: Exception) {
                input = "Erro"
            }
            else -> input += value
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "CALCULADORA",
            color = Color(0xFFBB86FC),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Surface (
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            color = Color.LightGray,
            contentColor = Color.Black

        ){
            Text(
                text = input,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(16.dp),
                color = Color.Black
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        LiCo("7", "8","9", "+",onButtonClick)
        LiCo("4", "5","6", "-",onButtonClick)
        LiCo("1", "2","3", "*",onButtonClick)
        LiCo("C", "0","=", "/",onButtonClick)
    }
}

fun evalExpression(expression: String): Double {
    return object : Any() {
        var pos = -1
        var ch = 0

        fun nextChar() {
            ch = if (++pos < expression.length) expression[pos].code else -1
        }

        fun eat(charToEat: Int): Boolean {
            while (ch == ' '.code) nextChar()
            if (ch == charToEat) {
                nextChar()
                return true
            }
            return false
        }

        fun parse(): Double {
            nextChar()
            val x = parseExpression()
            if (pos < expression.length) throw RuntimeException("Unexpected: " + ch.toChar())
            return x
        }

        fun parseExpression(): Double {
            var x = parseTerm()
            while (true) {
                when {
                    eat('+'.code) -> x += parseTerm()
                    eat('-'.code) -> x -= parseTerm()
                    else -> return x
                }
            }
        }

        fun parseTerm(): Double {
            var x = parseFactor()
            while (true) {
                when {
                    eat('*'.code) -> x *= parseFactor()
                    eat('/'.code) -> x /= parseFactor()
                    else -> return x
                }
            }
        }

        fun parseFactor(): Double {
            if (eat('+'.code)) return parseFactor()
            if (eat('-'.code)) return -parseFactor()

            var x: Double
            val startPos = this.pos
            if (eat('('.code)) {
                x = parseExpression()
                eat(')'.code)
            } else if (ch in '0'.code..'9'.code || ch == '.'.code) {
                while (ch in '0'.code..'9'.code || ch == '.'.code) nextChar()
                x = expression.substring(startPos, this.pos).toDouble()
            } else if (ch in 'a'.code..'z'.code) {
                while (ch in 'a'.code..'z'.code) nextChar()
                val func = expression.substring(startPos, this.pos)
                x = parseFactor()
                x = when (func) {
                    "sqrt" -> Math.sqrt(x)
                    "sin" -> Math.sin(Math.toRadians(x))
                    "cos" -> Math.cos(Math.toRadians(x))
                    "tan" -> Math.tan(Math.toRadians(x))
                    else -> throw RuntimeException("Unknown function: $func")
                }
            } else {
                throw RuntimeException("Unexpected: " + ch.toChar())
            }

            if (eat('^'.code)) x = Math.pow(x, parseFactor())

            return x
        }
    }.parse()
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EmptyJetComTheme {
        CalcVi()
    }
}