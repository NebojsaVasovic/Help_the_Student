package rs.raf.projekat1.nebojsa_vasovic_rn6518.view.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import rs.raf.projekat1.nebojsa_vasovic_rn6518.view.activities.LoginActivity.Companion.LOGIN_KEY

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPreferences = getSharedPreferences(packageName, Context.MODE_PRIVATE)
        val login = sharedPreferences.getBoolean(LOGIN_KEY, false)
        var intent = Intent(this, LoginActivity::class.java)

        if (login)
            intent = Intent(this, MainScreenActivity::class.java)
        startActivity(intent)
        finish()
    }
}