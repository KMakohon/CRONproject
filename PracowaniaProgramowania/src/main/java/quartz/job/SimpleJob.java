package quartz.job;

import org.quartz.JobExecutionContext;

import java.time.temporal.ChronoUnit;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;


public class SimpleJob implements org.quartz.Job {

    public static void WypiszCoJest(boolean status, long minut) {
        minut++;
        if (!status) {
            System.out.println(minut + " minut do konca przerwy.");
        } else {
            System.out.println( minut + " minut do konca zajec.");
        }
    }

    //false przerwa, true zajecia
    final public boolean SprawdzCoJest(LocalTime a) {
        if (LocalTime.now().getSecond() == 0) {
            LocalTime teraz = a;
            boolean status;
            long pomiedzy;
            Date dzis = new Date();
            Calendar c = Calendar.getInstance();
            c.setTime(dzis);
            int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);

            LocalTime zaj[] = new LocalTime[12];
            zaj[0] = LocalTime.of(8, 15);
            zaj[1] = LocalTime.of(9, 45);
            zaj[2] = LocalTime.of(10, 0);
            zaj[3] = LocalTime.of(11, 30);
            zaj[4] = LocalTime.of(11, 45);
            zaj[5] = LocalTime.of(13, 15);
            zaj[6] = LocalTime.of(13, 45);
            zaj[7] = LocalTime.of(15, 15);
            zaj[8] = LocalTime.of(15, 30);
            zaj[9] = LocalTime.of(17, 0);
            zaj[10] = LocalTime.of(17, 15);
            zaj[11] = LocalTime.of(18, 45);
            LocalTime midnight = LocalTime.MAX;

            switch (dayOfWeek) {
                //niedziela
                case (1): {
                    status = false;
                    pomiedzy = ChronoUnit.MINUTES.between(teraz, midnight) + 60 * 8 + 15;
                    WypiszCoJest(status, pomiedzy);
                    return false;
                }
                //sobota
                case (7): {
                    status = false;
                    pomiedzy = ChronoUnit.MINUTES.between(teraz, midnight) + 60 * 32 + 15;
                    WypiszCoJest(status, pomiedzy);
                    return false;
                }
                case (6): {
                    if (zaj[0].isAfter(teraz)) {
                        //przerwy
                        status = false;
                        pomiedzy = ChronoUnit.MINUTES.between(teraz, zaj[0]);
                        WypiszCoJest(status, pomiedzy);
                        return false;
                    } else if (zaj[11].isBefore(teraz)) {
                        status = false;
                        pomiedzy = ChronoUnit.MINUTES.between(teraz, midnight) + 60 * 56 + 15;
                        WypiszCoJest(status, pomiedzy);
                        return false;

                    }
                    break;
                }
                default: {
                    if (zaj[0].isAfter(teraz)) {
                        //przerwy
                        status = false;
                        pomiedzy = ChronoUnit.MINUTES.between(teraz, zaj[0]);
                        WypiszCoJest(status, pomiedzy);
                        return false;
                    } else if (zaj[11].isBefore(teraz)) {
                        status = false;
                        pomiedzy = ChronoUnit.MINUTES.between(teraz, midnight) + 60 * 8 + 15;
                        WypiszCoJest(status, pomiedzy);
                        return false;

                    } else if (zaj[1].isBefore(teraz) && zaj[2].isAfter(teraz)) {
                        status = false;
                        pomiedzy = ChronoUnit.MINUTES.between(teraz, zaj[2]);
                        WypiszCoJest(status, pomiedzy);
                        return false;
                    } else if ((zaj[3].isBefore(teraz) && zaj[4].isAfter(teraz))) {
                        status = false;
                        pomiedzy = ChronoUnit.MINUTES.between(teraz, zaj[4]);
                        WypiszCoJest(status, pomiedzy);
                        return false;
                    } else if ((zaj[5]).isBefore(teraz) && zaj[6].isAfter(teraz)) {
                        status = false;
                        pomiedzy = ChronoUnit.MINUTES.between(teraz, zaj[6]);
                        WypiszCoJest(status, pomiedzy);
                        return false;
                    } else if ((zaj[7]).isBefore(teraz) && zaj[8].isAfter(teraz)) {
                        status = false;
                        pomiedzy = ChronoUnit.MINUTES.between(teraz, zaj[8]);
                        WypiszCoJest(status, pomiedzy);
                        return false;
                    } else if ((zaj[9]).isBefore(teraz) && zaj[10].isAfter(teraz)) {
                        status = false;
                        pomiedzy = ChronoUnit.MINUTES.between(teraz, zaj[10]);
                        WypiszCoJest(status, pomiedzy);
                        return false;

                        //zajęcia
                    } else if (zaj[0].isBefore(teraz) && zaj[1].isAfter(teraz)) {
                        status = true;
                        pomiedzy = ChronoUnit.MINUTES.between(teraz, zaj[1]);
                        WypiszCoJest(status, pomiedzy);
                        return true;
                    } else if ((zaj[2].isBefore(teraz) && zaj[3].isAfter(teraz))) {
                        status = true;
                        pomiedzy = ChronoUnit.MINUTES.between(teraz, zaj[3]);
                        WypiszCoJest(status, pomiedzy);
                        return true;
                    } else if ((zaj[4]).isBefore(teraz) && zaj[5].isAfter(teraz)) {
                        status = true;
                        pomiedzy = ChronoUnit.MINUTES.between(teraz, zaj[5]);
                        WypiszCoJest(status, pomiedzy);
                        return true;
                    } else if ((zaj[6]).isBefore(teraz) && zaj[7].isAfter(teraz)) {
                        status = true;
                        pomiedzy = ChronoUnit.MINUTES.between(teraz, zaj[7]);
                        WypiszCoJest(status, pomiedzy);
                        return true;
                    } else if ((zaj[8]).isBefore(teraz) && zaj[9].isAfter(teraz)) {
                        status = true;
                        pomiedzy = ChronoUnit.MINUTES.between(teraz, zaj[9]);
                        WypiszCoJest(status, pomiedzy);
                        return true;
                    } else if ((zaj[10]).isBefore(teraz) && zaj[11].isAfter(teraz)) {
                        status = true;
                        pomiedzy = ChronoUnit.MINUTES.between(teraz, zaj[11]);
                        WypiszCoJest(status, pomiedzy);
                        return true;
                    }
                    break;
                }
            }
        }
        return false;
    }

    public void execute(JobExecutionContext jobExecutionContext) {

        SprawdzCoJest(LocalTime.now());

    }

}
