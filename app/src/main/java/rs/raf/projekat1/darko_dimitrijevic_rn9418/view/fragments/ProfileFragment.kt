package rs.raf.projekat1.darko_dimitrijevic_rn9418.view.fragments

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_profile.*

import rs.raf.projekat1.darko_dimitrijevic_rn9418.R
import rs.raf.projekat1.darko_dimitrijevic_rn9418.view.activity.ChangeUserDetailsActivity
import rs.raf.projekat1.darko_dimitrijevic_rn9418.view.activity.LoginActivity
import kotlin.math.sign

class ProfileFragment : Fragment() {

    lateinit var tvNameValue: TextView
    lateinit var tvLastNameValue: TextView
    lateinit var tvHospital: TextView
    lateinit var logOutButton: Button
    lateinit var editButton: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        fillUserData(view)

        init(view)

        return view
    }

    override fun onStart() {
        super.onStart()

        refreshUserData()
    }

    fun init(view: View){
        initEditButton(view)
        initSignOutButton(view)
    }

    fun initEditButton(view: View) {
        editButton = view.findViewById(R.id.button_edit)

        editButton.setOnClickListener {
            val intent = Intent(activity, ChangeUserDetailsActivity::class.java)
            startActivity(intent)
        }
    }

    fun initSignOutButton(view: View) {
        logOutButton = view.findViewById(R.id.button_log_out)

        logOutButton.setOnClickListener {
            val sharedPreferences = activity?.getSharedPreferences(activity?.packageName, Context.MODE_PRIVATE)
            val editor = sharedPreferences?.edit()
            editor?.clear()
            editor?.commit()

            val intent = Intent(activity, LoginActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }
    }

    fun fillUserData(view: View) {
        val sharedPreferences = activity?.getSharedPreferences(activity?.packageName, Context.MODE_PRIVATE)
        val name = sharedPreferences?.getString(LoginActivity.USER_NAME,"-1")
        val lastName = sharedPreferences?.getString(LoginActivity.USER_LAST_NAME, "-1")
        val hospital = sharedPreferences?.getString(LoginActivity.USER_HOSPITAL, "-1")

        tvNameValue = view.findViewById(R.id.tv_name_value)
        tvLastNameValue = view.findViewById(R.id.tv_last_name_value)
        tvHospital = view.findViewById(R.id.tv_hospital_value)


        tvNameValue.text = name
        tvLastNameValue.text = lastName
        tvHospital.text = hospital
    }


    fun refreshUserData() {
        val sharedPreferences = activity?.getSharedPreferences(activity?.packageName, Context.MODE_PRIVATE)
        val name = sharedPreferences?.getString(LoginActivity.USER_NAME,"-1")
        val lastName = sharedPreferences?.getString(LoginActivity.USER_LAST_NAME, "-1")
        val hospital = sharedPreferences?.getString(LoginActivity.USER_HOSPITAL, "-1")

        tvNameValue.text = name
        tvLastNameValue.text = lastName
        tvHospital.text = hospital
    }

}
