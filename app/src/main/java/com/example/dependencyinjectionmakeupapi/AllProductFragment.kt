package com.example.dependencyinjectionmakeupapi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.dependencyinjectionmakeupapi.api.ProductService
import com.example.dependencyinjectionmakeupapi.api.RetrofitClient
import com.example.dependencyinjectionmakeupapi.databinding.FragmentAllProductBinding
import com.example.dependencyinjectionmakeupapi.dataclass.ResponseProduct
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@AndroidEntryPoint
class AllProductFragment: Fragment(),ProductAdapter.ProductListener {
    lateinit var binding: FragmentAllProductBinding

    lateinit var adapter: ProductAdapter
    @Inject
    lateinit var service: ProductService

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding= FragmentAllProductBinding.inflate(inflater,container,false)

//Rcv Adapter
        adapter=ProductAdapter(this)
        binding.ProductRCV.adapter=adapter










        var CallApiService= service.getAllProducts()

        CallApiService.enqueue(object : Callback<ResponseProduct> {
            override fun onFailure(call: Call<ResponseProduct>?, t: Throwable?) {


                if (t != null) {
                    Toast.makeText(requireContext(),"${t.message}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onResponse(call: Call<ResponseProduct>?, response: Response<ResponseProduct>?) {
                if (response != null) {
                    if(response.code()==200) {

                        adapter.submitList(response.body())



                    }
                }
            }

        })


        return binding.root
    }

    companion object {

    }

    override fun productClickedListener(productId: Int) {


        var bundle=Bundle()
        bundle.putInt(DetailsFragment.PRODUCT_KEY,productId)
        findNavController().navigate(R.id.action_allProductFragment_to_detailsFragment,bundle)
    }
}