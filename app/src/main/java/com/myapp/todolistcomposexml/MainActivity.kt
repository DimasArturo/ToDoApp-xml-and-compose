
package com.myapp.todolistcomposexml

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.ComposeView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.myapp.todolistcomposexml.ui.compose.ComposeTodoScreen
import com.myapp.todolistcomposexml.viewmodel.TaskViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: TaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Obtener el ViewModel
        viewModel = ViewModelProvider(this)[TaskViewModel::class.java]

        // Mostrar Compose
        val composeView = ComposeView(this)

        composeView.setContent {
            ComposeTodoScreen(viewModel)
        }

        findViewById<android.widget.FrameLayout>(R.id.container)
            .addView(composeView)

        // Configurar márgenes del sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.container)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())

            v.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                systemBars.bottom
            )

            insets
        }
    }
}
