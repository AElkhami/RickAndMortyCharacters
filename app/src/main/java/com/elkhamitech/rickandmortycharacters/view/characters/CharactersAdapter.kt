package com.elkhamitech.rickandmortycharacters.view.characters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.elkhamitech.rickandmortycharacters.R
import com.elkhamitech.rickandmortycharacters.data.model.Character
import com.elkhamitech.rickandmortycharacters.databinding.ItemCharacterBinding
import javax.inject.Inject

/**
 * Created by A.Elkhami on 01,November,2021
 */
class CharactersAdapter :
    ListAdapter<Character, CharactersAdapter.ViewHolder>(DiffUtilCallback()) {

    class ViewHolder private constructor(private val binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(character: Character) {
            binding.character = character
        }

        companion object {

            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemCharacterBinding.inflate(
                    layoutInflater,
                    parent, false
                )
                return ViewHolder(binding)
            }

            @JvmStatic
            @BindingAdapter("imageUrl")
            fun setImageUrl(imgView: ImageView, imgUrl: String?) {
                Glide.with(imgView.context).load(imgView).load(imgUrl).into(imgView)
            }

            @JvmStatic
            @BindingAdapter("characterStatus")
            fun setCharacterStatus(view: View, status: String) {
                when {
                    status.equals("alive", ignoreCase = true) -> {
                        view.setBackgroundResource(R.drawable.green_circle)
                    }
                    status.equals("dead", ignoreCase = true) -> {
                        view.setBackgroundResource(R.drawable.red_circle)
                    }
                    else -> {
                        view.setBackgroundResource(R.drawable.yellow_circle)
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val character = getItem(position)
        holder.bind(character)
    }

    private class DiffUtilCallback : DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem == newItem
        }
    }
}