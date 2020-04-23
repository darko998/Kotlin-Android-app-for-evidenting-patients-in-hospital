package rs.raf.projekat1.darko_dimitrijevic_rn9418.view.activity

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_change_user_details.*
import kotlinx.android.synthetic.main.fragment_profile.*
import rs.raf.projekat1.darko_dimitrijevic_rn9418.R

class ChangeUserDetailsActivity : AppCompatActivity(R.layout.activity_change_user_details) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fillUserDetails()

        init()
    }

    fun init() {
        initChangeButton()
        initGiveUpButton()
    }

    fun initChangeButton() {
        button_change_edit.setOnClickListener {
            val name = tv_name_for_edit.text.toString()
            val lastName = tv_last_name_for_edit.text.toString()
            val hospital = tv_hospital_for_edit.text.toString()

            if (name == "" || lastName == "" || hospital == "") {
                Toast.makeText(this, "Morate popuniti sva polja!", Toast.LENGTH_SHORT).show()
            } else {
                val sharedPreferences = getSharedPreferences(packageName, Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putString(LoginActivity.USER_NAME, name)
                editor.putString(LoginActivity.USER_LAST_NAME, lastName)
                editor.putString(LoginActivity.USER_HOSPITAL, hospital)
                editor.commit()

                finish()
            }
        }
    }

    fun initGiveUpButton() {
        button_give_up_edit.setOnClickListener {
            finish()
        }
    }

    fun fillUserDetails() {
        val sharedPreferences = getSharedPreferences(packageName, Context.MODE_PRIVATE)

        tv_name_for_edit.setText(sharedPreferences.getString(LoginActivity.USER_NAME, "-1"))
        tv_last_name_for_edit.setText(sharedPreferences.getString(LoginActivity.USER_LAST_NAME, "-1"))
        tv_hospital_for_edit.setText(sharedPreferences.getString(LoginActivity.USER_HOSPITAL, "-1"))
    }
}