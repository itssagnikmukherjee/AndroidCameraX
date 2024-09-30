package app.sagnikmukherjee.androidcamerax

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import app.sagnikmukherjee.androidcamerax.ui.theme.AndroidCameraXTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidCameraXTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Permission()
                }
            }
        }
    }
}

@Composable
fun Permission() {
    val permission = listOf(
        android.Manifest.permission.CAMERA,
    )
    val isGranted = remember { mutableStateOf(false) }
    val launch = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
        onResult = {
            isGranted.value = it.all { it.value }
        }
    )
    if (isGranted.value){
//        TODO
    }else{
        Column {
            Button(onClick = {
                launch.launch(permission.toTypedArray())
            }) {
                Text("Request Permission")
            }
        }
    }
}