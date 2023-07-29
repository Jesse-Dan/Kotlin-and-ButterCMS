package com.example.myapplication
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.ui.core.setContent
import com.example.myapplication.composable.BuildHomePageView



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface {
                    BuildHomePageView()
                }
            }
        }
    }
}

