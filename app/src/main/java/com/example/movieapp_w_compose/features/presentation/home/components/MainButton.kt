package com.example.movieapp_w_compose.features.presentation.home.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.movieapp_w_compose.features.presentation.theme.customTheme.LocalAppColors
import com.example.movieapp_w_compose.features.presentation.theme.customTheme.LocalAppTypograph

@Composable
fun MainButton(
    modifier: Modifier = Modifier,
    text:String,
    cornerRadius: Dp? = 12.dp,
    textColor: Color = LocalAppColors.current.white,
    bgColor: Color = LocalAppColors.current.red,
    onClick:() -> Unit
){
    Button(onClick = {onClick()},
        colors = ButtonDefaults.buttonColors(
            containerColor = bgColor

        ),
        modifier = modifier,
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(text = text,
            Modifier.padding(16.dp),
            color =textColor,
            style = LocalAppTypograph.current.calloutMedium)
    }

}