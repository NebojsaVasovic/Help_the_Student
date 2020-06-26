package rs.raf.projekat1.nebojsa_vasovic_rn6518.view.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import kotlinx.android.synthetic.main.fragment_unos.*
import rs.raf.projekat1.nebojsa_vasovic_rn6518.R
import rs.raf.projekat1.nebojsa_vasovic_rn6518.view.fragments.CekaonicaFragment.Companion.scrollToNew
import rs.raf.projekat1.nebojsa_vasovic_rn6518.viewmodel.SharedViewModel

class UnosFragment : Fragment(R.layout.fragment_unos){

    private lateinit var ime: String
    private lateinit var prezime: String
    private lateinit var stanje: String

    private val sharedViewModel: SharedViewModel by activityViewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        unos_btn_cekaonica.setOnClickListener {
            if(checkData()){
                addData()
                unos_et_ime.text.clear()
                unos_et_prezime.text.clear()
                unos_et_simptomi.text.clear()
                Toast.makeText(
                    context,
                    "Unet je novi pacijent: $ime $prezime $stanje" ,
                    Toast.LENGTH_SHORT
                ).show()
                scrollToNew = true
            }
        }
    }


    private fun checkData(): Boolean {

        if (!checkIsNull())
            return false

        ime = unos_et_ime.text.toString().trim()
        if (ime.isEmpty()) {
            Toast.makeText(
                context,
                "Polje \"Unesite ime\" ne sme biti prazno",
                Toast.LENGTH_SHORT
            ).show()
            return false
        }

        prezime = unos_et_prezime.text.toString().trim()
        if (prezime.isEmpty()) {
            Toast.makeText(
                context,
                "Polje \"Unesite prezime\" ne sme biti prazno",
                Toast.LENGTH_SHORT
            ).show()
            return false
        }

        stanje = unos_et_simptomi.text.toString().trim()
        if (stanje.isEmpty()) {
            Toast.makeText(
                context,
                "Polje \"Unesite simptome…\" ne sme biti prazno",
                Toast.LENGTH_SHORT
            ).show()
            return false
        }

        return true
    }

    private fun checkIsNull(): Boolean {
        if (unos_et_ime.text == null || unos_et_prezime == null ||
            unos_et_simptomi == null )
            return false
        return true
    }

    private fun addData() {
        sharedViewModel.addPatientCekaonica(
            ime,
            prezime,
            stanje
        )
    }
}