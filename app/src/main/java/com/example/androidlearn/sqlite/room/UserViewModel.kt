package com.example.androidlearn.sqlite.room

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class StudentViewModel(
    private val studentDao: StudentDao
) : ViewModel() {

    val students: StateFlow<List<Student>> =
        studentDao.getAllStudents()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList()
            )

    fun addStudent(name: String, age: Int) {
        viewModelScope.launch {
            val student = Student(
                name = name,
                age = age
            )
            studentDao.insertStudent(student)
        }
    }

    fun updateStudent(student: Student) {
        viewModelScope.launch {
            studentDao.updateStudent(student)
        }
    }

    fun deleteStudent(student: Student) {
        viewModelScope.launch {
            studentDao.deleteStudent(student)
        }
    }

    fun deleteStudentById(id: Int) {
        viewModelScope.launch {
            studentDao.deleteStudentById(id)
        }
    }

    fun deleteAllStudents() {
        viewModelScope.launch {
            studentDao.deleteAllStudents()
        }
    }
}

