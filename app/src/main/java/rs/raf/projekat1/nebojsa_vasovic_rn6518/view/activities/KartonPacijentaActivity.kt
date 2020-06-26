package rs.raf.projekat1.nebojsa_vasovic_rn6518.view.activities


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_karton_pacijenta.*
import rs.raf.projekat1.nebojsa_vasovic_rn6518.R
import rs.raf.projekat1.nebojsa_vasovic_rn6518.view.fragments.HospitalizovaniFragment.Companion.CON_REC
import rs.raf.projekat1.nebojsa_vasovic_rn6518.view.fragments.HospitalizovaniFragment.Companion.CON_REL
import rs.raf.projekat1.nebojsa_vasovic_rn6518.view.fragments.HospitalizovaniFragment.Companion.DATE_REC
import rs.raf.projekat1.nebojsa_vasovic_rn6518.view.fragments.HospitalizovaniFragment.Companion.LAST_NAME_KEY
import rs.raf.projekat1.nebojsa_vasovic_rn6518.view.fragments.HospitalizovaniFragment.Companion.NAME_KEY

class KartonPacijentaActivity : AppCompatActivity(R.layout.activity_karton_pacijenta) {

    private lateinit var name: String
    private lateinit var lastName: String
    private lateinit var condRec: String
    private lateinit var condRel: String
    private lateinit var nameCrn: String
    private lateinit var lastNameCrn: String
    private lateinit var condRecCrn: String
    private lateinit var condRelCrn: String
    private lateinit var dateRec: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()



        karton_pacijenta_btn_izmeni.setOnClickListener {
            if (checkData()) {
                if (!changed()) {
                    val returnIntent = Intent()
                    returnIntent.putExtra(NAME_KEY, name)
                    returnIntent.putExtra(LAST_NAME_KEY, lastName)
                    returnIntent.putExtra(CON_REC, condRec)
                    returnIntent.putExtra(CON_REL, condRel)
                    returnIntent.putExtra(DATE_REC, dateRec)
                    setResult(Activity.RESULT_OK, returnIntent)
                    finish()
                } else {
                    finish()
                }
            }
        }

        karton_pacijenta_btn_odustani.setOnClickListener {
            finish()
        }
    }

    private fun changed(): Boolean {
        return name == nameCrn && lastName == lastNameCrn && condRec == condRecCrn && condRel == condRelCrn
    }


    private fun pickCrnData() {
        nameCrn = karton_pacijenta_et_ime.text.toString().trim()
        lastNameCrn = karton_pacijenta_et_prezime.text.toString().trim()
        condRecCrn = karton_pacijenta_et_stanje.text.toString().trim()
        condRelCrn = karton_pacijenta_et_trenutno_stanje.text.toString().trim()
        dateRec = karton_pacijenta_tv_datum.text.toString().trim()
    }

    private fun init() {
        getAndSetData()
        pickCrnData()
    }

    private fun getAndSetData() {
        val intent = intent
        if (intent != null) {
            karton_pacijenta_et_ime.text =
                Editable.Factory.getInstance().newEditable(intent.getStringExtra(NAME_KEY))
            karton_pacijenta_et_prezime.text =
                Editable.Factory.getInstance().newEditable(intent.getStringExtra(LAST_NAME_KEY))
            karton_pacijenta_et_stanje.text =
                Editable.Factory.getInstance().newEditable(intent.getStringExtra(CON_REC))
            if ((Editable.Factory.getInstance()
                    .newEditable(intent.getStringExtra(CON_REL))).isEmpty()
            ) {
                karton_pacijenta_et_trenutno_stanje.text =
                    Editable.Factory.getInstance().newEditable(
                        intent.getStringExtra(CON_REC)
                    )
            } else {
                karton_pacijenta_et_trenutno_stanje.text =
                    Editable.Factory.getInstance().newEditable(intent.getStringExtra(CON_REL))
            }
            karton_pacijenta_tv_datum.text =
                Editable.Factory.getInstance().newEditable(intent.getStringExtra(DATE_REC))
        }
    }

    private fun checkData(): Boolean {
        if (!checkIsNull())
            return false

        name = karton_pacijenta_et_ime.text.toString().trim()
        if (name.isEmpty()) {
            Toast.makeText(
                this,
                "Polje \"Ime\" ne sme biti prazno",
                Toast.LENGTH_SHORT
            ).show()
            return false
        }

        lastName = karton_pacijenta_et_prezime.text.toString().trim()
        if (lastName.isEmpty()) {
            Toast.makeText(
                this,
                "Polje \"Prezime\" ne sme biti prazno",
                Toast.LENGTH_SHORT
            ).show()
            return false
        }

        condRec = karton_pacijenta_et_stanje.text.toString().trim()
        if (condRec.isEmpty()) {
            Toast.makeText(
                this,
                "Polje \"Stanje pri prijemu\" ne sme biti prazno",
                Toast.LENGTH_SHORT
            ).show()
            return false
        }

        condRel = karton_pacijenta_et_trenutno_stanje.text.toString().trim()
        if (condRel.isEmpty()) {
            Toast.makeText(
                this,
                "Polje \"Trenutno stanje\" ne sme biti prazno",
                Toast.LENGTH_SHORT
            ).show()
            return false
        }


        return true
    }

    private fun checkIsNull(): Boolean {
        if (karton_pacijenta_et_ime.text == null || karton_pacijenta_et_prezime.text == null ||
            karton_pacijenta_et_stanje.text == null || karton_pacijenta_et_trenutno_stanje.text == null
        )
            return false
        return true
    }
}