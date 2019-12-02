package com.test.platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

@SpringBootApplication
public class TestPlatformApplication {

    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        ReentrantLock lock1 = new ReentrantLock();

        Semaphore sem = new Semaphore(3);
        sem.tryAcquire();
        sem.release();
        sem.acquire();
        sem.tryAcquire();
        sem.release();
        lock.tryLock();
        lock.lock();
        lock.unlock();
        SpringApplication.run(TestPlatformApplication.class, args);
    }

}
