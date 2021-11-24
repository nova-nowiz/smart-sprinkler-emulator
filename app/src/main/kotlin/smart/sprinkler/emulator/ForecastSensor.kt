package smart.sprinkler.emulator
class ForecastSensor() {
    val precipitations = arrayOf(
        arrayOf(Pair(2,3)),
        arrayOf(),
        arrayOf(),
        arrayOf(Pair(5,7)),
        arrayOf(),
        arrayOf(),
        arrayOf(Pair(10,13),Pair(15,18))
    )

    fun isPrecipitating(currentDay: Int, currentTime: Int): Boolean {
        val currentDayPrecipitations = precipitations[currentDay]
        for (precipitation in currentDayPrecipitations) {
            if ((currentTime >= precipitation.first) and (currentTime <= precipitation.second)) {
                return true
            }
        }
        return false
    }
    fun willPrecipitate(
        currentDay: Int,
        currentTime: Int,
        afterCycleDay: Int,
        afterCycleTime: Double
    ): Boolean {
        // if there is a precipitation before the afterCycleDay,
        // then it will precipitate

        // here we are going to check for the current day if
        // there is going to be precipitation
        //
        // if there is going to be a precipitation today
        // after the currentTime, then it will precipitate
        for (precipitation in precipitations[currentDay]) {
            if (currentTime <= precipitation.first) {
                return true
            }
        }
        // here we are going to check if on the days
        // between the current day and afterCycleDay
        // there will be precipitation
        //
        // if afterCycleDay and currentDay are the same
        // or if they are adjacent,
        // there are no days in between the two
        // we also check if afterCycleDay looped around or not
        // we consider here that the afterCycleDay can't be 1 week or more in the future
        if ((afterCycleDay == currentDay) or (afterCycleDay == ((currentDay+1) % precipitations.size))) {
        } else if (afterCycleDay > currentDay) {
            // if it didn't loop around, just check the comming days
            for (dayPrecipitations in precipitations.slice((currentDay+1)..(afterCycleDay-1))) {
                if (dayPrecipitations.isNotEmpty()) {
                    return true
                }
            }
        } else {
            // if it looped, we need to check to the end of the precipitations,
            // and from the beginning of precipitations to the day before afterCycleDay
            //
            // the first check should only be run if currentDay+1 would not in fact
            // loop back around to the first element of precipitations
            // if it does so, then this currentDay+1 will be checked as part
            // of the second check
            if (((currentDay + 1) % precipitations.size) != 0) {
                for (dayPrecipitations in precipitations.slice((currentDay+1)..(precipitations.size-1))) {
                    if (dayPrecipitations.isNotEmpty()) {
                        return true
                    }
                }
            }
            // if afterCycleDay is 0, only the first part needs to be done
            // as the day before afterCycleDay loops back to the last element of precipitations
            // so the first check will take care of checking this day
            // if afterCycleDay is greater than 0, then we need to do the second step
            if (afterCycleDay > 0) {
                for (dayPrecipitations in precipitations.slice(0..(afterCycleDay-1))) {
                    if (dayPrecipitations.isNotEmpty()) {
                        return true
                    }
                }
            }
        }
        // here we are going to check if on the afterCycleDay
        // there will be precipitation before afterCycleTime
        //
        // if there is a precipitation on the afterCycleDay
        // before the afterCycleTime,
        // then it will precipitate
        for (precipitation in precipitations[afterCycleDay]) {
            if (afterCycleTime >= precipitation.first) {
                return true
            }
        }
        return false
    }
}
