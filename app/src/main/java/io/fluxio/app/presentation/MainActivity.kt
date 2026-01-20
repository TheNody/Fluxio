package io.fluxio.app.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint
import io.fluxio.app.presentation.app.FluxioApp
import io.fluxio.app.presentation.ui.theme.FluxioTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FluxioTheme {
                FluxioApp()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    FluxioTheme {
        FluxioApp()
    }
}
