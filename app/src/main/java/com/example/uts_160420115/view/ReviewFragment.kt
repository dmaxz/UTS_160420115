package com.example.uts_160420115.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.uts_160420115.R
import com.example.uts_160420115.viewmodel.CommentVM

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ReviewFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ReviewFragment : Fragment() {
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

    private lateinit var commentVM: CommentVM
    private val commentAdapter = CommentAdapter(arrayListOf())
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val restoId = ReviewFragmentArgs.fromBundle(requireArguments()).restoId
        Log.e("resto_id", restoId.toString() )

        commentVM = ViewModelProvider(this)[CommentVM::class.java]
        commentVM.refresh(restoId,this)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recViewReview)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = commentAdapter
        observeViewModel()
    }

    fun observeViewModel(){
        commentVM.commentLD.observe(viewLifecycleOwner, Observer {
            commentAdapter.updateRestaurants(it)
        })
//        val txtError = view?.findViewById<TextView>(R.id.txtError)
        val recyclerView = view?.findViewById<RecyclerView>(R.id.recViewHistory)
//        val progressBar = view?.findViewById<ProgressBar>(R.id.progressBar)
        commentVM.commentLoadErrorLD.observe(viewLifecycleOwner, Observer {

        })
        commentVM.loadingLD.observe(viewLifecycleOwner, Observer {
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


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_review, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ReviewFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ReviewFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}