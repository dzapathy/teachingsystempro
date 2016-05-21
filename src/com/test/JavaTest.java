package com.test;

import java.util.List;
import java.util.UUID;

import org.junit.Test;

import com.util.RandomUtil;

public class JavaTest {
	
	@Test
	public void test1(){
		UUID uuid = UUID.randomUUID();
		System.out.println("uploadfile/ppt/"+uuid+".ppt");
	}
	
	@Test
	public void test2(){
		List<Integer> list = RandomUtil.getRandom(10 , 30);
		String choiceStr = list.toString();
		System.out.println(list.toString());
		System.out.println(choiceStr.substring(1, choiceStr.length()-1));
	}
}
