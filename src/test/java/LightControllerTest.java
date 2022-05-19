import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


/**
 * Version 1:
 * motion detected -> turn on light for 5min
 * after light turned on for 5 min -> light turns off
 * no motion -> nothing happens
 * <p>
 * Version 2:
 * WinterMonths / SummerMonths
 * WinterMonths have letter "r" in the name
 * WinterMonths have daylight from 8am to 6pm
 * SummerMonths have daylight from 6am to 8pm
 *
 * whichTimeIsIt(time) => Day/Night
 *
 * winterOrSummer(time) => Winter/Summer
 *
 * Given I have a daytime calculator
 * And its January 1st 8am
 *
 * When I ask it what time it is
 *
 * Then it will yield DayTime
 */
public class LightControllerTest {

    @Test
    void turns_on_light_when_motion_detected() {
        Light light = new Light(LightStatus.OFF);
        LightController controller = new LightController(light);

        controller.onMotion();

        assertThat(light.getStatus()).isEqualTo(LightStatus.ON);
    }

    @Test
    void turns_off_light_after_5min() {
        Light light = new Light(LightStatus.ON);
        LightController controller = new LightController(light);

        passFiveMinutes(controller);

        assertThat(light.getStatus()).isEqualTo(LightStatus.OFF);
    }

    @Test
    void light_stays_off_after_5min() {
        Light light = new Light(LightStatus.OFF);
        LightController controller = new LightController(light);

        passFiveMinutes(controller);

        assertThat(light.getStatus()).isEqualTo(LightStatus.OFF);
    }

    @Test
    void turns_off_light_after_another_5min() {
        Light light = new Light(LightStatus.ON);
        LightController controller = new LightController(light);
        lightGotTurnedOffAfter5Min(light, controller);
        light.turnOn();

        passFiveMinutes(controller);

        assertThat(light.getStatus()).isEqualTo(LightStatus.OFF);
    }

    private void passFiveMinutes(LightController controller) {
        controller.tick();
        controller.tick();
        controller.tick();
        controller.tick();
        controller.tick();
    }

    private void lightGotTurnedOffAfter5Min(Light light, LightController controller) {
        passFiveMinutes(controller);

        assertThat(light.getStatus()).isEqualTo(LightStatus.OFF);
    }
}