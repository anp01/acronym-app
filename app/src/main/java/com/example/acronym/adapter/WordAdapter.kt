package com.example.acronym.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.acronym.databinding.RowWordBinding
import com.example.acronym.models.Lf

class WordAdapter : ListAdapter<Lf, RecyclerView.ViewHolder>(WordDiffUtilsCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = RowWordBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WordViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as WordViewHolder).bind(getItem(position))
    }

    class WordViewHolder(private val binding: RowWordBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(word: Lf) {
            binding.data = word
        }
    }
}

class WordDiffUtilsCallback : DiffUtil.ItemCallback<Lf>() {
    override fun areItemsTheSame(oldItem: Lf, newItem: Lf): Boolean {
        return oldItem.lf == newItem.lf
    }

    override fun areContentsTheSame(oldItem: Lf, newItem: Lf): Boolean {
        return oldItem == newItem
    }
}