package com.jacky.wanandroidkotlin.model.entity

import java.io.Serializable

/**
 * @Author:YumDreaming
 * @date :2024/5/30
 * desc  ：树 子节点数据实体
 * record：
 */
data class TreeChildEntity(
    val child: List<TreeChildEntity>,
    val courseId: Int,
    val id: Int,
    val name: String,
    val order: Int,
    val parentChapterId: Int,
    val visible: Int
) : Serializable