package com.example.uts_160420115.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.uts_160420115.R
import com.example.uts_160420115.viewmodel.RestaurantVM
import com.google.android.material.textview.MaterialTextView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RestaurantFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RestaurantFragment : Fragment() {
    private lateinit var restaurantVM: RestaurantVM
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_restaurant, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val restoId = RestaurantFragmentArgs.fromBundle(requireArguments()).restoId
        Log.e("resto_id", restoId.toString() )
        restaurantVM = ViewModelProvider(this)[RestaurantVM::class.java]
        restaurantVM.fetch(restoId)
        observeViewModel()
        var btnMenu = view?.findViewById<Button>(R.id.btnMenu)
        btnMenu?.setOnClickListener {
            val action = RestaurantFragmentDirections.actionRestaurantFragmentToMenuFragment(restoId)
            Navigation.findNavController(it).navigate(action)
        }
        var btnReview = view?.findViewById<Button>(R.id.btnReview)
        btnReview?.setOnClickListener {
            val action = RestaurantFragmentDirections.actionRestaurantFragmentToReviewFragment(restoId)
            Navigation.findNavController(it).navigate(action)
        }
        var btnPromo = view?.findViewById<Button>(R.id.btnPromo)
        btnPromo?.setOnClickListener {
            val action = RestaurantFragmentDirections.actionRestaurantFragmentToPromoFragment(restoId)
            Navigation.findNavController(it).navigate(action)
        }
    }

    fun observeViewModel(){
        val txtName = view?.findViewById<MaterialTextView>(R.id.txtNamaRDetail)
        val txtAlamat = view?.findViewById<MaterialTextView>(R.id.txtAlamatRDetail)
        val txtPhone = view?.findViewById<MaterialTextView>(R.id.txtPhoneRDetail)
//        val txtInputPhone = view?.findViewById<TextInputLayout>(R.id.txtInputPhone)
//        val imgViewDetail = view?.findViewById<ImageView>(R.id.imageViewDetail)
        restaurantVM.restaurantLD.observe(viewLifecycleOwner, Observer {
            Log.e("resto_id_json", it.id.toString())
            txtName?.text = it.name.toString()
            txtAlamat?.text = it.address.toString()
            txtPhone?.text = it.phone.toString()
//            txtInputPhone?.editText?.setText(it.phone)
        })

    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RestaurantFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RestaurantFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}