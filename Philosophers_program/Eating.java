package Philosophers_program;

import java.util.Arrays;
// import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class Eating extends Thread {

    public Boolean[] forks;
    // private final Random random = new Random();
    // private final Integer councountEating = 0;
    private final CountDownLatch countDownLatch;
    private final Philosopher[] philosophers;

    public Eating() {
        philosophers = new Philosopher[5];
        countDownLatch = new CountDownLatch(5);
    }

    public void setStartCondition() {
        forks = new Boolean[5];
        Arrays.fill(forks, true);
        for (int i = 0; i < philosophers.length; i++) {
            philosophers[i] = new Philosopher(i, this);
        }
    }

    @Override
    public void run() {
        setStartCondition();
        for (int i = 0; i < philosophers.length; i++) {
            philosophers[i].start();
        }
    }

    public synchronized boolean getEating(int firstFork, int secondFork) {
        if (forks[firstFork] && forks[secondFork]) {
            forks[firstFork] = false;
            forks[secondFork] = false;
            return true;
        }
        return false;
    }

    public void returnEating(int firstFork, int secondFork) {
        forks[firstFork] = true;
        forks[secondFork] = true;
    }

    public CountDownLatch getCountDownLatch() {
        return countDownLatch;
    }
}