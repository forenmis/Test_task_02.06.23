package com.example.vrg_soft_test.screens.posts

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.vrg_soft_test.app.App
import com.example.vrg_soft_test.data.network.NetworkManager
import com.example.vrg_soft_test.entity.Post
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class PostsViewModel(application: Application) : AndroidViewModel(application), CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    private val networkManager: NetworkManager
    private var after: String? = null

    val postsLD = MutableLiveData<List<Post>>()
    val progressLD = MutableLiveData(false)

    init {
        networkManager = App.getInstance(application).networkManager
        loadPosts()
    }

    fun loadPosts() = launch {
        progressLD.value = true
        val postPage = withContext(Dispatchers.IO) { networkManager.loadPosts(after) }
        after = postPage.after
        val oldPosts = postsLD.value
        postsLD.value = if (oldPosts != null) oldPosts + postPage.posts else postPage.posts
        progressLD.value = false
    }
}