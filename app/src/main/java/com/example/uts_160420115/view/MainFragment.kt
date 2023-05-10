package com.example.uts_160420115.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.uts_160420115.R
import com.example.uts_160420115.viewmodel.RestaurantsVM
import com.google.android.material.bottomnavigation.BottomNavigationView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainFragment : Fragment() {
    private lateinit var restaurantsVM: RestaurantsVM
    private val restaurantsAdapter = RestaurantsAdapter(arrayListOf())
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
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navHostFragment =
            activity?.supportFragmentManager?.findFragmentById(R.id.navHost) as NavHostFragment



        (activity as? MainActivity)?.supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)
        (activity as? MainActivity)?.supportActionBar?.show()
        activity?.findViewById<BottomNavigationView>(R.id.bottomNav)?.visibility = View.VISIBLE
        val sharedP = activity?.getSharedPreferences("sharedP", Context.MODE_PRIVATE)
        val username = sharedP?.getString("username", "")
        Log.e("username_main", username.toString())

        restaurantsVM = ViewModelProvider(this)[RestaurantsVM::class.java]
        restaurantsVM.refresh()


        val recyclerView = view.findViewById<RecyclerView>(R.id.recViewRestaurants)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = restaurantsAdapter
        observeViewModel()
    }

    fun observeViewModel(){
        restaurantsVM.restaurantLD.observe(viewLifecycleOwner, Observer {
            restaurantsAdapter.updateRestaurants(it)
        })
//        val txtError = view?.findViewById<TextView>(R.id.txtError)
        val recyclerView = view?.findViewById<RecyclerView>(R.id.recViewRestaurants)
//        val progressBar = view?.findViewById<ProgressBar>(R.id.progressBar)
        restaurantsVM.restaurantLoadErrorLD.observe(viewLifecycleOwner, Observer {

        })
        restaurantsVM.loadingLD.observe(viewLifecycleOwner, Observer {
            if (it) {
                recyclerView?.visibility = View.GONE
//                progressBar?.visibility = View.VISIBLE
            }
            else{
                recyclerView?.visibility = View.VISIBLE
//                progressBar?.visibility = View.GONE
            }
        })
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MainFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MainFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}