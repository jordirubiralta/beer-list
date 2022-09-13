package com.jrubiralta.beerlistapp.presentation.commons

import android.view.View
import androidx.recyclerview.widget.RecyclerView

class ViewWrapper<out V : View>(val view: V) : RecyclerView.ViewHolder(view)
