package com.jacky.wanandroidkotlin.jetpack.room

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.RoomWarnings

/**
 * @Author:YumDreaming
 * @date :2024/5/30
 * desc  ：定义数据库实体类
 * record：
 */
@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,

    @ColumnInfo(name = "name")
    val name: String,

//    @ColumnInfo(name="age")
    val age: Int,

    @ColumnInfo(name = "user_address")
    val address: String?
){

}


