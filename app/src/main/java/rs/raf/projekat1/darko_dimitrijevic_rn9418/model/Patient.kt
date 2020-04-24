package rs.raf.projekat1.darko_dimitrijevic_rn9418.model

data class Patient(
    var id: Int,
    var name: String,
    var lastName: String,
    var pictureUrl: String,
    var symptoms: String
) {

    var hospitalizeDate: String = ""
    var hospitalizeFreeDate: String = ""
    var currentSymptoms: String = ""

}