package rs.raf.projekat1.nebojsa_vasovic_rn6518.view.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import rs.raf.projekat1.nebojsa_vasovic_rn6518.R

class LoginActivity : AppCompatActivity(R.layout.activity_login) {

    companion object{
        const val pin = 1234
        const val NAME_KEY = "name"
        const val LAST_NAME_KEY = "lastname"
        const val HOSPITAL_KEY = "hospital"
        const val LOGIN_KEY = "login"
    }

    private lateinit var ime: String
    private lateinit var prezime: String
    private lateinit var bolnica: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        login_btn_prijava.setOnClickListener {
            if (checkData()) {
                storeData()
                val intent = Intent(this, MainScreenActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }


    private fun checkData(): Boolean {
        if (!checkIsNull())
            return false

        ime = login_et_ime.text.toString().trim()
        if (ime.isEmpty()) {
            Toast.makeText(
                this,
                "Polje \"Unesite ime\" ne sme biti prazno",
                Toast.LENGTH_SHORT
            ).show()
            return false
        }

        prezime = login_et_prezime.text.toString().trim()
        if (prezime.isEmpty()) {
            Toast.makeText(
                this,
                "Polje \"Unesite prezime\" ne sme biti prazno",
                Toast.LENGTH_SHORT
            ).show()
            return false
        }

        bolnica = login_et_ime_bolnice.text.toString().trim()
        if (bolnica.isEmpty()) {
            Toast.makeText(
                this,
                "Polje \"Unesite ime bolnice…\" ne sme biti prazno",
                Toast.LENGTH_SHORT
            ).show()
            return false
        }

        if(login_et_pin.text.toString().trim().isEmpty()) {
            Toast.makeText(
                this,
                "Polje \"Unesite pin\" ne sme biti prazno",
                Toast.LENGTH_SHORT
            ).show()
            return false
        }

        if(login_et_pin.text.toString().trim().length < 4) {
            Toast.makeText(
                this,
                "Polje \"Unesite pin\" ima manje od 4 cifre",
                Toast.LENGTH_SHORT
            ).show()
            return false
        }

        if(login_et_pin.text.toString().trim().toInt() != pin) {
            Toast.makeText(
                this,
                "Polje \"Unesite pin\" je pogrešne vrednosti",
                Toast.LENGTH_SHORT
            ).show()
            return false
        }

        return true

    }

    private fun checkIsNull(): Boolean {
        if (login_et_ime.text == null || login_et_prezime == null ||
            login_et_ime_bolnice == null || login_et_pin == null
        )
            return false
        return true
    }

    private fun storeData() {
        val sharedPreferences = getSharedPreferences(packageName, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(NAME_KEY, ime)
        editor.putString(LAST_NAME_KEY, prezime)
        editor.putString(HOSPITAL_KEY, bolnica)
        editor.putBoolean(LOGIN_KEY, true)
        editor.apply()
    }
}
