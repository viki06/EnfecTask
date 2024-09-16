package com.example.sampleapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sampleapp.R
import com.example.sampleapp.databinding.ItemImageBinding
import com.example.sampleapp.model.Model

class ImageRecyclerAdapter : RecyclerView.Adapter<ImageRecyclerAdapter.ViewHolder>() {

    private var mImageList: MutableList<Model.ImageData> = mutableListOf()

    fun setData(imageList: MutableList<Model.ImageData>) {

        mImageList = imageList

        notifyDataSetChanged()

    }

    class ViewHolder(val binding: ItemImageBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder = ViewHolder(
        ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun getItemCount(): Int = mImageList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        mImageList[position].run {

            Glide
                .with(holder.itemView.context)
                .load(url)
                .placeholder(R.drawable.no_image)
                .into(holder.binding.imageView)

            holder.binding.title.text = title

        }

    }

}