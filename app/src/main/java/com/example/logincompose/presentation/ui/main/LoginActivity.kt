package com.example.logincompose.presentation.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.example.logincompose.data.common.Resource
import com.example.logincompose.domain.models.UserItem
import com.example.logincompose.presentation.ui.SecondActivity
import com.example.logincompose.ui.theme.LoginComposeTheme
import org.koin.androidx.compose.viewModel

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val lifecycle = LocalLifecycleOwner.current
            LoginComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Observe(lifecycle)
                    LoginForm()

                }
            }
        }
    }


}

@Composable
fun Observe(lifecycle: LifecycleOwner) {
    val context = LocalContext.current

    val viewModel: MainViewModel by viewModel()


    //// Collect a Flow lifecycle-aware in a composable////
//    val exampleFlowLifecycleAware = remember(viewModel.dataObserver, lifecycle) {
//        viewModel.dataObserver.flowWithLifecycle(lifecycle.lifecycle, Lifecycle.State.STARTED)
//    }
//
//    val state = exampleFlowLifecycleAware.collectAsState(initial = UserItem()).value
//
////    viewModel.dataObserver.collectAsState(initial =UserItem()).value
//    when (state) {
//        is Resource.Loading -> {
//
//        }
//        is Resource.Error -> {
//
//        }
//        is Resource.Success<*> -> {
//            context.startActivity(Intent(context, SecondActivity::class.java))
//            Toast.makeText(context, "Success login user", Toast.LENGTH_SHORT).show()
//        }
//    }

    lifecycle.lifecycleScope.launchWhenStarted {
        viewModel.dataObserver.collect {
            when (it) {
                is Resource.Loading -> {

                }
                is Resource.Error -> {

                }
                is Resource.Success -> {
                    context.startActivity(Intent(context, SecondActivity::class.java))
                    Toast.makeText(context, "Success login user", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LoginComposeTheme {
        LoginForm()
    }
}