package smart.sprinkler.emulator
class MoistureSensor() {
    var currentMoisture: Int = 10
    val precipitationRate = 30
    val dryingRate = 5
    val sprinklingRate = 20

    // update will get precipitating and sprinklersOn flag from controller initially, until features coded
    fun update(isPrecipitating: Boolean, isSprinkling: Boolean) {
        if (isSprinkling) {
            currentMoisture += sprinklingRate
        } else if (isPrecipitating) {
            currentMoisture += precipitationRate
        } else {
            currentMoisture -= dryingRate
        }
    }
}
