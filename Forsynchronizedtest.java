package Practisefirst;
class Printcontent{
	private int flag=0;
	private int i=1;
	private char c=65;
	
	public Printcontent() {}
	public synchronized void printContentNum() {
		try {
			System.out.print(i);
			if (i%2==0) {//余数为零输入字母
				++i;
				wait();
			}else {
				//打印数字
					++i;	
				notifyAll();
							
			}
		} catch (InterruptedException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public synchronized void printContentChar() {
		try {
			
					System.out.print(c);
					++c;
					notifyAll();
					wait();
			
		} catch (InterruptedException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	} 
	//public void printChar(char c) {System.out.println(c);}
	//public void printNum(int i) {System.out.println(i);}
}



class Usenumber extends Thread{
	private Printcontent printcontent;
	//构造函数
	public Usenumber(String s,Printcontent printcontent) {
		super("s");
		this.printcontent=printcontent;
		
		
	}
	public void run() {
		for(int i=0;i<52;i++) {
				printcontent.printContentNum();
		}
	}	
}



class Useword extends Thread{
	private Printcontent printcontent;
	public Useword(String s,Printcontent printcontent) {
		super("s");
		this.printcontent=printcontent;
	}
	public void run() {
		for(int i=65;i<=90;i++) {
			printcontent.printContentChar();
		}
	}
	
}

public class Forsynchronizedtest {
	public static void main(String[] args) {
		Printcontent printcontent=new Printcontent();
		new Usenumber("num", printcontent).start();
		new Useword("num", printcontent).start();	
	}

}
