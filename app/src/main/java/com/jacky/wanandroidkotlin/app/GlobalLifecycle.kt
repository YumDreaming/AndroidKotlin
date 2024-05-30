package com.jacky.wanandroidkotlin.app

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Process
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.jacky.wanandroidkotlin.ui.splash.navigateToRestart
import java.lang.ref.WeakReference
import java.util.*

/**
 * @Author:YumDreaming
 * @date :2024/5/30
 * desc  ： 全局生命周期观察者
 * record：
 */

class GlobalLifecycleObserver : DefaultLifecycleObserver {
    //activity 引用任务栈
    private val actStack = Stack<WeakReference<Activity>>()

    //记录所有的callback
    private val callbackStack = Stack<WeakReference<ActivityLifecycleCallback>>()

    //记录前台activity数目
    private var foregroundActCount = 0

    override fun onCreate(owner: LifecycleOwner) {
        (owner as Activity).let {
            actStack.add(WeakReference(it))
        }
    }

    override fun onStart(owner: LifecycleOwner) {
        var activityLifecycleCallback: ActivityLifecycleCallback?
        if (foregroundActCount == 0) {
            for (i in callbackStack.indices.reversed()) {
                activityLifecycleCallback = callbackStack[i].get()
                activityLifecycleCallback?.onForeground()
            }
        }
        foregroundActCount++
    }

    override fun onStop(owner: LifecycleOwner) {
        foregroundActCount--
        if (foregroundActCount == 0) {
            for (i in callbackStack.indices.reversed()) {
                callbackStack[i].get()?.onBackground()
            }
        }
    }

    override fun onDestroy(owner: LifecycleOwner) {
        for (index in actStack.indices.reversed()) {
            if (actStack[index].get() == owner) {
                actStack.removeAt(index)
                break
            }
        }
    }

    fun clearAll() {
        actStack.clear()
        callbackStack.clear()
    }

    fun getTopActivity(): Activity? = if (actStack.empty()) null else actStack.peek().get()

    fun finishAllActivity() {
        for (weakReference in actStack.reversed()) {
            weakReference.get()?.finish()
        }
    }

    /**
     * 清除activity栈和所有回调
     */
    fun clearActivityStackAndCallback() {
        for (i in callbackStack.indices.reversed()) {
            callbackStack[i].get()?.onDestroyedSelf()
        }
        finishAllActivity()
        clearAll()
    }

    /**
     * 强制杀掉App进程，暴力方式
     */
    fun killApp() {
        clearActivityStackAndCallback()
        Process.killProcess(Process.myPid())
    }

    fun addActivityCycleCallback(callback: ActivityLifecycleCallback) {
        for (i in callbackStack.indices.reversed()) {
            if (callbackStack[i].get() == callback) {
                return
            }
        }
        callbackStack.add(WeakReference(callback))
    }

    fun removeActivityCycleCallback(callback: ActivityLifecycleCallback) {
        for (i in callbackStack.indices.reversed()) {
            if (callbackStack[i].get() == callback) {
                callbackStack.removeAt(i)
                return
            }
        }
    }

    companion object {
        val INSTANCE = GlobalLifecycleObserver()

        fun restartApp(gotoLogin: Boolean = false) {
            INSTANCE.getTopActivity().navigateToRestart(gotoLogin)
        }

        /**
         * 重启应用
         */
        fun restartApp(context: Context) {
            val intent = context.packageManager.getLaunchIntentForPackage(context.packageName)
            intent?.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }
    }
}

interface ActivityLifecycleCallback {
    fun onBackground()
    fun onForeground()
    fun onDestroyedSelf()
}