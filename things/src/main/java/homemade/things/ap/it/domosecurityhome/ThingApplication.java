package homemade.things.ap.it.domosecurityhome;


        import eu.vranckaert.driver.touch.TouchScreenDriverApplication;
        import eu.vranckaert.driver.touch.profile.DriverProfile;
        import eu.vranckaert.driver.touch.profile.WaveshareProfile;

/**
 * Created by dirkvranckaert on 13/10/2017.
 */

public class ThingApplication extends TouchScreenDriverApplication {
    @Override
    public DriverProfile getDriverProfile() {
        return WaveshareProfile.getInstance(WaveshareProfile.DIMENSION_800_480);
    }
}