package quartz.scheduler;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import quartz.job.SimpleJob;
import quartz.job.Osoba;
import quartz.job.ZapisJob;
import java.util.*;
import java.util.Collections;
import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import java.io.File;
import java.io.PrintWriter;

public class SimpleScheduler {
    public static boolean SumaKontrolna(String psl) {
        if (psl.length() == 11) {
            int wynik = 9 * Character.getNumericValue(psl.charAt(0)) + 7 * Character.getNumericValue(psl.charAt(1)) + 3 * Character.getNumericValue(psl.charAt(2)) + Character.getNumericValue(psl.charAt(3)) + 9 * Character.getNumericValue(psl.charAt(4)) + 7 * Character.getNumericValue(psl.charAt(5)) + 3 * Character.getNumericValue(psl.charAt(6)) + Character.getNumericValue(psl.charAt(7)) + 9 * Character.getNumericValue(psl.charAt(8)) + 7 * Character.getNumericValue(psl.charAt(9));
            if (Character.getNumericValue(psl.charAt(10)) == wynik % 10) {
                return true;
            } else {
                System.err.println("niepoprawna suma kontrolna");
                return false;
            }}
        else {
            System.err.println("niepoprawny pesel");
        }
        return false;}

    public static void main(String[] args) throws NullPointerException {

        try {
            // Grab the Scheduler instance from the Factory
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            Scheduler scheduler2 = StdSchedulerFactory.getDefaultScheduler();



            // define the job and tie it to our SimpleJob class
            JobDetail job = newJob(ZapisJob.class)
                    .withIdentity("job1", "group1")
                    .build();

            // Trigger the job to run now, and then repeat every 1 seconds
            Trigger trigger = newTrigger()
                    .withIdentity("trigger1", "group1")
                    .startNow()
                    .withSchedule(cronSchedule("0/30 * * ? * * *"))
                    .build();

            JobDetail job2 = newJob(SimpleJob.class)
                    .withIdentity("job2", "group2")
                    .build();

            // Trigger the job to run now, and then repeat every 1 seconds
            Trigger trigger2 = newTrigger()
                    .withIdentity("trigger2", "group2")
                    .startNow()
                    .withSchedule(cronSchedule("0 * * ? * * *"))
                    .build();

            scheduler.scheduleJob(job, trigger);
            scheduler2.scheduleJob(job2, trigger2);

            scheduler.start();
            Osoba Pierwszy = new Osoba();
            Pierwszy.Ludzie = new ArrayList<>();
            Pierwszy.Ludzie.clear();
            String miasto;
            while (true) {
                Scanner odczyt = new Scanner(System.in);
                miasto = odczyt.nextLine();
                String[] dane = odczyt.nextLine().split(" ");
                if (dane.length != 3) {
                    System.err.println("Zacznij wpisywać jeszcze raz od miasta");
                } else {
                    if (SumaKontrolna(dane[2]) == false) {
                    } else {
                        Osoba osoba = new Osoba(miasto, dane[0], dane[1], dane[2]);
                        if (Pierwszy.Ludzie != null) {
                            for (int j = 0; j < Pierwszy.Ludzie.size(); j++) {
                                if (osoba.dajPesel().equals(Pierwszy.Ludzie.get(j).dajPesel())) {
                                    Pierwszy.Ludzie.remove(j);
                                }
                            }
                        }
                        Pierwszy.Ludzie.add(osoba);
                        Collections.sort(Pierwszy.Ludzie);
                    }
                }
            }
    } catch (SchedulerException se) {
        se.printStackTrace();
    }
    }}