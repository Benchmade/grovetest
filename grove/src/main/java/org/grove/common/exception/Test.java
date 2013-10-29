package org.grove.common.exception;

public class Test {

	public void run(int i){
		if(i==0){
			throw new RunTimeExceptionChild("i==0");
		}
	}
	
	public void ref(){
		run(0);
	}
	
	public static void main(String[] args) {
		Test t = new Test();
		t.ref();
	}

}
