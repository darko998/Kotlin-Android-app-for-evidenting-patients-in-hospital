package rs.raf.projekat1.darko_dimitrijevic_rn9418.view.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity

class SplashActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent: Intent

        if(isUserLoggedIn()){
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        } else {
            intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        finish()
    }

    /** Function that check is user logged in (check if exist user name) */
    fun isUserLoggedIn(): Boolean{
        val sharedPreferences = getSharedPreferences(packageName, Context.MODE_PRIVATE)
        val name = sharedPreferences.getString(LoginActivity.USER_NAME, "-1")

        return name != "-1"
    }

}