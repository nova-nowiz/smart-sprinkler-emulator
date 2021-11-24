package smart.sprinkler.emulator
class Controller(
    val moistureSensor: MoistureSensor,
    val forecastSensor: ForecastSensor,
    val time: Time
) {
    var lastMoisture: Int = moistureSensor.currentMoisture
    var isSprinkling: Boolean = false

    fun update(currentDay: Int, currentTime: Int) {
        // poll moisture sensor
        val currentMoisture = moistureSensor.currentMoisture
        val fullCycleDuration = moistureSensor.fullCycleDuration
        val afterCycleDay: Int = (currentDay + Math.floor((currentTime + fullCycleDuration) / 24).toInt()) % 7
        val afterCycleTime: Double = (currentTime + fullCycleDuration) % 24

        // poll forecast sensor
        val isPrecipitating = forecastSensor
            .isPrecipitating(currentDay, currentTime)
        val willPrecipitate = forecastSensor
            .willPrecipitate(
                currentDay,
                currentTime,
                afterCycleDay,
                afterCycleTime
            )

        isSprinkling = needToSprinkle(
            currentMoisture,
            isPrecipitating,
            willPrecipitate
        )
        logging(
            currentDay,
            currentTime,
            fullCycleDuration,
            afterCycleDay,
            afterCycleTime,
            currentMoisture,
            isPrecipitating,
            willPrecipitate
        )
        lastMoisture = currentMoisture
    }
    fun needToSprinkle(
        currentMoisture: Int,
        isPrecipitating: Boolean,
        willPrecipitate: Boolean
    ): Boolean {
        if (isSprinkling) {
            if (currentMoisture < 100) {
                // if it is in the process of sprinkling
                // and bringing the moisture
                // to the higher bound,
                // let it sprinkle
                return true
            } else {
                // when the moisture rose to the higher bound
                // stop the sprinklers
                //
                // this case is not necessary as there is a
                // following currentMoisture >= 5 case that would
                // take care of turning the sprinklers off
                // However, as it is a different requirement,
                // leaving this case for clarity seems appropiate
                return false
            }
        } else if (isPrecipitating or willPrecipitate) {
            // if it is precipitating
            // or if there is going to be a precipitation
            // in the time it takes for the moisture
            // to rise up to the upper limit
            // and for the ground to dry up
            // to the lower bound
            // then don't sprinkle
            return false
        } else if (currentMoisture > 5) {
            // if we are not sprinkling and the
            // currentMoisture is above 5 percent
            // then don't sprinkle
            return false
        } else if (currentMoisture < 0) {
            // if the currentMoisture is under 0 percent,
            // sprinkle immediatly
            return true
        } else {
            // if the current moisture is between 0 and 5
            // and it is dropping, then sprinkle, else don't
            if (currentMoisture < lastMoisture) {
                return true
            } else {
                return false
            }
        }
    }
    fun logging(
        currentDay: Int,
        currentTime: Int,
        fullCycleDuration: Double,
        afterCycleDay: Int,
        afterCycleTime: Double,
        currentMoisture: Int,
        isPrecipitating: Boolean,
        willPrecipitate: Boolean
    ) {
        if (currentTime == 0) {
            println("--- Logging for Day " + currentDay + " ---")
            println(""
                + "currentDay"
                + "\t"
                + "currentTime"
                + "\t"
                + "fullCycleDuration"
                + "\t"
                + "afterCycleDay"
                + "\t"
                + "afterCycleTime"
                + "\t"
                + "\t"
                + "lastMoisture"
                + "\t"
                + "currentMoisture"
                + "\t"
                + "isPrecipitating"
                + "\t"
                + "willPrecipitate"
                + "\t"
                + "isPrinkling"
            )
        }
        print(""
            + currentDay
            + "\t"
            + "\t"
            + currentTime
            + "\t"
            + "\t"
            + fullCycleDuration
            + "\t"
            + afterCycleDay
            + "\t"
            + "\t"
            + afterCycleTime
            + "\t"
            + lastMoisture
            + "\t"
            + "\t"
            + currentMoisture
            + "\t"
            + "\t"
            + isPrecipitating
            + "\t"
            + "\t"
            + willPrecipitate
            + "\t"
            + "\t"
            + isSprinkling
        )
        if (time == Time.RealTime) {
            println()
        }
    }
}
