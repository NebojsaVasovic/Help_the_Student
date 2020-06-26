package rs.raf.projekat1.nebojsa_vasovic_rn6518.view.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_profil.*
import rs.raf.projekat1.nebojsa_vasovic_rn6518.R
import rs.raf.projekat1.nebojsa_vasovic_rn6518.view.activities.LoginActivity
import rs.raf.projekat1.nebojsa_vasovic_rn6518.view.activities.ZdravstveniRadnikActivity

class ProfilFragment : Fragment(R.layout.fragment_profil) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setData()

        profil_btn_odjava.setOnClickListener {

            val sharedPreferences =
                context?.getSharedPreferences(context?.packageName, Context.MODE_PRIVATE)
            if (sharedPreferences != null) {
                val editor = sharedPreferences.edit()
                editor?.putBoolean(LoginActivity.LOGIN_KEY, false)
                editor?.apply()
            }
            val intent = Intent(context, LoginActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }

        profil_btn_izmeni.setOnClickListener {
            val intent = Intent(context, ZdravstveniRadnikActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        setData()
    }

    private fun setData() {
        val sharedPreferences =
            context?.getSharedPreferences(context?.packageName, Context.MODE_PRIVATE)

        if (sharedPreferences != null) {
            profil_tv_ime_value.text = sharedPreferences.getString(LoginActivity.NAME_KEY, "Ime")
            profil_tv_prezime_value.text =
                sharedPreferences.getString(LoginActivity.LAST_NAME_KEY, "Prezime")
            profil_tv_bolnica_value.text =
                sharedPreferences.getString(LoginActivity.HOSPITAL_KEY, "Bolnica")
        }
    }
}