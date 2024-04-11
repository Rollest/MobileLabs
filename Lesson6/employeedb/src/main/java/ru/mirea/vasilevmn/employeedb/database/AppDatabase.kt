package ru.mirea.vasilevmn.employeedb.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.mirea.vasilevmn.employeedb.dao.EmployeeDao
import ru.mirea.vasilevmn.employeedb.entity.Employee

@Database(entities = [Employee::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun employeeDao(): EmployeeDao?
}