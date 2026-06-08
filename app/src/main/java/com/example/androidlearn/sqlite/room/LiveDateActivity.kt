package com.example.androidlearn.sqlite.room

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity

class LiveDateActivity : AppCompatActivity() {

    private val studentViewModel: StudentViewModel by viewModels {
        StudentViewModelFactory(
            AppDatabase.getInstance(this).studentDao()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        studentViewModel.addStudent("张三", 20)

        studentViewModel.deleteStudentById(1)

        studentViewModel.deleteAllStudents()
    }
}