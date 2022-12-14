package com.example.fontapp.reAdapter


import android.content.Context
import android.graphics.Typeface
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.fontapp.R
import com.example.fontapp.billing.GBilling
import com.example.fontapp.callBack.FontAdapterCallBack
import com.example.fontapp.other.Utils
import java.io.File
import java.lang.Exception

class FontAdapter(
    private var mFontList: ArrayList<String>,
    private val mCallBack: FontAdapterCallBack
) : RecyclerView.Adapter<FontAdapter.ViewHolder>() {

    lateinit var mcontext: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mcontext = parent.context
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.re_font_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Log.d("myFontHolder", "${File(mFontList[position].toString()).nameWithoutExtension}")

        val fontNameInList = "${File(mFontList[position].toString()).nameWithoutExtension}"

        holder.fontName.text = fontNameInList

        try {
            holder.textFont.typeface =
                Typeface.createFromAsset(mcontext.assets, "font/" + mFontList[position])
        } catch (ex: Exception) {
            ex.printStackTrace()
        }

        if (position > 2) {
            if (GBilling.isSubscribedOrPurchasedSaved) {
                Log.e("mybp", "buy pro")
                holder.thumbnailShow.setImageResource(R.drawable.card_next)
            } else {
                Log.e("mybp", "not buy pro")
                holder.thumbnailShow.setImageResource(R.drawable.pro_icon)
            }
        } else {
            holder.thumbnailShow.setImageResource(R.drawable.card_next)
        }

    }

    override fun getItemCount(): Int {
        return mFontList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var textFont: TextView
        var fontName: TextView
        var thumbnailShow: ImageView

        init {

            textFont = itemView.findViewById(R.id.textView2)
            fontName = itemView.findViewById(R.id.textView3)
            thumbnailShow = itemView.findViewById(R.id.imageView4)

            itemView.setOnClickListener {
                mCallBack.setFont("${mFontList[adapterPosition].toString()}",adapterPosition)
            }
        }
    }

    // method for filtering our recyclerview items.
    fun filterList(filterList: ArrayList<String>) {
        // below line is to add our filtered
        // list in our course array list.
        mFontList = filterList
        // below line is to notify our adapter
        // as change in recycler view data.
        notifyDataSetChanged()
    }
}