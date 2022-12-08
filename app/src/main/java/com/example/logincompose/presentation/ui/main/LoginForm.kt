package com.example.logincompose.presentation.ui.main

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import org.koin.androidx.compose.viewModel
import com.example.logincompose.R
import com.example.logincompose.presentation.ui.SecondActivity
import org.koin.androidx.compose.getViewModel

@Composable
fun LoginForm(
    loginViewModel: MainViewModel = getViewModel()
){
    val mContext = LocalContext.current
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


        TextString(txt = stringResource(id = R.string.identity))

        TxtField(textValue, stringResource(id = R.string.identity), KeyboardType.Number,loginViewModel,mContext)

        TextString(txt = stringResource(id = R.string.password))

        TxtField(textPass, stringResource(id = R.string.password), KeyboardType.Password,loginViewModel,mContext)


        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = {
                        loginViewModel.onEvent(mContext,UIEventLogin.Submit)
                    println("Call Login pass=${textPass.value} identity =${textValue.value}")
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Blue
                ), modifier = Modifier
                    .padding(top = 25.dp)
                    .height(45.dp)
                    .width(160.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.login),
                    color = colorResource(id = R.color.white)
                )
            }
        }

    }


}

@Composable
fun TextString(txt: String) {
    Text(
        text = txt, style = TextStyle(
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        ), modifier = Modifier.padding(0.dp, 20.dp, 0.dp, 10.dp)
    )

}

@Composable
fun TxtField(textState: MutableState<String>, txtValue: String, key: KeyboardType, loginViewModel: MainViewModel ,context: Context) {

    val textValue = remember { textState }
    TextField(
        textStyle = TextStyle(
            color = Color.Black,
            textAlign = TextAlign.End
        ),
        colors = TextFieldDefaults.textFieldColors(
            cursorColor = Color.Black,
            disabledLabelColor = Color(0xffd8e6ff),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        placeholder = {
            Text(
                txtValue, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.End,
            )
        },

        value = textValue.value,
        onValueChange = { it ->
            textValue.value = it
            if(txtValue.equals(context.resources.getString(R.string.identity)))
            loginViewModel.onEvent(context,UIEventLogin.IdentityChange(it))
            else
                loginViewModel.onEvent(context,UIEventLogin.PasswordChange(it))
        },

        keyboardOptions = KeyboardOptions(keyboardType = key)
    )
}
