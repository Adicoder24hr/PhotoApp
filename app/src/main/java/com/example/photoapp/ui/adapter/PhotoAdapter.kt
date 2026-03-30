package com.example.photoapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.photoapp.databinding.ItemPhotoBinding
import com.example.photoapp.model.Photo
import com.example.photoapp.R

class PhotoAdapter : PagingDataAdapter<Photo, PhotoAdapter.PhotoViewHolder>(DIFF_CALLBACK) {
    inner class PhotoViewHolder(private val binding: ItemPhotoBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(photo: Photo){
            binding.titleText.text = photo.title

//            Glide.with(binding.imageView.context)
//                .load(photo.thumbnailUrl)
//                .error(R.drawable.user)
//                .placeholder(R.drawable.user)
//                .into(binding.imageView)   this doesn't work as the image urls provided by the api are broken


            Glide.with(binding.imageView.context)
                .load("https://picsum.photos/200?random=${photo.id}")
                .error(R.drawable.user)
                .placeholder(R.drawable.user)
                .into(binding.imageView)
        }
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val photo = getItem(position)
        photo?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val binding = ItemPhotoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return PhotoViewHolder(binding)
    }

    companion object{
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Photo>(){
            override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
                return oldItem == newItem
            }
        }
    }
}