package com.example.fontapp.reAdapter

import android.annotation.SuppressLint
import android.content.Context

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fontapp.callBack.PopularClickListener
import com.example.fontapp.dataModel.RecyclerItemsModel
import com.example.fontapp.databinding.NewBottomMenuItemsLayoutBinding

class BottomMenuAdapter(
    var listItems: ArrayList<RecyclerItemsModel>
) :
    RecyclerView.Adapter<BottomMenuAdapter.ViewHolder>() {

    var callBack: PopularClickListener? = null
    private var selection: Int? = null
    lateinit var mContext: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mContext = parent.context

        val binding =
            NewBottomMenuItemsLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.thumbnailShow.setImageResource(listItems[position].image)
        holder.tvTitle.text = listItems[position].title

    }

    override fun getItemCount(): Int {
        return listItems.size
    }

    inner class ViewHolder(view: NewBottomMenuItemsLayoutBinding) :
        RecyclerView.ViewHolder(view.root) {

        var thumbnailShow: ImageView
        var tvTitle: TextView

        init {
            thumbnailShow = view.imageView5
            tvTitle = view.textView2

            view.root.setOnClickListener {
                callBack?.onPopularClick(listItems[adapterPosition].callBackValue)
            }
        }
    }

    fun upDateCallBack(callBackClick: PopularClickListener) {
        this.callBack = callBackClick
    }

    @SuppressLint("NotifyDataSetChanged")
    fun upIconList(newList: ArrayList<RecyclerItemsModel>) {
        this.listItems = newList
        notifyDataSetChanged()
    }

}