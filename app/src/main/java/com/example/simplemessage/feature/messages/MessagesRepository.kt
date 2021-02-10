package com.example.simplemessage.feature.messages

import com.example.simplemessage.data.apis.ApiService
import com.example.simplemessage.data.models.Post
import com.example.simplemessage.db.MessagesDao
import com.example.simplemessage.util.NetworkUtil
import kotlinx.coroutines.flow.Flow

class MessagesRepository(
    val dao: MessagesDao,
    val api: ApiService,
    val networkUtil: NetworkUtil
) {
    fun getPosts(): Flow<List<Post>> = dao.getPosts()
    suspend fun insertPosts(list: List<Post>) = dao.insertPosts(list)

    suspend fun responseGetPosts(): List<Post>? {
        val response = api.getApiPosts()

        if (response.isSuccessful) {
            val body = response.body()
            return body!!.posts
        }
        return null
    }

    suspend fun deletePost(post: Post) = dao.deletePost(post)

    suspend fun updatePost(post: Post) = dao.updatePost(post)

    suspend fun insertPost(post: Post): Long = dao.insertPost(post)

    fun isConnected(): Boolean = networkUtil.isConnected()


}