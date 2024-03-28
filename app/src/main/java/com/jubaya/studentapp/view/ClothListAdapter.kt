package com.jubaya.studentapp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.jubaya.studentapp.databinding.ClothListItemBinding
import com.jubaya.studentapp.databinding.StudentListItemBinding
import com.jubaya.studentapp.model.Cloth
import com.jubaya.studentapp.model.Student
import java.util.Arrays

class ClothListAdapter(val clothList:ArrayList<Cloth>)
    : RecyclerView.Adapter<ClothListAdapter.ClothViewHolder>() {
    class ClothViewHolder(var binding: ClothListItemBinding)
        :RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClothViewHolder {
        val binding =ClothListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ClothListAdapter.ClothViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return clothList.size
    }

    override fun onBindViewHolder(holder: ClothViewHolder, position: Int) {
        var listOfSize = ""
        clothList[position].sizes?.forEach{
            listOfSize += " $it "
        }

        holder.binding.txtID.text = clothList[position].id + " :"
        holder.binding.txtType.text = clothList[position].type
        holder.binding.txtCountry.text = clothList[position].brand?.country + " -"
        holder.binding.txtBrand.text = clothList[position].brand?.name
        holder.binding.txtColor.text = "Color : " + clothList[position].color
        holder.binding.txtSize.text = "Size : " + listOfSize + " "
    }
    fun updateClothList(newClothList:ArrayList<Cloth>){
        clothList.clear()
        clothList.addAll(newClothList)

        notifyDataSetChanged()
    }


}