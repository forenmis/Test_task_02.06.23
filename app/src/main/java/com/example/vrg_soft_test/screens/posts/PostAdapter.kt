package com.example.vrg_soft_test.screens.posts

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.vrg_soft_test.R
import com.example.vrg_soft_test.databinding.ItemPostBinding
import com.example.vrg_soft_test.entity.Post
import com.example.vrg_soft_test.utils.relativeTime
import com.example.vrg_soft_test.utils.setImage

class PostAdapter : RecyclerView.Adapter<PostAdapter.VH>() {
    private val posts = mutableListOf<Post>()
    var callbackOnClick: ((String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = posts.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        val post = posts[position]
        with(holder.binding.ivImage) {
            if (post.image.isNullOrEmpty()) {
                isVisible = false
                setOnClickListener(null)
            } else {
                isVisible = true
                setImage(post.image)
                setOnClickListener {
                    callbackOnClick?.invoke(post.image)
                }
            }
        }
        holder.feelItem(post)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateItems(newPosts: List<Post>) {
        posts.clear()
        posts.addAll(newPosts)
        notifyDataSetChanged()
    }

    class VH(val binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root) {
        fun feelItem(post: Post) {
            with(binding) {
                tvAuthor.text = post.authorName
                tvTitle.text = post.title
                tvCreatedAt.text = post.created.relativeTime()
                tvNumComments.text = root.context.getString(R.string.comments, post.numComments)
            }
        }
    }
}