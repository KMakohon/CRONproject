package Tests;

import org.apache.log4j.Logger;
import org.junit.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.After;
import quartz.job.SimpleJob;

import java.time.LocalTime;

import static org.junit.Assert.*;

public class SimpleJobTest {

    SimpleJob example;
    final static Logger logger = Logger.getLogger(SimpleJob.class);

    @Before
    public void setUp(){
        System.out.println("Odpalam setUpa");
        example = new SimpleJob();
    }

    @Test
    public void SprawdzCoJestZaj() {
        LocalTime a;
        a = LocalTime.of(10,45);
        boolean b = example.SprawdzCoJest(a);
        assertTrue(b);
    }

    @Test
    public void SprawdzCoJestPrzerwa() {
        LocalTime a;
        a = LocalTime.of(10,45);
        boolean b = example.SprawdzCoJest(a);
        assertFalse(!b);
    }
    @Test
    public void WypiszCoJestWolny() {
        LocalTime a;
        a = LocalTime.of(23,59);
        boolean b = example.SprawdzCoJest(a);
        assertTrue(!b);
    }
}