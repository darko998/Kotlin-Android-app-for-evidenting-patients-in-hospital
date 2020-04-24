package rs.raf.projekat1.darko_dimitrijevic_rn9418.view.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.InputType
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*
import rs.raf.projekat1.darko_dimitrijevic_rn9418.R

class LoginActivity: AppCompatActivity(R.layout.activity_login) {

    companion object {
        const val USER_NAME = "name"
        const val USER_LAST_NAME = "last_name"
        const val USER_HOSPITAL = "hospital"
        const val PIN = "1234"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        login_et_pin.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD


        login_button_sign_in.setOnClickListener {

            val name = login_et_name.text.toString()
            val lastName = login_et_last_name.text.toString()
            val hospital = login_et_hospital.text.toString()
            val pin = login_et_pin.text.toString()


            if(name == "" || lastName == "" || hospital == "" || pin == "") {
                Toast.makeText(this, "Morate popuniti sva polja!", Toast.LENGTH_LONG).show()
            } else if(pin.length != 4){
                Toast.makeText(this, "Pin mora imati tacno 4 cifre, a vas pin ima ${pin.length} cifre.", Toast.LENGTH_LONG).show()
            } else if(pin != PIN) {
                Toast.makeText(this, "Uneli ste pogresan PIN. Pokusajte ponovo.", Toast.LENGTH_SHORT).show()
            } else {
                /** Svi podaci su uneti i pin je ispravan. */

                val sharedPreferences = getSharedPreferences(packageName, Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()

                editor.putString(USER_NAME, name)
                editor.putString(USER_LAST_NAME, lastName)
                editor.putString(USER_HOSPITAL, hospital)

                editor.apply()

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

    }


}