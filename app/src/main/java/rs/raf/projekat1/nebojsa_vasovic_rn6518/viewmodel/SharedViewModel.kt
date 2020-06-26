package rs.raf.projekat1.nebojsa_vasovic_rn6518.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import rs.raf.projekat1.nebojsa_vasovic_rn6518.model.Patient
import rs.raf.projekat1.nebojsa_vasovic_rn6518.view.fragments.OtpusteniFragment
import java.text.DateFormat
import java.util.*

class SharedViewModel : ViewModel() {

    private val patientsCekaonica: MutableLiveData<List<Patient>> = MutableLiveData()

    private val patientHospitalizovani: MutableLiveData<List<Patient>> = MutableLiveData()

    private val patientOtpusteni: MutableLiveData<List<Patient>> = MutableLiveData()

    private val patientCekaonicaList: MutableList<Patient> = mutableListOf()

    private val patientHospitalizovaniList: MutableList<Patient> = mutableListOf()

    private val patientOtpusteniList: MutableList<Patient> = mutableListOf()


    companion object {
        const val pcrUrl =
            "https://st.depositphotos.com/1967477/4276/v/450/depositphotos_42765325-stock-illustration-sick-boy.jpg"
    }

    init {
        for (i in 1..20) {
            val patient = Patient(
                i.toLong(),
                pcrUrl,
                "Petar",
                "Petrović",
                "Pacijent ima povisenu temperaturu i bolove u misicima$i",
                "",
                "21.04.2020",
                ""
            )
            patientCekaonicaList.add(patient)
        }
        val listToSubmit = mutableListOf<Patient>()
        listToSubmit.addAll(patientCekaonicaList)
        patientsCekaonica.value = listToSubmit

    }

    fun getPatientsCekaonica(): LiveData<List<Patient>> {
        return patientsCekaonica
    }

    fun getPatientsHospitalizovani(): LiveData<List<Patient>> {
        return patientHospitalizovani
    }

    fun getPatientsOtpusteni(): LiveData<List<Patient>> {
        return patientOtpusteni
    }

    fun addPatientCekaonica(name: String, lastName: String, conditionRec: String) {

        val currentTime = System.currentTimeMillis()

        val patient = Patient(
            currentTime,
            pcrUrl,
            name,
            lastName,
            conditionRec,
            conditionRec,
            "",
            ""
        )

        patientCekaonicaList.add(patient)
        val listToSubmit = mutableListOf<Patient>()
        listToSubmit.addAll(patientCekaonicaList)
        patientsCekaonica.value = listToSubmit
    }

    fun filterPatientOtpusteni(filter: String) {
        val filteredList = patientOtpusteniList.filter {
            it.name.toLowerCase(Locale.ROOT).startsWith(filter.toLowerCase(Locale.ROOT)) or
                    it.lastName.toLowerCase(Locale.ROOT).startsWith(filter.toLowerCase(Locale.ROOT))
        }
        patientOtpusteni.value = filteredList

    }

    fun izmeniPatientHospitalizovani(
        idPatient: Long,
        name: String?,
        lastName: String?,
        condRec: String?,
        condRel: String?,
        dateRec: String?
    ) {


        val patient = patientHospitalizovaniList.find { it.id == idPatient }
        val position = patientHospitalizovaniList.indexOf(patient)


        patientHospitalizovaniList.removeAt(position)

        patientHospitalizovaniList.add(
            position,
            Patient(
                idPatient,
                pcrUrl,
                name!!,
                lastName!!,
                condRec!!,
                condRel!!,
                dateRec!!,
                ""
            )
        )


        val listToSubmit = mutableListOf<Patient>()
        listToSubmit.addAll(patientHospitalizovaniList)
        patientHospitalizovani.value = listToSubmit
    }

    fun filterPatientHospitalizovani(filter: String) {
        val filteredList = patientHospitalizovaniList.filter {
            it.name.toLowerCase(Locale.ROOT).startsWith(filter.toLowerCase(Locale.ROOT)) or
                    it.lastName.toLowerCase(Locale.ROOT).startsWith(filter.toLowerCase(Locale.ROOT))
        }
        patientHospitalizovani.value = filteredList

    }

    fun addHospitalizovane(patient: Patient) {

        val currentTime = System.currentTimeMillis()
        val dateRec: String = DateFormat.getInstance().format(currentTime)

        patient.dateRec = dateRec

        removePatientCekaonica(patient.id)

        patientHospitalizovaniList.add(patient)
        val listToSubmit = mutableListOf<Patient>()
        listToSubmit.addAll(patientHospitalizovaniList)
        patientHospitalizovani.value = listToSubmit
    }

    private fun removePatientHospitalizovani(id: Long) {
        val listToSubmit = mutableListOf<Patient>()
        var delPatient: Patient? = null
        for (patient: Patient in patientHospitalizovaniList) {
            if (patient.id != id)
                listToSubmit.add(patient)
            else
                delPatient = patient
        }
        patientHospitalizovaniList.remove(delPatient)
        patientHospitalizovani.value = listToSubmit
    }

    fun addOtpustene(patient: Patient) {

        val currentTime = System.currentTimeMillis()
        val dateRel: String = DateFormat.getInstance().format(currentTime)

        patient.dateRel = dateRel

        removePatientHospitalizovani(patient.id)

        patientOtpusteniList.add(patient)
        val listToSubmit = mutableListOf<Patient>()
        listToSubmit.addAll(patientOtpusteniList)
        patientOtpusteni.value = listToSubmit
        OtpusteniFragment.scrollToChanged = true
    }

    fun filterPatientCekaonica(filter: String) {
        val filteredList = patientCekaonicaList.filter {
            it.name.toLowerCase(Locale.ROOT).startsWith(filter.toLowerCase(Locale.ROOT)) or
                    it.lastName.toLowerCase(Locale.ROOT).startsWith(filter.toLowerCase(Locale.ROOT))
        }
        patientsCekaonica.value = filteredList

    }

    fun removePatientCekaonica(id: Long) {
        val listToSubmit = mutableListOf<Patient>()
        var delPatient: Patient? = null
        for (patient: Patient in patientCekaonicaList) {
            if (patient.id != id)
                listToSubmit.add(patient)
            else
                delPatient = patient
        }
        patientCekaonicaList.remove(delPatient)
        patientsCekaonica.value = listToSubmit
    }

    fun countCekaonica(): Int {
        val listToSubmit = mutableListOf<Patient>()
        listToSubmit.addAll(patientCekaonicaList)
        patientsCekaonica.value = listToSubmit
        return listToSubmit.size
    }

//    fun countHospitalizovani(): Int {
//        val listToSubmit = mutableListOf<Patient>()
//        listToSubmit.addAll(patientHospitalizovaniList)
//        patientHospitalizovani.value = listToSubmit
//        return listToSubmit.size
//    }
//
//    fun countOtpusteni(): Int {
//        val listToSubmit = mutableListOf<Patient>()
//        listToSubmit.addAll(patientOtpusteniList)
//        patientOtpusteni.value = listToSubmit
//        return listToSubmit.size
//    }


}