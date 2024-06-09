package Philosophers_program;

public class Philosopher extends Thread {
    Integer numPhilosopher;
    Integer countEating;
    Eating eatingThings;

    public Philosopher(Integer numPhilosopher, Eating eatingThings) {
        this.numPhilosopher = numPhilosopher;
        this.eatingThings = eatingThings;
        this.countEating = 0;
    }

    @Override
    public void run() {
        while (countEating < 3) {
            int firstFork = getNumPhilosopher();
            int secondFork = (getNumPhilosopher() + 1) - 5 * (getNumPhilosopher() / 4);
            if (eatingThings.getEating(firstFork, secondFork)) {
                try {
                    eatingProcess(firstFork, secondFork);
                    thinkingProcess();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            eatingThings.getCountDownLatch().countDown();
        }
    }

    public Integer getNumPhilosopher() {
        return numPhilosopher;
    }

    public void eatingProcess(int firstFork, int secondFork) throws InterruptedException {
        System.out.println("The philosopher " + getNumPhilosopher() + " uses the forks "
                + firstFork + " и " + secondFork);
        countEating++;
        sleep(3000);
        System.out.println("The philosopher " + getNumPhilosopher() + " puts the forks down "
                + firstFork + " and " + secondFork + " - starts thinking.");
        eatingThings.returnEating(firstFork, secondFork);
    }

    public void thinkingProcess() throws InterruptedException {
        System.out.println("The philosopher " + getNumPhilosopher() + " thinks");
        sleep(3000);
    }

    @Override
    public String toString() {
        return String.format("%s", "The philosopher No." + numPhilosopher);
    }
}