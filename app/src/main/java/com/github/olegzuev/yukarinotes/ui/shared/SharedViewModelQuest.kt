package com.github.olegzuev.yukarinotes.ui.shared

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.olegzuev.yukarinotes.data.Quest
import com.github.olegzuev.yukarinotes.db.MasterQuest
import kotlin.concurrent.thread

class SharedViewModelQuest : ViewModel() {
    val questList = MutableLiveData<List<Quest>>()
    val loadingFlag = MutableLiveData<Boolean>(false)
    var includeNormal = false
    var includeHard = false

    /***
     * 从数据库读取所有任务数据。
     */
    fun loadData() {
        if (questList.value.isNullOrEmpty()) {
            loadingFlag.value = true
            thread(start = true) {
                questList.postValue(MasterQuest().quest)
                loadingFlag.postValue(false)
            }
        }
    }
}