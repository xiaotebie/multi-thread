package Practisefirst;

 class Parksss {

    private int state = 3;

    public synchronized void CarIn(int i) {

        try {
            while (state == 0) {
                System.out.println("目前空余车位为 " + state + " 请等待");
                wait();
            }
            System.out.println(i+"号车停车成功");
            state = state - 1;
            System.out.println("目前剩余车位： " + state);
            notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void CarOut(int i) {

        try {
            while (state == 3) {
                wait();
            }
            System.out.println(i +"号车已驶出");
            state = state + 1;
            System.out.println("目前剩余车位： " + state);
            notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}


 class CarInThread extends Thread{
 Parksss park=new Parksss();
 public CarInThread(Parksss park) {
     this.park=park;
 }

 @Override
    public void run() {
        super.run();
        for(int i=1;i<20;i++){
            park.CarIn(i);
        }
    }
}



 class CarOutThread extends Thread{
Parksss park=new Parksss();
public CarOutThread(Parksss park) {
this.park=park;
}
@Override
    public void run() {
        super.run();
        for(int i=1;i<20;i++){
            park.CarOut(i);
        }
    }
}



public class Parkingspace {
	public static void main(String[] args){
	      Parksss park =new Parksss();
	      CarInThread cInThread=new CarInThread(park);
	      CarOutThread cOutThread=new CarOutThread(park);
	      new Thread(cInThread).start();
	      new Thread(cOutThread).start();


	}
}
