package simhash;

import java.math.BigInteger;

/**
 * Created by xin on 14-4-12.
 * 获得两个数的海明距离
 */
public class HammingDistance {
    /**
     * 两个二进制字符串的海明距离
     * @param str1
     * @param str2
     * @return
     */
    public static int getHammingByBitString(String str1, String str2){
        int distance;
        if (str1.length() != str2.length()) {
            distance = -1;
        } else {
            distance = 0;
            for (int i = 0; i < str1.length(); i++) {
                if (str1.charAt(i) != str2.charAt(i)) {
                    distance++;
                }
            }
        }
        return distance;
    }

    /**
     * 获得两个大整数的海明距离
     * @param bigInteger1
     * @param bigInteger2
     * @return
     */
    public static int getHammingByBigInteger(BigInteger bigInteger1, BigInteger bigInteger2){
        //两数异或后不同的为1
        BigInteger bigInteger = bigInteger1.xor(bigInteger2);
        int tot = 0;
        //统计bigInteger中二进制位数为1的个数
        //一个二进制数减去1，那么，从最后那个1（包括那个1）起后面所有的数字全都反了。n&(n-1)就相当于把后面的数字清0，
        //重复此操作看n能做多少次，次数就为海明距离
        while (bigInteger.signum() != 0) {
            tot += 1;
            bigInteger = bigInteger.and(bigInteger.subtract(new BigInteger("1")));
        }
        return tot;
    }

//    public static void main(String[] args) {
//        String b1 = Fingerprint.getFingerprint("北京 上海 香港 深圳 广州 台北 南京 大连 苏州 无锡 佛山 重庆 宁波 杭州 成都 武汉 澳门 天津 青岛 青岛");
//        String b2 = Fingerprint.getFingerprint("北京 上海 香港 深圳 广州 台北 南京 大连 苏州 无锡 佛山 佛山 重庆 宁波 杭州 成都 武汉 澳门 天津 青岛");
//        System.out.println(b1);
//        System.out.println(b2);
//        //System.out.println(HammingDistance.getHammingByBitString(b1,b2));
//    }
}
