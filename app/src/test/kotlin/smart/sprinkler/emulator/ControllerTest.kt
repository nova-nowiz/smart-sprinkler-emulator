package smart.sprinkler.emulator

import kotlin.test.Test

class ControllerTest {
    val moistureSensor = MoistureSensor(
        currentMoisture = 10,
        precipitationRate = 30,
        dryingRate = 3,
        sprinklingRate = 20
    )
    val forecastSensor = ForecastSensor()
    @Test fun `moisture between 0 and 5 and decreasing`() {
        //Given
        val controller = Controller(moistureSensor, forecastSensor, Time.RealTime)
        controller.isSprinkling = false
        controller.lastMoisture = 5
        assert(!controller.isSprinkling)
        //When
        val result = controller.needToSprinkle(3, false, false)
        //Then
        assert(result)
    }
    @Test fun `moisture below 0`() {
        //Given
        val controller = Controller(moistureSensor, forecastSensor, Time.RealTime)
        controller.isSprinkling = false
        controller.lastMoisture = 5
        //When
        val result = controller.needToSprinkle(-1, false, false)
        //Then
        assert(result)
    }
    @Test fun `moisture above 5`() {
        //Given
        val controller = Controller(moistureSensor, forecastSensor, Time.RealTime)
        controller.isSprinkling = false
        controller.lastMoisture = 5
        //When
        val result = controller.needToSprinkle(8, false, false)
        //Then
        assert(!result)
    }
    @Test fun `is precipitating`() {
        //Given
        val controller = Controller(moistureSensor, forecastSensor, Time.RealTime)
        controller.isSprinkling = false
        controller.lastMoisture = 5
        //When
        val result = controller.needToSprinkle(3, true, false)
        //Then
        assert(!result)
    }
    @Test fun `will precipitate`() {
        //Given
        val controller = Controller(moistureSensor, forecastSensor, Time.RealTime)
        controller.isSprinkling = false
        controller.lastMoisture = 5
        //When
        val result = controller.needToSprinkle(3, false, true)
        //Then
        assert(!result)
    }
    @Test fun `moisture between 0 and 5 and increasing`() {
        //Given
        val controller = Controller(moistureSensor, forecastSensor, Time.RealTime)
        controller.isSprinkling = false
        controller.lastMoisture = 2
        //When
        val result = controller.needToSprinkle(3, false, false)
        //Then
        assert(!result)
    }
    @Test fun `moisture below 100 and sprinkling`() {
        //Given
        val controller = Controller(moistureSensor, forecastSensor, Time.RealTime)
        controller.isSprinkling = true
        controller.lastMoisture = 2
        //When
        val result = controller.needToSprinkle(10, false, false)
        //Then
        assert(result)
    }
    @Test fun `moisture above or equal to 100 and sprinkling`() {
        //Given
        val controller = Controller(moistureSensor, forecastSensor, Time.RealTime)
        controller.isSprinkling = true
        controller.lastMoisture = 2
        //When
        val result = controller.needToSprinkle(100, false, false)
        //Then
        assert(!result)
    }
}
