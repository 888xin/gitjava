package simhash;

import trash.util.HashAlgorithms;

import java.math.BigInteger;

/**
 * 返回指纹fingerprint
 * Created by xin on 14-4-12.
 */
public class Fingerprint {
    private static final int BITS = 64 ; //位数

    /**
     * 返回有权值的指纹字符串
     * @param weight
     * @param str
     * @return
     */
    public static String getFingerprint(int[] weight, String str){
        int[] v = new int[BITS]; //64位待取值的特征值
        String[] splitStr = str.split(" ");
        for (int i = 0; i < splitStr.length; i++) {
            //调用外面的函数，计算出64位的Hash值，这个可以采取其他的算法，不拘泥于一种，此处用的是Google的cityHash
            BigInteger t = String2CityHash.string2cityhash64ByBigInteger(splitStr[i]);
            for (int j = 0; j < BITS; j++) {
                BigInteger bitmask = new BigInteger("1").shiftLeft(j);
                if (t.and(bitmask).signum() != 0) {//与1“与”，还是本身，就像与0“异或”一个样
                    v[j] += weight[i]; //权值
                }else {
                    v[j] -= weight[i];
                }
            }
        }
        StringBuffer simHashBuffer = new StringBuffer();
        for (int i = 0; i < BITS; i++) {
            if (v[i] >= 0) {
                simHashBuffer.append("1");
            } else {
                simHashBuffer.append("0");
            }
        }
        return simHashBuffer.toString() ;
    }

    /**
     * 两个字符串分别采用不用的权重，返回指纹字符串
     * @param weight 字符串str1 的权重
     * @param str1 采用指定权重
     * @param str2 默认权重为1
     * @return
     */
    public static String getFingerprint(int weight, String str1, String str2){
        int[] v = new int[BITS]; //64位待取值的特征值
        String[] splitStr1 = str1.split(" ");
        String[] splitStr2 = str2.split(" ");

        for (int i = 0; i < splitStr1.length; i++) {
            //调用外面的函数，计算出64位的Hash值，这个可以采取其他的算法，不拘泥于一种，此处用的是Google的cityHash
            BigInteger t = String2CityHash.string2cityhash64ByBigInteger(splitStr1[i]);
            for (int j = 0; j < BITS; j++) {
                BigInteger bitmask = new BigInteger("1").shiftLeft(j);
                if (t.and(bitmask).signum() != 0) {//与1“与”，还是本身，就像与0“异或”一个样
                    v[j] += weight; //权值
                }else {
                    v[j] -= weight;
                }
            }
        }
        for (int i = 0; i < splitStr2.length; i++) {
            //调用外面的函数，计算出64位的Hash值，这个可以采取其他的算法，不拘泥于一种，此处用的是Google的cityHash
            BigInteger t = String2CityHash.string2cityhash64ByBigInteger(splitStr2[i]);
            for (int j = 0; j < BITS; j++) {
                BigInteger bitmask = new BigInteger("1").shiftLeft(j);
                if (t.and(bitmask).signum() != 0) {//与1“与”，还是本身，就像与0“异或”一个样
                    v[j] += 1; //权值
                }else {
                    v[j] -= 1;
                }
            }
        }
        StringBuffer simHashBuffer = new StringBuffer();
        for (int i = 0; i < BITS; i++) {
            if (v[i] >= 0) {
                simHashBuffer.append("1");
            } else {
                simHashBuffer.append("0");
            }
        }
        return simHashBuffer.toString() ;
    }

    /**
     * 返回无权值的指纹字符串
     * @param str
     * @return
     */
    public static String getFingerprint(String str){
        String[] strs = str.split(" ");
        int[] number = new int[strs.length];
        for (int i = 0; i < strs.length; i++) {
            number[i] = 1 ;
        }
        return Fingerprint.getFingerprint(number, str) ;
    }


    /**
     * 返回有权值的指纹大整数
     * @param weight
     * @param str
     * @return
     */
    public static BigInteger getFingerprintByBigInteger(int[] weight, String str){
        int[] v = new int[BITS]; //64位待取值的特征值
        String[] splitStr = str.split(" ");
        for (int i = 0; i < splitStr.length; i++) {
            BigInteger t = String2CityHash.string2cityhash64ByBigInteger(splitStr[i]);
            for (int j = 0; j < BITS; j++) {
                BigInteger bitmask = new BigInteger("1").shiftLeft(j);
                if (t.and(bitmask).signum() != 0) {
                    v[j] += weight[i];
                }else {
                    v[j] -= weight[i];
                }
            }
        }
        BigInteger fingerprint = new BigInteger("0");
        for (int i = 0; i < BITS; i++) {
            if (v[i] >= 0) {
                fingerprint = fingerprint.add(new BigInteger("1").shiftLeft(i)); //遇到1，加上偏移量
            }
        }
        return fingerprint ;
    }

