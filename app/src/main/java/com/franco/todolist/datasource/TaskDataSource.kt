package com.franco.todolist.datasource

import com.franco.todolist.model.Task

object TaskDataSource {

    private val list = arrayListOf<Task>()

    fun getList() = list.toList()

    fun insertTask(task: Task) {
        if (task.id == 0) {
            list.add(task.copy(id = list.size + 1))
        } else {
            list.remove(findById(task.id))
            list.add(task)

        }

    }


    fun findById(taskId: Int) = list.find { it.id == taskId }

    fun deletTask(task: Task){
        list.remove(task)
    }

}