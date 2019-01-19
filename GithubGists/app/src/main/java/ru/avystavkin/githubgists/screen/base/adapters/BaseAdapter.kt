package ru.avystavkin.githubgists.screen.base.adapters

import android.view.View
import androidx.annotation.CallSuper
import androidx.recyclerview.widget.RecyclerView
import ru.avystavkin.githubgists.screen.interfaces.OnItemClickListener
import java.util.*

abstract class BaseAdapter<VH : RecyclerView.ViewHolder, T>(items: List<T>) : BaseRecyclerViewAdapter<VH>() {

    private val mItems = ArrayList<T>(items)

    var onItemClickListener: OnItemClickListener<T>? = null

    private val mInternalListener = View.OnClickListener { view ->
        if (onItemClickListener != null) {
            val position = view.tag as Int
            val item = mItems[position]
            onItemClickListener!!.onItemClick(item)
        }
    }

    fun add(value: T) {
        mItems.add(value)
        refreshRecycler()
    }

    fun changeDataSet(values: List<T>) {
        mItems.clear()
        mItems.addAll(values)
        refreshRecycler()
    }

    fun clear() {
        mItems.clear()
        refreshRecycler()
    }


    @CallSuper
    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.itemView.tag = position
        holder.itemView.setOnClickListener(mInternalListener)
    }

    fun getItem(position: Int): T {
        return mItems[position]
    }

    override fun getItemCount(): Int {
        return mItems.size
    }
}
