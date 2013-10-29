package org.grove.common;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class SerializableTest {

	@Test
	public void serialize() throws Exception {
		List<String> list1 = new ArrayList<String>();
		list1.add("xiaolin");
		list1.add("xianglin");
		list1.add("linlin");
		list1.add("xiangxiang");

		List<String> list2 = new ArrayList<String>();
		list2.add("wangyu");
		list2.add("liugang");
		list2.add("lihonggang");
		list2.add("jifuli");

		FileOutputStream fos = new FileOutputStream("d:/list");
		ObjectOutputStream oos = new ObjectOutputStream(fos);

		oos.writeObject(list1);
		oos.writeObject(list2);

		oos.close();
		fos.close();

		FileInputStream fis = new FileInputStream("d:/list");
		ObjectInputStream ois = new ObjectInputStream(fis);

		ArrayList<String> reserializ1 = (ArrayList<String>) ois.readObject();

		for (String str : reserializ1) {
			System.out.println(str);
		}

		ArrayList<String> reserializ2 = (ArrayList<String>) ois.readObject();

		for (String str : reserializ2) {
			System.out.println(str);
		}

	}

}
