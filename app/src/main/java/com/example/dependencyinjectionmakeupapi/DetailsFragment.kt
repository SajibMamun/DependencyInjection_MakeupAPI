package com.example.dependencyinjectionmakeupapi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import coil.load
import com.example.dependencyinjectionmakeupapi.api.ProductService
import com.example.dependencyinjectionmakeupapi.api.RetrofitClient
import com.example.dependencyinjectionmakeupapi.databinding.FragmentDetailsBinding
import com.example.dependencyinjectionmakeupapi.dataclass.ResponseProductItem
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@AndroidEntryPoint
class DetailsFragment : Fragment() {

      lateinit var binding: FragmentDetailsBinding
    @Inject
    lateinit var service: ProductService
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)


        var ProductID=requireArguments().getInt(PRODUCT_KEY)

        var CallApiServiceById= service.getProductsById(ProductID)


        CallApiServiceById.enqueue(object : Callback<ResponseProductItem> {
            override fun onFailure(call: Call<ResponseProductItem>?, t: Throwable?) {


                if (t != null) {
                    Toast.makeText(requireContext(),"${t.message}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onResponse(call: Call<ResponseProductItem>?, response: Response<ResponseProductItem>?) {
                if (response != null) {
                    if(response.code()==200) {

                        var product=response.body()


                        binding.apply {

                            if (product != null) {
                                productName.text=product.name
                                productDescription.text=product.description
                                productImage.load(product.imageLink)
                                productPrice.text="${product.priceSign} ${product.price} ${product.currency}"
                                productTags.text=product.tagList.toString()
                            }
                        }

                    }
                }
            }

        })





        return binding.root
    }

    companion object {

        const val PRODUCT_KEY="productid"

    }
}