package com.jinmark.core.utils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 排序
 * QC
 * 2017年2月21日 上午9:52:29
 */
public class SortUtils {
	
	public static void main(String[] args) {
		Integer[] dd = new Integer[] {1,2,7,26,5,78,3,2,1};
		arraySortASC(dd);
		
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(4);
		list.add(2);
		list.add(898);
		list.add(3);
		list.add(67);
		list.add(21);
		listSortDESC(list);
		System.out.println(list.toString());
	}
	
	
	/**
	 * 容器List降序+Comparator
	 * @param list
	 * @param com
	 * @return void
	 */
	public static <T> void listSortDESC(List<T> list, Comparator<T> com) {
		// 第一步：转成数组
		Object[] array = list.toArray();
		arraySortDESC(array, com);
		// 第二步：改变容器中对应的值
		for (int i = 0; i < array.length; i++) {
			list.set(i, (T)array[i]);
		}
	}
	/**
	 * 容器List升序+Comparator
	 * @param list
	 * @param com
	 * @return void
	 */
	public static <T> void listSortASC(List<T> list, Comparator<T> com) {
		// 第一步：转成数组
		Object[] array = list.toArray();
		arraySortASC(array, com);
		// 第二步：改变容器中对应的值
		for (int i = 0; i < array.length; i++) {
			list.set(i, (T)array[i]);
		}
	}
	
	
	
	/**
	 * 容器List降序
	 * @param list
	 * @return void
	 */
	public static <T extends Comparable<T>> void listSortDESC(List<T> list) {
		// 第一步：转成数组
		Object[] array = list.toArray();
		arraySortDESC(array);
		// 第二步：改变容器中对应的值
		for (int i = 0; i < array.length; i++) {
			list.set(i, (T)array[i]);
		}
	}
	/**
	 * 容器List升序
	 * @param list
	 * @return void
	 */
	public static <T extends Comparable<T>> void listSortASC(List<T> list) {
		// 第一步：转成数组
		Object[] array = list.toArray();
		arraySortASC(array);
		// 第二步：改变容器中对应的值
		for (int i = 0; i < array.length; i++) {
			list.set(i, (T)array[i]);
		}
	}
	
	/**
	 * 数组冒泡排序：降序，使用泛型
	 * @param array
	 * @return void
	 */
	public static <T extends Comparable<T>> void arraySortDESC(T[] array) {
		boolean sorted = true;
		int len = array.length;
		for(int i = 0; i< len-1; i++) {//趟数array.length-1
			sorted = true;//假定有序
			for(int j = 0; j < len-(i+1); j++) {//次数，每趟次数减少
				if(((Comparable)array[j]).compareTo((Comparable)array[j+1]) < 0) {
					T temp = array[j];
					array[j] = array[j+1];
					array[j+1] = temp;
					sorted = false;
				}
			}
			
			if(sorted) {//减少趟数
				break;
			}
		}
	}
	/**
	 * 数组冒泡排序：升序，使用泛型
	 * @param array
	 * @return void
	 */
	public static <T extends Comparable<T>> void arraySortASC(T[] array) {
		boolean sorted = true;
		int len = array.length;
		for(int i = 0; i< len-1; i++) {//趟数array.length-1
			sorted = true;//假定有序
			for(int j = 0; j < len-(i+1); j++) {//次数，每趟次数减少
				if(((Comparable)array[j]).compareTo((Comparable)array[j+1]) > 0) {
					T temp = array[j];
					array[j] = array[j+1];
					array[j+1] = temp;
					sorted = false;
				}
			}
			
			if(sorted) {//减少趟数
				break;
			}
		}
	}
	
	/**
	 * 数组冒泡排序：降序,不使用泛型
	 * @param array
	 * @return void
	 */
	public static void arraySortDESC(Object[] array) {
		boolean sorted = true;
		int len = array.length;
		for(int i = 0; i< len-1; i++) {//趟数array.length-1
			sorted = true;//假定有序
			for(int j = 0; j < len-(i+1); j++) {//次数，每趟次数减少
				if(((Comparable)array[j]).compareTo((Comparable)array[j+1]) < 0) {
					Object temp = array[j];
					array[j] = array[j+1];
					array[j+1] = temp;
					sorted = false;
				}
			}
			
			if(sorted) {//减少趟数
				break;
			}
		}
	}
	/**
	 * 数组冒泡排序：升序，不使用泛型
	 * @param array
	 * @return void
	 */
	public static void arraySortASC(Object[] array) {
		boolean sorted = true;
		int len = array.length;
		for(int i = 0; i< len-1; i++) {//趟数array.length-1
			sorted = true;//假定有序
			for(int j = 0; j < len-(i+1); j++) {//次数，每趟次数减少
				if(((Comparable)array[j]).compareTo((Comparable)array[j+1]) > 0) {
					Object temp = array[j];
					array[j] = array[j+1];
					array[j+1] = temp;
					sorted = false;
				}
			}
			
			if(sorted) {//减少趟数
				break;
			}
		}
	}
	
	/**
	 * 数组冒泡排序：降序,+Comparator接口（自定义排序规则）
	 * @param array
	 * @param com
	 * @return void
	 */
	public static <T> void arraySortDESC(Object[] array, Comparator<T> com) {
		boolean sorted = true;
		int len = array.length;
		for(int i = 0; i< len-1; i++) {//趟数array.length-1
			sorted = true;//假定有序
			for(int j = 0; j < len-(i+1); j++) {//次数，每趟次数减少
				if(com.compare((T)array[j], (T)array[j+1]) < 0) {
					Object temp = array[j];
					array[j] = array[j+1];
					array[j+1] = temp;
					sorted = false;
				}
			}
			
			if(sorted) {//减少趟数
				break;
			}
		}
	}
	
	/**
	 * 数组冒泡排序：升序，+Comparator接口（自定义排序规则）
	 * @param array
	 * @param com
	 * @return void
	 */
	public static <T> void arraySortASC(Object[] array, Comparator<T> com) {
		boolean sorted = true;
		int len = array.length;
		for(int i = 0; i< len-1; i++) {//趟数array.length-1
			sorted = true;//假定有序
			for(int j = 0; j < len-(i+1); j++) {//次数，每趟次数减少
				if(com.compare((T)array[j], (T)array[j+1]) > 0) {
					Object temp = array[j];
					array[j] = array[j+1];
					array[j+1] = temp;
					sorted = false;
				}
			}
			
			if(sorted) {//减少趟数
				break;
			}
		}
	}
}
