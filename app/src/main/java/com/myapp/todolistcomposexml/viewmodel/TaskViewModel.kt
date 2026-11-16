package com.myapp.todolistcomposexml.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.myapp.todolistcomposexml.data.Task

class TaskViewModel : ViewModel(){
    private val _tasks = MutableLiveData<List<Task>>(emptyList())
    val tasks: LiveData<List<Task>> = _tasks

    //Agregar tarea
    fun addTask(title: String) {
        val newTask = Task(
            id = System.currentTimeMillis(),
            title = title,
            isCompleted = false
        )
        _tasks.value = _tasks.value.orEmpty() + newTask
    }
    //completar o descompletar tarea
    fun toggleTask(id:Long){
        _tasks.value = _tasks.value.orEmpty().map { task ->
            if (task.id == id) {
                task.copy(isCompleted = !task.isCompleted)
            } else {
                task
            }
        }
    }
    //eliminar tarea
    fun deleteTask(id: Long) {
        _tasks.value = _tasks.value.orEmpty().filter { task -> task.id != id }
    }
}