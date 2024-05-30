package com.jacky.wanandroidkotlin.wrapper.musicplay

/**
 * @Author:YumDreaming
 * @date :2024/5/30
 * desc  ：
 * record：
 */
enum class PlayModeEnum(val playMode: Int, val modeDesc: String) {
    ORDER_PLAY(PlayMode.PLAY_IN_ORDER, "顺序播放"),
    RANDOM_PLAY(PlayMode.PLAY_IN_RANDOM, "随机播放"),
    SINGLE_CYCLE(PlayMode.SINGLE_CYCLE, "单曲循环");
}