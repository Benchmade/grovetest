package org.grove.common.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class PatternTest {

	@Test
	public void pattern()throws Exception{
		Pattern p = Pattern.compile("(\\d{8})");
		Matcher m = p.matcher("wbv4_1_1_20120409_000123");
		if(m.find()){
			System.out.println(m.group());
		}
	}
	
}
