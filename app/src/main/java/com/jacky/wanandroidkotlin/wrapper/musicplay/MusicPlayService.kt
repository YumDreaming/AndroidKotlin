package com.jacky.wanandroidkotlin.wrapper.musicplay

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

/**
 * @Author:YumDreaming
 * @date :2024/5/30
 * desc  ：音乐播放服务，管理播放器初始化和资源回收
 * record：
 */
class MusicPlayService :Service(){

    override fun onBind(intent: Intent?): IBinder? =null

    override fun onCreate() {
        super.onCreate()
        Log.d("MusicService","onCreate")
        MusicPlayManager.initPlayer(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MusicService","onDestroy")
        //释放资源
        MusicPlayManager.release()
    }
}