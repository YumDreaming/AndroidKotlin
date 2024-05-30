package com.jacky.wanandroidkotlin.app

import android.content.Context
import androidx.startup.Initializer
import com.jacky.support.CommonInitializer
import com.jacky.wanandroidkotlin.ui.demos.WeatherResManager

/**
 * @Author:YumDreaming
 * @date :2024/5/30
 * desc  ：startup全局管理所有module初始化
 * record：注意：先执行这里的create,最后执行application的create
 */
class AppGlobalInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        //do initialize
        WeatherResManager.initIcons()
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return mutableListOf(CommonInitializer::class.java)
    }
}