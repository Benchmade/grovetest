package org.grove.common.classloader;

/**
 * 测试MethodHandler和反射的性能,测试结果显示MethodHandler性能优于反射
 * @author xiaolin.mxl
 *
 */
public class MethodHandleTest {
	
	private String name;
	private String company;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public void printName(){
		System.out.println("name");
	}
	
	public static void xxx(){
		System.out.println("xxx");
	}
	
	
	
	public static void main(String[] args) throws Throwable {
		
		/*//执行时间在50-60之间
		MethodHandles.Lookup lookup = MethodHandles.lookup();
		MethodType mt = MethodType.methodType(String.class, char.class, char.class);
		MethodHandle mh = lookup.findVirtual(String.class, "replace", mt);
		long st = System.currentTimeMillis();
		for(int i=0;i<100;i++){
			for(int j=0;j<10000;j++){
				String s = (String) mh.invokeExact("daddy", 'd', 'n');
			}
			
		}
		System.out.println(System.currentTimeMillis()-st);*/
		//稳定在75-80之间
		/*Class stringClass = Class.forName("java.lang.String");
		Method method = stringClass.getDeclaredMethod("replace", char.class, char.class);
		long st = System.currentTimeMillis();
		for(int i=0;i<100;i++){
			for(int j=0;j<10000;j++){
				String s = (String) method.invoke("daddy", 'd', 'n');
			}
		}
		System.out.println(System.currentTimeMillis()-st);
		*/
		
		//36ms
		/*long st = System.currentTimeMillis();
		for(int i=0;i<100;i++){
			for(int j=0;j<10000;j++){
				String s = "daddy".replace('d', 'n');
			}
			
		}
		System.out.println(System.currentTimeMillis()-st);*/
	}

}
