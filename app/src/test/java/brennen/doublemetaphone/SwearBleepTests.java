package brennen.doublemetaphone;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

/**
 * Created by root on 5/19/16.
 */
//This is harder to test in general. Goal is to have it effective at blocking. We do not need this
//    code to be perfect comparatively.
public class SwearBleepTests {
    @Test
    public void BleepTests(){
        assertTrue(SwearCensor.isBlocked(SwearCensor.censorString("I fucking hate faggot style assrape")));
        assertEquals(SwearCensor.censorString("I fucking hate faggot style assrape"),"I **ing hate ** style **rape");
    }

}
