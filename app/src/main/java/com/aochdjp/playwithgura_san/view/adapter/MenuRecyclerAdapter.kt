package com.aochdjp.playwithgura_san.view.adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.aochdjp.playwithgura_san.R
import com.aochdjp.playwithgura_san.databinding.LayoutMenuRowBinding
import com.aochdjp.playwithgura_san.model.Menu
import com.aochdjp.playwithgura_san.model.util.DiffUtilCallback

class MenuRecyclerAdapter(private val context: Context): RecyclerView.Adapter<MenuRecyclerAdapter.ViewHolder>() {
    var menus: List<Menu.MenuList>? = null
        set(value) {
            DiffUtil.calculateDiff(DiffUtilCallback(field, value)).dispatchUpdatesTo(this)
            field = value
        }

    private var clickListener: ((Menu.MenuList) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<LayoutMenuRowBinding>(LayoutInflater.from(context), R.layout.layout_menu_row, parent, false)

        return ViewHolder(binding)
    }

    override fun getItemCount() = menus?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ev = menus?.getOrNull(position) ?: return
        holder.binding.menu = ev
        holder.binding.root.setOnClickListener {
            clickListener?.invoke(ev)
        }
    }

    fun setOnClickListener(callback: (Menu.MenuList) -> Unit) {
        this.clickListener = callback
    }

    inner class ViewHolder(val binding: LayoutMenuRowBinding): RecyclerView.ViewHolder(binding.root)
}