package com.jinmark.core.utils;

import java.math.BigDecimal;
/**
 * 浮点数精确计算
 * QC
 * 2016年12月21日 下午2:15:19
 */
public class BigDecimalArithUtil {
	private static final int DIV_SCALE = 10;//除法精度（除不尽时保留10为小数）
    
	/**
	 * 小数精确加法
	 * @param d1 参数1
	 * @param d2 参数2
	 * @return double
	 */
    public static double add(double d1,double d2) {
        BigDecimal bd1 = BigDecimal.valueOf(d1);
        BigDecimal bd2 = BigDecimal.valueOf(d2);
        return bd1.add(bd2).doubleValue();
    }
    
    /**
     * 小数精确减法
     * @param d1 参数1
	 * @param d2 参数2
     * @return double
     */
    public static double sub(double d1,double d2) {
        BigDecimal bd1 = BigDecimal.valueOf(d1);
        BigDecimal bd2 = BigDecimal.valueOf(d2);
        return bd1.subtract(bd2).doubleValue();
    }
    
    /**
     * 小数精确乘法 
     * @param d1 参数1
	 * @param d2 参数2
     * @return double
     */
    public static double mul(double d1,double d2) {
        BigDecimal bd1 = BigDecimal.valueOf(d1);
        BigDecimal bd2 = BigDecimal.valueOf(d2);
        return bd1.multiply(bd2).doubleValue();
    }
    /**
     * 小数精确乘法
     * @param d1 参数1
	 * @param d2 参数2
     * @param scale 保留小数位
     * @return double
     */
    public static double mul(double d1,double d2, int scale) {
        BigDecimal bd1 = BigDecimal.valueOf(d1);
        BigDecimal bd2 = BigDecimal.valueOf(d2);
        return bd1.multiply(bd2).setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
    
    /**
     * 小数精确除法，默认保留10位小数
     * @param d1 参数1
	 * @param d2 参数2
     * @return double
     */
    public static double div(double d1,double d2) {
        BigDecimal bd1 = BigDecimal.valueOf(d1);
        BigDecimal bd2 = BigDecimal.valueOf(d2);
        /*
         * 当除不尽时，以四舍五入的方式
         */
        return bd1.divide(bd2, DIV_SCALE, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
    
    /**
     * 小数精确除法保留scale位小数
     * @param d1 参数1
	 * @param d2 参数2
     * @param scale 保留小数位
     * @return double
     */
    public static double div(double d1,double d2, int scale) {
        BigDecimal bd1 = BigDecimal.valueOf(d1);
        BigDecimal bd2 = BigDecimal.valueOf(d2);
        /*
         * 当除不尽时，以四舍五入的方式
         */
        return bd1.divide(bd2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
    /**
     * 将浮点数保留scale位小数
     * @param data 数据
     * @param scale 小数位
     * @return double
     */
    public static double setScale(double data, int scale) {
    	return BigDecimal.valueOf(data).setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
