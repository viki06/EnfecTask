package com.example.sampleapp.ui.user

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sampleapp.R
import com.example.sampleapp.databinding.ItemUserBinding
import com.example.sampleapp.model.Model

class UserListAdapter : RecyclerView.Adapter<UserListAdapter.ViewHolder>() {

    private var userList = mutableListOf<Model.User>()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: MutableList<Model.User>) {

        userList = list

        notifyDataSetChanged()

    }

    class ViewHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder = ViewHolder(
        ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun getItemCount(): Int = userList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        userList[position].let { user ->

            holder.binding.let { binding ->

                binding.userID.text = user.id.toString()

                binding.latAndLong.text = holder.itemView.context.getString(
                    R.string.prefix_lat_lng,
                    user.address.geo.lat,
                    user.address.geo.lng
                )

                binding.companyName.text = user.company.name

            }

        }
    }
}