package com.example.bolkarclub.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bolkarclub.R
import com.example.bolkarclub.model.DataModel

class MemberAdapter(
    private var mContext: Context?,
    private var dataModel: ArrayList<DataModel>,
    private var mListener: OnItemClickListener
) : RecyclerView.Adapter<MemberAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_member, parent, false)
        return ItemViewHolder(itemView)
    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById<TextView>(R.id.name)
        val ivProfile=itemView.findViewById<ImageView>(R.id.ivMember)

    }

    interface OnItemClickListener {
        fun onItemClick(memberList: ArrayList<DataModel>, position: Int, view: View)

    }

    override fun onBindViewHolder(holder: MemberAdapter.ItemViewHolder, position: Int) {

        val firstSpace: Int =
            dataModel[position].name!!.indexOf(" ") // detect the first space character
        val firstName: String = dataModel[position].name!!.substring(
            0,
            firstSpace
        ) // get everything upto the first space character

        val lastName: String = dataModel[position].name!!.substring(firstSpace).trim()

        holder.name.text = firstName

        mContext?.let { Glide.with(it).load(dataModel[position].profileImg).placeholder(R.drawable.bolkar).into(holder.ivProfile) }
    }

    override fun getItemCount(): Int {
        return dataModel.size

    }
}