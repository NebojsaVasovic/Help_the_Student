package rs.raf.projekat1.nebojsa_vasovic_rn6518.model

data class Patient(
    val id: Long,
    val picture: String,
    var name: String,
    var lastName: String,
    var conditionRec: String,
    var conditionRel: String,
    var dateRec: String,
    var dateRel: String
)