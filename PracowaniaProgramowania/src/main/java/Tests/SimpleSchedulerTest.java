package Tests;

import org.apache.log4j.Logger;
import org.junit.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.After;
import quartz.scheduler.SimpleScheduler;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.sym.error;
import static org.junit.Assert.*;

public class SimpleSchedulerTest {

    SimpleScheduler przyklad;
    final static Logger logger = Logger.getLogger(SimpleScheduler.class);

    @Before
    public void setUp(){
        przyklad = new SimpleScheduler();
    }

    @Test
    public void SumaKontrolnaTest() {
        String a = "11111111111";
        boolean b = przyklad.SumaKontrolna(a);
        assertFalse(b);
    }

    @Test
    public void LiczbaZnakowTest() {
        String a = "12345";
        boolean b = przyklad.SumaKontrolna(a);
        assertFalse(b);
    }
    @Test
    public void PoprawnaSumaKontrolnaTest() {
    String a = "97102906482";
    boolean b = przyklad.SumaKontrolna(a);
    assertTrue(b);
    }


}