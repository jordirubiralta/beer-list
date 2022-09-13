package com.jrubiralta.beerlistapp.presentation.ui.beerlist.adapter

import android.content.Context
import android.net.Uri
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.jrubiralta.beerlistapp.R
import com.jrubiralta.beerlistapp.databinding.BeerItemViewBinding
import com.jrubiralta.beerlistapp.domain.model.BeerModel

class BeerItemView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding: BeerItemViewBinding =
        BeerItemViewBinding.inflate(
            LayoutInflater.from(context),
            this,
            true
        )

    init {
        layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

    fun bind(
        model: BeerModel,
        onClick: (BeerModel) -> Unit
    ) {
        Glide.with(context)
            .load(model.img?.let { Uri.parse(model.img) } ?: null)
            .fitCenter()
            .placeholder(R.drawable.placeholder)
            .into(binding.beerImage)

        binding.beerName.text = model.name
        binding.rootCard.setOnClickListener { onClick.invoke(model) }
    }
}