    /**
     * 返回无权值的指纹大整数
     * @param str
     * @return
     */
    public static BigInteger getFingerprintByBigInteger(String str){
        String[] strs = str.split(" ");
        int[] number = new int[strs.length];
        for (int i = 0; i < strs.length; i++) {
            number[i] = 1 ;
        }
        return Fingerprint.getFingerprintByBigInteger(number, str) ;
    }


    /**
     * 使用cityHash默认带的字符串hash函数
     * 两个字符串分别采用不用的权重，返回指纹字符串
     * @param weight 字符串str1 的权重
     * @param str1 采用指定权重
     * @param str2 默认权重为1
     * @return
     */
    public static String getFingerprintByCommon64Hash(int weight, String str1, String str2){
        int[] v = new int[BITS]; //64位待取值的特征值
        String[] splitStr1 = str1.split(" ");
        String[] splitStr2 = str2.split(" ");

        for (int i = 0; i < splitStr1.length; i++) {
            //调用cityHash自带的生成64位hash函数
            BigInteger t = Common64Hash.hash(splitStr1[i]);
            for (int j = 0; j < BITS; j++) {
                BigInteger bitmask = new BigInteger("1").shiftLeft(j);
                if (t.and(bitmask).signum() != 0) {//与1“与”，还是本身，就像与0“异或”一个样
                    v[j] += weight; //权值
                }else {
                    v[j] -= weight;
                }
            }
        }
        for (int i = 0; i < splitStr2.length; i++) {
            //调用外面的函数，计算出64位的Hash值，这个可以采取其他的算法，不拘泥于一种，此处用的是Google的cityHash
            BigInteger t = Common64Hash.hash(splitStr2[i]);
            for (int j = 0; j < BITS; j++) {
                BigInteger bitmask = new BigInteger("1").shiftLeft(j);
                if (t.and(bitmask).signum() != 0) {//与1“与”，还是本身，就像与0“异或”一个样
                    v[j] += 1; //权值
                }else {
                    v[j] -= 1;
                }
            }
        }
        StringBuffer simHashBuffer = new StringBuffer();
        for (int i = 0; i < BITS; i++) {
            if (v[i] >= 0) {
                simHashBuffer.append("1");
            } else {
                simHashBuffer.append("0");
            }
        }
        return simHashBuffer.toString() ;
    }

    /**
     * 使用BKDRHash和APHash组成的64位字符串hash函数
     * 两个字符串分别采用不用的权重，返回指纹字符串
     * @param weight 字符串str1 的权重
     * @param str1 采用指定权重
     * @param str2 默认权重为1
     * @return
     */
    public static String getFingerprintByBKDRHashAndAPHash(int weight, String str1, String str2){
        int[] v = new int[BITS]; //64位待取值的特征值
        String[] splitStr1 = str1.split(" ");
        String[] splitStr2 = str2.split(" ");

        for (int i = 0; i < splitStr1.length; i++) {
            BigInteger t = HashAlgorithms.BKDRHashAndAPHash(splitStr1[i]);
            for (int j = 0; j < BITS; j++) {
                BigInteger bitmask = new BigInteger("1").shiftLeft(j);
                if (t.and(bitmask).signum() != 0) {//与1“与”，还是本身，就像与0“异或”一个样
                    v[j] += weight; //权值
                }else {
                    v[j] -= weight;
                }
            }
        }
        for (int i = 0; i < splitStr2.length; i++) {
            //调用外面的函数，计算出64位的Hash值，这个可以采取其他的算法，不拘泥于一种，此处用的是Google的cityHash
            BigInteger t = HashAlgorithms.BKDRHashAndAPHash(splitStr2[i]);
            for (int j = 0; j < BITS; j++) {
                BigInteger bitmask = new BigInteger("1").shiftLeft(j);
                if (t.and(bitmask).signum() != 0) {//与1“与”，还是本身，就像与0“异或”一个样
                    v[j] += 1; //权值
                }else {
                    v[j] -= 1;
                }
            }
        }
        StringBuffer simHashBuffer = new StringBuffer();
        for (int i = 0; i < BITS; i++) {
            if (v[i] >= 0) {
                simHashBuffer.append("1");
            } else {
                simHashBuffer.append("0");
            }
        }
        return simHashBuffer.toString() ;
    }

//    public static void main(String[] args) {
//        System.out.println(Long.toBinaryString(Fingerprint.getFingerprintByBigInteger("23423423").longValue()));
//        System.out.println(Fingerprint.getFingerprint(new int[]{1, 1}, "你好 我是谁"));
//    }
}
