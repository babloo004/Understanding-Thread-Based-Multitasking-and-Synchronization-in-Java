import java.util.Scanner;

class Primeadd {
    long limit;
    long  primeadd = 0;

    Primeadd(long limit) {
        this.limit = limit;
    }

    public boolean isPrime(long num) {
        if (num <= 1) return false;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) return false;
        }
        return true;
    }

    synchronized public void addToPrime(long start, long end) {
        for (long i = start; i <= end; i++) {
            if (isPrime(i)) {
                synchronized (this) {
                    primeadd += i;
                }
            }
        }
    }

    public long finalAnswer() {
        return primeadd;
    }
}
// 3203324994356
class Main {
    public static void main(String[] args) {
        long limit;
        System.out.println("Enter limit : ");
        Scanner sc = new Scanner(System.in);
        limit = sc.nextLong();
        sc.close();
        Primeadd p = new Primeadd(limit);
        Thread t1 = new Thread(() -> p.addToPrime(1, limit/2));
        Thread t2 = new Thread(() -> p.addToPrime((limit/2)+1, limit));
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Total prime addition: " + p.finalAnswer());
    }
}
