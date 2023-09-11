package com.example.dependencyinjectionmakeupapi

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.dependencyinjectionmakeupapi.databinding.ItemDesignRcvBinding

    class ProductAdapter(var prdouctListener:ProductListener):androidx.recyclerview.widget.ListAdapter<ResponseProductItem,ProductAdapter.ProductViewHolder> (
        COMPARATOR){




        interface ProductListener{
            fun productClickedListener(productId:Int)
        }

        class ProductViewHolder(var binding: ItemDesignRcvBinding):
            RecyclerView.ViewHolder(binding.root)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
            return ProductViewHolder(ItemDesignRcvBinding.inflate(LayoutInflater.from(parent.context),parent,false))

        }

        override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
            getItem(position)?.let {
                holder.binding.apply {
                    productName.text=it.name
                    productDescription.text=it.description
                    productImage.load(it.imageLink)
                    productPrice.text="${it.priceSign} ${it.price} ${it.currency}"

                    holder.itemView.setOnClickListener {_->
                        prdouctListener.productClickedListener(it.id!!)
                    }

                }
            }
        }


        companion object{
            var COMPARATOR=object : DiffUtil.ItemCallback<ResponseProductItem>(){
                override fun areItemsTheSame(
                    oldItem: ResponseProductItem,
                    newItem: ResponseProductItem
                ): Boolean {
                    return oldItem==newItem
                }

                override fun areContentsTheSame(
                    oldItem: ResponseProductItem,
                    newItem: ResponseProductItem
                ): Boolean {
                    return  oldItem.id==newItem.id
                }


            }
        }
}