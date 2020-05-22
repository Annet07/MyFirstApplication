package com.example.myapplication

class Database {
    private val users = arrayListOf(User("Anna", "Budrevich", "BudrevichAnya@gmail.com", "Annet0707"),
                                    User("Dmitriy", "Remizov","RemizovD@yandex.ru", "Dima7076"),
                                    User("Ulyana", "Smirnova", "UlechkaSuper@mail.ru", "Zayka0308")
    )

    fun checkUser(mail: String?, password: String?): Boolean {
        for (i in 0 until users.size) {
            if (mail == users[i].email && password == users[i].password) {
                return true
            }
        }
        return false
    }

    fun getUser(mail: String?, password: String?): User? {
        for (i in 0 until users.size) {
            if (mail == users[i].email && password == users[i].password) {
                return users[i]
            }
        }
        return null
    }
}