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
import com.example.uts_160420115.viewmodel.OrderVM

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HistoryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HistoryFragment : Fragment() {
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
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    private lateinit var orderVM: OrderVM
    private val orderAdapter = OrderAdapter(arrayListOf())

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? MainActivity)?.supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)
        val navHostFragment =
            activity?.supportFragmentManager?.findFragmentById(R.id.navHost) as NavHostFragment

        val navController = (navHostFragment).navController
        val sharedP = activity?.getSharedPreferences("sharedP", Context.MODE_PRIVATE)
        val userId = sharedP?.getInt("id", 0)
        Log.e("userid_history", userId.toString())
        orderVM = ViewModelProvider(this)[OrderVM::class.java]
        val fragment = this
        orderVM.refresh(userId!!, fragment)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recViewHistory)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = orderAdapter
        observeViewModel()
    }

    fun observeViewModel(){
        orderVM.orderLD.observe(viewLifecycleOwner, Observer {
            orderAdapter.updateOrders(it)
        })
//        val txtError = view?.findViewById<TextView>(R.id.txtError)
        val recyclerView = view?.findViewById<RecyclerView>(R.id.recViewRestaurants)
//        val progressBar = view?.findViewById<ProgressBar>(R.id.progressBar)
        orderVM.orderLoadErrorLD.observe(viewLifecycleOwner, Observer {

        })
        orderVM.loadingLD.observe(viewLifecycleOwner, Observer {
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
         * @return A new instance of fragment HistoryFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HistoryFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}