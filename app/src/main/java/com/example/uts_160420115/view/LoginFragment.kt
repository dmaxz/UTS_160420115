package com.example.uts_160420115.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.uts_160420115.R
import com.example.uts_160420115.model.User
import com.example.uts_160420115.viewmodel.UserVM
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var userVM: UserVM

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? MainActivity)?.supportActionBar?.hide()
        activity?.findViewById<BottomNavigationView>(R.id.bottomNav)?.visibility = View.GONE
        val btnLogin = view?.findViewById<Button>(R.id.btnLogin)
        btnLogin?.setOnClickListener {

            val txtU = view?.findViewById<EditText>(R.id.txtUsername)
            val txtP = view?.findViewById<EditText>(R.id.txtPassword)
            if (txtP?.text.toString() == "") {
                Toast.makeText(activity, "User not found", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val fragment = this
            GlobalScope.launch {
                userVM = ViewModelProvider(fragment)[UserVM::class.java]
                var userStatus = userVM.refreshOneSus(txtU?.text.toString(), txtP?.text.toString())
                val sharedP = activity?.getSharedPreferences("sharedP", Context.MODE_PRIVATE)
                val edit = sharedP?.edit()
                val user = userStatus[0] as User
                val status = userStatus[1] as Boolean
                Log.e("user_login", user.toString())
                if (status) {
                    edit?.putString("username", user.username.toString())
                    edit?.putInt("id", user.id as Int)
                    edit?.apply()
                    val action = LoginFragmentDirections.actionLoginFragmentToMainFragment()
                    CoroutineScope(Dispatchers.Main).launch {
                        Navigation.findNavController(it).navigate(action)
                    }
                } else {
                    CoroutineScope(Dispatchers.Main).launch {
                        Toast.makeText(activity, "User not found", Toast.LENGTH_SHORT).show()
                    }

                }
            }
            //replace this with password checking from read json


        }
    }

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
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LoginFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LoginFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}