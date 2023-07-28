package com.example.myapplication
import HomePage
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.ui.core.Text
import androidx.ui.core.setContent


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Text("Hello, World!")
        }

    }
}