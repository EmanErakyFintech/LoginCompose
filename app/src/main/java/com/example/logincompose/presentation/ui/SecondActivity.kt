package com.example.logincompose.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.logincompose.R
import com.example.logincompose.domain.models.UserItem
import com.example.logincompose.ui.theme.LoginComposeTheme

class SecondActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MyList()
                }
            }
        }
    }
}

@Composable
fun MyList() {
    LazyColumn {
        items(myItems) {
            ListItem(dataU = it)
        }
    }
}

@Composable
fun ListItem(dataU: UserItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        Image(
            painter = painterResource(dataU.img),
            modifier = Modifier.width(40.dp),
            contentDescription = "My Image"
        )
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(start = 10.dp),
            text = dataU.name, style = TextStyle(
                color = Color.Blue,
                textAlign = TextAlign.Start,
                fontSize = 20.sp
            )
        )
    }
}


val myItems = listOf (
    UserItem("Android", R.drawable.splash_logo),
    UserItem("Android", R.drawable.splash_logo),
    UserItem("ios", R.drawable.splash_logo),
    UserItem("Phone", R.drawable.splash_logo),
    UserItem("Android", R.drawable.splash_logo),
    UserItem("Android", R.drawable.splash_logo),
    UserItem("Mobile", R.drawable.splash_logo),
    UserItem("Android", R.drawable.splash_logo),
    UserItem("Mobile", R.drawable.splash_logo),
    UserItem("Mobile", R.drawable.splash_logo),
    UserItem("Mobile", R.drawable.splash_logo),
    UserItem("Mobile", R.drawable.splash_logo),
    UserItem("Mobile", R.drawable.splash_logo),
    UserItem("ios", R.drawable.splash_logo),
    UserItem("Phone", R.drawable.splash_logo),
    UserItem("ios", R.drawable.splash_logo),
    UserItem("Phone", R.drawable.ic_launcher_background)
)


@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    LoginComposeTheme {
//        RowItem()
    }
}