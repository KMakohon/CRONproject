package quartz.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.io.IOException;
import java.io.PrintWriter;


public class ZapisJob implements org.quartz.Job {
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        try {
            PrintWriter printfile = new PrintWriter("odp.txt");
            for (int z = 0; z < Osoba.Ludzie.size(); z++) {
                printfile.println(Osoba.Ludzie.get(z).wypis());

            }
            printfile.close();

        } catch (IOException x) {
            x.printStackTrace();
        }
    }
}
