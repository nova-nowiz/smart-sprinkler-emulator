class MoistureSensor (
        _currentTime: Int
){
    var currentTime: Int = _currentTime
    val deltaTime: Int = 1
    var currentMoisture: Int = 10
    val precipitationRate = 30
    val dryingRate = 5
    val sprinklingRate = 20

    // update will get precipitating and sprinklersOn flag from controller initially, until features coded
    fun update(newTime: Int, isPrecipitating: Boolean, isSprinkling: Boolean) {
        currentTime = newTime

        if(isSprinkling){
            currentMoisture += sprinklingRate * deltaTime
        }
        else if (isPrecipitating){
            currentMoisture += precipitationRate * deltaTime
        }
        else {
            currentMoisture -= dryingRate * deltaTime
        }
    }
}
