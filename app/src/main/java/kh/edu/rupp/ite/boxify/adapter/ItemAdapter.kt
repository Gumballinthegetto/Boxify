package kh.edu.rupp.ite.boxify.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kh.edu.rupp.ite.boxify.data.model.ItemModel
import kh.edu.rupp.ite.boxify.databinding.ItemLayoutBinding

class ItemAdapter (private val itemList : ArrayList<ItemModel>) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemLayoutBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]
        holder.bind(item)
    }
    class ViewHolder(val layoutBinding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(layoutBinding.root) {
        @SuppressLint("SimpleDateFormat")
        fun bind(itemModel: ItemModel) {
            Glide.with(layoutBinding.root.context).load(itemModel.image).into(layoutBinding.itemImageTv)
            layoutBinding.itemNameTv.text = itemModel.name ?: "null"
            layoutBinding.priceTv.text = String.format("$%.2f", itemModel.price ?: 0.0)
            layoutBinding.quantityTv.text = String.format("%d unit", itemModel.quantity?: 0)
        }

    }
}


