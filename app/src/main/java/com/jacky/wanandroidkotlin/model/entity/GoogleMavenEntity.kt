package com.jacky.wanandroidkotlin.model.entity

/**
 * @Author:YumDreaming
 * @date :2024/5/30
 * desc  ： Google Maven 仓库搜索数据实体
 * record：
 */

data class GoogleMavenEntity(
    val artifactMap: HashMap<String, MutableList<ArtifactEntity>>? = null,
    val groupName: String? = null,
    var groupExpand: Boolean = true
)

//Group map转换成对应数据实体
data class ArtifactMapDTO(
    val mapKeyName: String? = null,
    val artifactList: MutableList<ArtifactEntity>? = null,
    var expand: Boolean = false
)

data class ArtifactEntity(
    val artifact: String? = null,
    val content: String? = null,
    val date: String? = null,
    val group: String? = null,
    val id: Int? = null,
    val version: String? = null
)