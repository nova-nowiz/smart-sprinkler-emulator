package smart.sprinkler.emulator

import kotlin.test.Test
import kotlin.test.assertEquals

class MoistureSensorTest {
    @Test fun `is precipitating`() {
        //Given
        val moistureSensor = MoistureSensor(
            currentMoisture = 10,
            precipitationRate = 30,
            dryingRate = 3,
            sprinklingRate = 20
        )
        //When
        moistureSensor.update(true, false)
        //Then
        assertEquals(moistureSensor.currentMoisture, 37)
    }
    @Test fun `is sprinkling`() {
        //Given
        val moistureSensor = MoistureSensor(
            currentMoisture = 10,
            precipitationRate = 30,
            dryingRate = 3,
            sprinklingRate = 20
        )
        //When
        moistureSensor.update(false, true)
        //Then
        assertEquals(moistureSensor.currentMoisture, 27)
    }
    @Test fun `current moisture goes below -50`() {
        //Given
        val moistureSensor = MoistureSensor(
            currentMoisture = -49,
            precipitationRate = 30,
            dryingRate = 3,
            sprinklingRate = 20
        )
        //When
        moistureSensor.update(false, false)
        //Then
        assertEquals(moistureSensor.currentMoisture, -50)
    }
    @Test fun `current moisture goes above 150`() {
        //Given
        val moistureSensor = MoistureSensor(
            currentMoisture = 140,
            precipitationRate = 30,
            dryingRate = 3,
            sprinklingRate = 20
        )
        //When
        moistureSensor.update(true, false)
        //Then
        assertEquals(moistureSensor.currentMoisture, 150)
    }
    @Test fun `is drying only`() {
        //Given
        val moistureSensor = MoistureSensor(
            currentMoisture = 10,
            precipitationRate = 30,
            dryingRate = 3,
            sprinklingRate = 20
        )
        //When
        moistureSensor.update(false, false)
        //Then
        assertEquals(moistureSensor.currentMoisture, 7)
    }
}
