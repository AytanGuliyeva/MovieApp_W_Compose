package com.example.movieapp_w_compose.features.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@Composable
fun HomeScreen(){

    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {

    }
}

@Composable
fun ViewPager(){
  //  val viewPagerItems = remember {  }
}