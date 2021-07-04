package de.techgamez.pleezon;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class GameClock {
    private static final ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
    private static ScheduledFuture<?> futureTask;
    private static Runnable task = GameOfLife::tick;
    public static void start(){
        futureTask = scheduledExecutorService.scheduleAtFixedRate(task, 0, 250, TimeUnit.MILLISECONDS);
    }
    public static void changeReadInterval(long time)
    {
        if(time > 0)
        {
            if (futureTask != null)
            {
                futureTask.cancel(true);
            }
            futureTask = scheduledExecutorService.scheduleAtFixedRate(task, 0, time, TimeUnit.MILLISECONDS);
        }
    }
}
