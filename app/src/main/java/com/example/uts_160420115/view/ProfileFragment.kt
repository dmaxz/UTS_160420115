package com.example.uts_160420115.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import com.example.uts_160420115.R
import com.example.uts_160420115.model.User
import com.example.uts_160420115.viewmodel.UserVM
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileFragment : Fragment() {
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
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    private lateinit var userVM: UserVM
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as? MainActivity)?.supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)

        val navHostFragment =
            activity?.supportFragmentManager?.findFragmentById(R.id.navHost) as NavHostFragment

        val sharedP = activity?.getSharedPreferences("sharedP", Context.MODE_PRIVATE)
        val username = sharedP?.getString("username", "")
        Log.e("username_main", username.toString())

        val fragment = this
        lifecycleScope.launch {
            userVM = ViewModelProvider(fragment)[UserVM::class.java]
            var userStatus = userVM.refreshOneSus(username!!)
            var user = userStatus[0] as User
            Log.e("user_profilefragment", user.toString())

            val txtN = view?.findViewById<TextView>(R.id.txtNamePFragment)
            val txtUN = view?.findViewById<TextView>(R.id.txtUsernamePFragment)
            val txtEmail = view?.findViewById<TextView>(R.id.txtEmailPFragment)
            val txtBalance = view?.findViewById<TextView>(R.id.txtBalancePFragment)
            val txtPhone = view?.findViewById<TextView>(R.id.txtPhonePFragment)
            txtN?.text = "Welcome, ${user.name.toString()}"
            txtUN?.text = "Username: ${user.username.toString()}"
            txtEmail?.text = "Email: ${user.email.toString()}"
            txtBalance?.text = "Balance: ${user.balance.toString()}"
            txtPhone?.text = "Phone: ${user.phone.toString()}"

        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ProfileFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProfileFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}