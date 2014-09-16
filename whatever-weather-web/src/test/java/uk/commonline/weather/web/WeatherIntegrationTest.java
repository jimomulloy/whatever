package uk.commonline.weather.web;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import uk.commonline.weather.man.client.jaxrs.WeatherManClient;
import uk.commonline.weather.model.WeatherReport;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class WeatherIntegrationTest extends TestCase {

    class Runner implements Runnable {
        double lat, lon;

        public Runner(double lat, double lon) {
            this.lat = lat;
            this.lon = lon;
        }

        @Override
        public void run() {
            try {
                System.out.println("!!Run Weather report lat:" + lat + ", lon:" + lon);
                WeatherReport weathers = WeatherIntegrationTest.this.weatherManClient.updateWeather(lat, lon);
                System.out.println("!!Received Weather report lat:" + lat + ", lon:" + lon + " size:" + weathers.getSourceMap().size());
                // assertEquals("Invalid region", 1, region);
            } catch (Exception ex) {
                ex.printStackTrace();

            }
            lock.countDown();
        }
    }

    @Inject
    private WeatherManClient weatherManClient;

    /** Countdown latch */
    private CountDownLatch lock = new CountDownLatch(2);

    @Test
    public void dummy() throws Exception {

    }

    // @Test
    public void test() throws Exception {
        Thread t = new Thread(new Runner(50, 0));
        t.start();
        // Thread t2 = new Thread(new Runner(30, 10));
        // t2.start();

        lock.await(30000, TimeUnit.MILLISECONDS);

        // assertEquals("Invalid region", 1, region);
    }
}