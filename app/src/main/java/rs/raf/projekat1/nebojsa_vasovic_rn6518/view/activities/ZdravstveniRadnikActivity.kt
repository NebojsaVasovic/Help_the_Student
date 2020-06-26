package rs.raf.projekat1.nebojsa_vasovic_rn6518.view.activities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_zdravstveni_radnik.*
import rs.raf.projekat1.nebojsa_vasovic_rn6518.R
import rs.raf.projekat1.nebojsa_vasovic_rn6518.view.activities.LoginActivity.Companion.HOSPITAL_KEY
import rs.raf.projekat1.nebojsa_vasovic_rn6518.view.activities.LoginActivity.Companion.LAST_NAME_KEY
import rs.raf.projekat1.nebojsa_vasovic_rn6518.view.activities.LoginActivity.Companion.NAME_KEY

class ZdravstveniRadnikActivity : AppCompatActivity(R.layout.activity_zdravstveni_radnik) {

    private lateinit var name: String
    private lateinit var lastName: String
    private lateinit var hospital: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {
        val sharedPreferences = getSharedPreferences(packageName, Context.MODE_PRIVATE)

        name = sharedPreferences.getString(NAME_KEY, "Ime") ?: "Ime"
        zdravstveni_radnik_et_ime.text = Editable.Factory.getInstance().newEditable(name)

        lastName = sharedPreferences.getString(LAST_NAME_KEY, "Prezime") ?: "Prezime"
        zdravstveni_radnik_et_prezime.text = Editable.Factory.getInstance().newEditable(lastName)

        hospital = sharedPreferences.getString(HOSPITAL_KEY, "Bolnica") ?: "Bolnica"
        zdravstveni_radnik_et_ustanova.text = Editable.Factory.getInstance().newEditable(hospital)

        zdravstveni_radnik_btn_izmeni.setOnClickListener {
            if (checkData()) {
                storeData()
                finish()
            }
        }

        zdravstveni_radnik_btn_odustani.setOnClickListener {
            finish()
        }
    }

    private fun storeData() {
        val sharedPreferences = getSharedPreferences(packageName, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(NAME_KEY, name)
        editor.putString(LAST_NAME_KEY, lastName)
        editor.putString(HOSPITAL_KEY, hospital)
        editor.putBoolean(LoginActivity.LOGIN_KEY, true)
        editor.apply()
    }

    private fun checkData(): Boolean {
        if (!checkIsNull())
            return false

        name = zdravstveni_radnik_et_ime.text.toString().trim()
        if (name.isEmpty()) {
            Toast.makeText(
                this,
                "Polje \"Ime\" ne sme biti prazno",
                Toast.LENGTH_SHORT
            ).show()
            return false
        }

        lastName = zdravstveni_radnik_et_prezime.text.toString().trim()
        if (lastName.isEmpty()) {
            Toast.makeText(
                this,
                "Polje \"Prezime\" ne sme biti prazno",
                Toast.LENGTH_SHORT
            ).show()
            return false
        }

        hospital = zdravstveni_radnik_et_ustanova.text.toString().trim()
        if (hospital.isEmpty()) {
            Toast.makeText(
                this,
                "Polje \"Bolnica\" ne sme biti prazno",
                Toast.LENGTH_SHORT
            ).show()
            return false
        }


        return true
    }

    private fun checkIsNull(): Boolean {
        if (zdravstveni_radnik_et_ime.text == null || zdravstveni_radnik_et_prezime.text == null ||
            zdravstveni_radnik_et_ustanova.text == null)
            return false
        return true
    }
}
