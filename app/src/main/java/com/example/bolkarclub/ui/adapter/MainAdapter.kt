package com.example.bolkarclub.ui.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bolkarclub.R
import com.example.bolkarclub.model.DataModel
import de.hdodenhof.circleimageview.CircleImageView


class MainAdapter(
    private var mContext: Context?,
    private var dataModel: ArrayList<DataModel>,
    private var mListener: MemberAdapter.OnItemClickListener
) :
    RecyclerView.Adapter<MainAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapter.ItemViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_main, parent, false)
        return ItemViewHolder(itemView)
    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById<TextView>(R.id.name)
        val ivProfile = itemView.findViewById<CircleImageView>(R.id.profile_image)

        val categoryType = itemView.findViewById<TextView>(R.id.category)
        val micImg = itemView.findViewById<ImageView>(R.id.mic)
        val hostImg = itemView.findViewById<ImageView>(R.id.host)

    }

    interface OnItemClickListener {
        fun onItemClick(data: ArrayList<DataModel>, position: Int, view: View)


    }

    override fun onBindViewHolder(holder: MainAdapter.ItemViewHolder, position: Int) {
        val firstSpace: Int =
            dataModel[position].name!!.indexOf(" ") // detect the first space character
        val firstName: String = dataModel[position].name!!.substring(
            0,
            firstSpace
        ) // get everything upto the first space character

        val lastName: String = dataModel[position].name!!.substring(firstSpace).trim()

        holder.name.text = firstName
        mContext?.let {
            Glide.with(it).load(dataModel[position].profileImg)
                .placeholder(R.drawable.user).into(holder.ivProfile)
        }
        holder.categoryType.text = dataModel[position].category.toString()

        if (holder.categoryType.text == "Host") {
            holder.hostImg.visibility = View.VISIBLE
            holder.micImg.visibility = View.GONE
            holder.ivProfile.borderWidth =4

            holder.ivProfile.borderColor = Color.parseColor("#0EBCB6")

        } else {
            holder.hostImg.visibility = View.GONE
            holder.micImg.visibility = View.VISIBLE
            holder.ivProfile.borderWidth =0
//            holder.ivProfile.borderColor = Color.parseColor("#00FFFFFF")



        }

    }

    override fun getItemCount(): Int {
        return dataModel.size

    }
}

private fun CircleImageView.borderColor(parseColor: Int) {

}
