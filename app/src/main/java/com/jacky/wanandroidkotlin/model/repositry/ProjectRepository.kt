package com.jacky.wanandroidkotlin.model.repositry

import com.jacky.wanandroidkotlin.model.api.WanRetrofitClient
import com.jacky.wanandroidkotlin.model.entity.ArticleList
import com.jacky.wanandroidkotlin.model.entity.TreeParentEntity
import com.jacky.wanandroidkotlin.model.entity.WanResponse

/**
 * @Author:YumDreaming
 * @date :2024/5/30
 * desc  ：项目 数据仓库
 * record：
 */
class ProjectRepository : CollectRepository() {
    /**
     * 获取项目分类tab
     */
    suspend fun getProjectType(): WanResponse<List<TreeParentEntity>> {
        return WanRetrofitClient.mService.getProjectType()
    }

    /**
     * 根据类型id分页获取项目列表
     */
    suspend fun getProjectListWithTypeId(page: Int, typeId: Int): WanResponse<ArticleList> {
        return WanRetrofitClient.mService.getProjectListWithTypeId(page, typeId)
    }

    /**
     * 分页获取最新的项目列表
     */
    suspend fun getLatestProjectList(page: Int): WanResponse<ArticleList> {
        return WanRetrofitClient.mService.getLastedProjectList(page)
    }

    /**
     * 获取公众号分类tab
     */
    suspend fun getBlogType(): WanResponse<List<TreeParentEntity>> {
        return WanRetrofitClient.mService.getBlogType()
    }

    /**
     * 根据公众号id分页获取公众号文章列表
     */
    suspend fun getBlogListWithId(page: Int, blogId: Int): WanResponse<ArticleList> {
        return WanRetrofitClient.mService.getBlogArticleList(page, blogId)
    }
}