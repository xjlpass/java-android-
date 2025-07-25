package com.example.androidlearn.databinding

import androidx.databinding.ObservableField

 class Book  {
    val name: ObservableField<String> by lazy { ObservableField<String>() }
    val author: ObservableField<String> by lazy { ObservableField<String>() }
}
