import java.util.Scanner;

class BankAccount{

    //lock object
    public static Object lock = new Object();

    //Instance variables
    private int accountbalance;
    private String accountholdername;
    boolean isWithdrawing = false;
    Scanner sc;
    //Constructor
    BankAccount(int accountbalance,String accountholdername){
        this.accountbalance = accountbalance;
        this.accountholdername = accountholdername;
        this.sc = new Scanner(System.in);
    }

    //deposit
    synchronized public void deposit(){
        System.out.println("Enter amount to be deposited : ");
        int ammount = sc.nextInt();
        synchronized(lock){
            while(isWithdrawing){
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            accountbalance+= ammount;
            isWithdrawing = true;
            lock.notify();
        }
        System.out.println("Dear,"+this.accountholdername+" "+ammount+"/- credited into your account");
        System.out.println("Total Balance is "+accountbalance);
    }

    //withdraw
    synchronized public void withdraw(){
        System.out.println("Enter amount to be withdrawed : ");
        int ammount = sc.nextInt();
        if(ammount>this.accountbalance){
            System.out.println("Insufficeint Balance!");
            return ;
        }
        synchronized(lock){
            while(!isWithdrawing){
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            accountbalance-= ammount;
            isWithdrawing = false;
            lock.notify();
        }
        System.out.println("Dear,"+this.accountholdername+" "+ammount+"/- debited from your account");
        System.out.println("Total Balance is "+accountbalance);
    }

    //scanner close
    public void scannerclose(){
        sc.close();
        System.out.println("Scanner closed...");
    }
}

class Main{
    public static void main(String a[]){
        BankAccount acc1 = new BankAccount(100000,"Avinash");
        Thread depositthread = new Thread(()->acc1.deposit());
        Thread withdrawthread = new Thread(()->acc1.withdraw());
        depositthread.start();
        withdrawthread.start();
        try{
            depositthread.join();
            withdrawthread.join();
        }catch(Exception e){
            e.printStackTrace();
        }
        acc1.scannerclose();
    }
}
