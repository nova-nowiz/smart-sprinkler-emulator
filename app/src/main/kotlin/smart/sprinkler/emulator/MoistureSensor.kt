package smart.sprinkler.emulator
class MoistureSensor(
    var currentMoisture: Int,
    val precipitationRate: Int,
    val dryingRate: Int,
    val sprinklingRate: Int
) {
    // time is takes to go from 0 to 100 when sprinkling
    // plus the time is takes for to go from 100 to 0 when drying
    val fullCycleDuration = 100.0 / (sprinklingRate - dryingRate) + 100.0 / dryingRate
    fun update(isPrecipitating: Boolean, isSprinkling: Boolean) {
        if (isSprinkling) {
            currentMoisture += sprinklingRate
        }
        if (isPrecipitating) {
            currentMoisture += precipitationRate
        }
        currentMoisture -= dryingRate
        if (currentMoisture < -50) {
            // this will be considered as completely dry
            currentMoisture = -50
        } else if (currentMoisture > 150) {
            // this will be considered completely flooded
           currentMoisture = 150
        }
    }
}
