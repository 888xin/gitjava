package simhash;

import java.math.BigInteger;

/**
 * Created by xin on 14-4-12.
 * 此为工具类
 * 传入字符串，调用cityHash工具类，转换为64位的cityHash二进制数
 * param 字符串或者字符串数组
 * return long/BigInteger/string
 */
public class String2CityHash {
    /**
     * str.getBytes().length 获取字节长度，数字和汉字不同，数字一个字节，汉字的unicode为三个字节
     * @param str
     * @return
     */
    public static long string2cityhash64(String str){
        int n = str.getBytes().length;
        return CityHash.cityHash64(str.getBytes(), 0, n);
    }

    public static long[] strings2cityhash64(String[] strs){
        long[] ls = new long[strs.length] ;
        for (int i = 0; i < strs.length; i++) {
            ls[i] =  String2CityHash.string2cityhash64(strs[i]);
        }
        return ls ;
    }

    public static BigInteger string2cityhash64ByBigInteger(String str){
        return new BigInteger(String2CityHash.string2cityhash64(str) + "") ;
    }

    public static BigInteger[] strings2cityhash64ByBigIntegers(String[] strs){
        BigInteger[] bigs = new BigInteger[strs.length] ;
        for (int i = 0; i < strs.length; i++) {
            bigs[i] = String2CityHash.string2cityhash64ByBigInteger(strs[i]);
        }
        return bigs ;
    }

    public static String string2cityhash64ByString(String str){
        return Long.toBinaryString(String2CityHash.string2cityhash64(str));
    }

    public static String[] strings2cityhash64ByStrings(String[] strs){
        String[] strings = new String[strs.length];
        for (int i = 0; i < strs.length; i++) {
            strings[i] = String2CityHash.string2cityhash64ByString(strs[i]);
        }
        return strings ;
    }
}
