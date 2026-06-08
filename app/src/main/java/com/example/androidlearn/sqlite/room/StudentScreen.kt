package com.example.androidlearn.sqlite.room

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

@Composable
fun StudentScreen(
    viewModel: StudentViewModel
) {
    val students by viewModel.students.collectAsState()

    students.forEach { student ->
        println("${student.name} - ${student.age}")
    }
}
