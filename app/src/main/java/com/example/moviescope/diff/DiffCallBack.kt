package com.example.moviescope.diff

import androidx.recyclerview.widget.DiffUtil

class DiffCallBack<T : Any>(
    private val itemTheSame: (T, T) -> Boolean = { _, _ -> false },
    private val contentsTheSame: (T, T) -> Boolean = { _, _ -> false }
) : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return itemTheSame(oldItem, newItem)
    }

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return contentsTheSame(oldItem, newItem)
    }
}