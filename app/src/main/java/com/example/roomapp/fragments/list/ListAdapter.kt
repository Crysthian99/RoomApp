package com.example.roomapp.fragments.list
import android.app.PendingIntent.getActivity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.roomapp.AESKnowledgeFcatory.encrypt
import com.example.roomapp.R
import com.example.roomapp.data.User
import kotlinx.android.synthetic.main.custom_row.view.*
import android.content.SharedPreferences
import com.example.roomapp.HashUtils.sha256
import com.example.roomapp.MainActivity


class ListAdapter(): RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var userList = emptyList<User>()


    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false))
    }

    override fun getItemCount(): Int {
       return userList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position]
        holder.itemView.id_txt.text = currentItem.id.toString()
        holder.itemView.firstName_txt.text = currentItem.firstName
        holder.itemView.lastName_txt.text = encrypt(currentItem.lastName,sha256(currentItem.salt.toString()))
        holder.itemView.salt_txt.text = currentItem.salt
    }

    fun setData(user: List<User>){
        this.userList = user
        notifyDataSetChanged()
    }
}