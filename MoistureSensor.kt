class MoistureSensor (
        _currentTime: Int
){
    var currentTime: Int = _currentTime
    var deltaTime: Int = 0
    var currentMoisture: Int = 10
    val precipitationRate = 30
    val dryingRate = 5
    val sprinklingRate = 20

    // update will get precipitating and sprinklersOn flag from controller initially, until features coded
    fun update(newTime: Int, isPrecipitating: Boolean, isSprinkling: Boolean) {
        if(newTime == 0 && currentTime == 23){
            deltaTime = 1
            currentTime = newTime
        }
        else {
            deltaTime = newTime - currentTime
            currentTime = newTime
        }

        if(isSprinkling){
            currentMoisture += sprinklingRate * deltaTime
        }
        else if (isPrecipitating){
            currentMoisture += precipitationRate * deltaTime
        }
        else {
            currentMoisture -= dryingRate * deltaTime
            if(currentMoisture < 0){
                currentMoisture = 0
            }
        }
    }
}
