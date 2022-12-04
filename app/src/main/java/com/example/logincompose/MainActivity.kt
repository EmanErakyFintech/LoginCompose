package com.example.logincompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.logincompose.ui.theme.LoginComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    LoginUser()
                }
            }
        }
    }
}

@Composable
fun LoginUser() {
    val textValue = remember { mutableStateOf("") }
    val textPass = remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp),
        horizontalAlignment = Alignment.End,
//        verticalArrangement = Arrangement.Center,

    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp),
            painter = painterResource(R.drawable.splash_logo),
            contentDescription = "content description"
        )


        TextString(txt = "رقم الهوية")

        TxtField(textValue , "رقم الهوية")

        TextString(txt = "كلمة المرور")

        TxtField(textPass , "كلمة المرور")


        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = { println("Call Login ${textPass.value} ${textValue.value}") }, colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Blue
                ), modifier = Modifier
                    .padding(top = 25.dp)
                    .height(45.dp)
                    .width(160.dp)
            ) {
                Text(
                    text = "تسجيل الدخول",
                    color = colorResource(id = R.color.white)
                )
            }
        }

    }
}

@Composable
fun TextString(txt:String){
    Text(
        text = txt, style = TextStyle(
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        ), modifier = Modifier.padding(0.dp, 20.dp, 0.dp, 10.dp)
    )

}

@Composable
fun TxtField(textState: MutableState<String>, txtValue: String) {

    val textValue = remember { textState }
    TextField(textStyle = TextStyle(
        color = Color.Black,
        textAlign = TextAlign.End
    ),
        colors = TextFieldDefaults.textFieldColors(
            cursorColor = Color.Black,
            disabledLabelColor =  Color(0xffd8e6ff),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
//        label={ Text(text = txtValue, textAlign = TextAlign.End,style =TextStyle(textAlign = TextAlign.End))},
        placeholder = {
            Text(txtValue, textAlign = TextAlign.Left,
                style =TextStyle(textAlign = TextAlign.Left)
            )
        },



        value = textValue.value,
        onValueChange = { it ->
            textValue.value = it
        })


}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LoginComposeTheme {
        LoginUser()
    }
}