package trash.util;

import java.math.BigInteger;

/**
 * Created by xin on 14-4-11.
 * Hash算法大全（传统32位hash数）
 * 推荐使用FNV1算法
 */
public class HashAlgorithms {
    private static int M_MASK = 0x8765fed1;
    private static int M_SHIFT = 0;
    /**
     * 加法hash
     * @param key 字符串
     * @param prime 一个质数
     * @return hash结果
     */
    public static int additiveHash(String key, int prime){
        int hash, i ;
        for (hash = key.length(), i = 0; i < key.length(); i++) {
            hash += key.charAt(i);
        }
        return (hash % prime) ;
    }

    /**
     * 旋转hash
     * @param key
     * @param prime
     * @return
     */
    public static int rotatingHash(String key, int prime){
        int hash = key.length();
        for (int i = 0; i < key.length(); ++i) {
            hash = (hash << 4) ^ (hash >> 28) ^ key.charAt(i);
        }
        return (hash % prime);
    }

    /**
     * 一次一个hash
     * @param key
     * @return
     */
    public static int oneByOneHash(String key){
        int hash,i ;
        for (i = 0,hash = 0; i < key.length(); ++i) {
            hash += key.charAt(i);
            hash += (hash << 10);
            hash ^= (hash >> 6) ;
        }
        hash += (hash << 3);
        hash ^= (hash >> 11);
        hash += (hash << 15);
        return hash;
    }

    /**
     * Bernstein's hash
     * @param key
     * @return
     */
    public static int bernstein(String key) {
        int hash = 0;
        int i;
        for (i = 0; i < key.length(); ++i)
            hash = 33 * hash + key.charAt(i);
        return hash;
    }

    /**
     * Universal Hashing
     * @param key
     * @param mask
     * @param tab
     * @return
     */
    public static int universal(char[] key, int mask, int[] tab) {
        int hash = key.length, i, len = key.length;
        for (i = 0; i < (len << 3); i += 8) {
            char k = key[i >> 3];
            if ((k & 0x01) == 0)
                hash ^= tab[i + 0];
            if ((k & 0x02) == 0)
                hash ^= tab[i + 1];
            if ((k & 0x04) == 0)
                hash ^= tab[i + 2];
            if ((k & 0x08) == 0)
                hash ^= tab[i + 3];
            if ((k & 0x10) == 0)
                hash ^= tab[i + 4];
            if ((k & 0x20) == 0)
                hash ^= tab[i + 5];
            if ((k & 0x40) == 0)
                hash ^= tab[i + 6];
            if ((k & 0x80) == 0)
                hash ^= tab[i + 7];
        }
        return (hash & mask);
    }

    /**
     * Zobrist Hashing
     * @param key
     * @param mask
     * @param tab
     * @return
     */
    public static int zobrist(char[] key, int mask, int[][] tab) {
        int hash, i;
        for (hash = key.length, i = 0; i < key.length; ++i)
            hash ^= tab[i][key[i]];
        return (hash & mask);
    }

    /**
     * 32位的FNV算法
     * @param data
     * @return
     */
    public static int FNVHash(byte[] data) {
        int hash = (int) 2166136261L;
        for (byte b : data)
            hash = (hash * 16777619) ^ b;
        if (M_SHIFT == 0)
            return hash;
        return (hash ^ (hash >> M_SHIFT)) & M_MASK;
    }

    /**
     * 改进的32位FNV算法1
     * @param data
     * @return
     */
    public static int FNVHash1(byte[] data) {
        final int p = 16777619;
        int hash = (int) 2166136261L;
        for (byte b : data)
            hash = (hash ^ b) * p;
        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;
        return hash;
    }

    /**
     * Thomas Wang的算法，整数hash
     * @param key
     * @return
     */
    public static int intHash(int key) {
        key += ~(key << 15);
        key ^= (key >>> 10);
        key += (key << 3);
        key ^= (key >>> 6);
        key += ~(key << 11);
        key ^= (key >>> 16);
        return key;
    }

    /**
     * RS算法hash
     * @param str
     * @return
     */
    public static int RSHash(String str) {
        int b = 378551;
        int a = 63689;
        int hash = 0;
        for (int i = 0; i < str.length(); i++) {
            hash = hash * a + str.charAt(i);
            a = a * b;
        }
        return (hash & 0x7FFFFFFF);
    }

    /**
     * JS算法
     * @param str
     * @return
     */
    public static int JSHash(String str) {
        int hash = 1315423911;
        for (int i = 0; i < str.length(); i++) {
            hash ^= ((hash << 5) + str.charAt(i) + (hash >> 2));
        }
        return (hash & 0x7FFFFFFF);
    }

    /**
     *  PJW算法
     * @param str
     * @return
     */
    public static int PJWHash(String str) {
        int BitsInUnsignedInt = 32;
        int ThreeQuarters = (BitsInUnsignedInt * 3) / 4;
        int OneEighth = BitsInUnsignedInt / 8;
        int HighBits = 0xFFFFFFFF << (BitsInUnsignedInt - OneEighth);
        int hash = 0;
        int test = 0;
        for (int i = 0; i < str.length(); i++) {
            hash = (hash << OneEighth) + str.charAt(i);

            if ((test = hash & HighBits) != 0) {
                hash = ((hash ^ (test >> ThreeQuarters)) & (~HighBits));
            }
        }
        return (hash & 0x7FFFFFFF);
    }

    /**
     * ELF算法
     * @param str
     * @return
     */
    public static int ELFHash(String str) {
        int hash = 0;
        int x = 0;
        for (int i = 0; i < str.length(); i++) {
            hash = (hash << 4) + str.charAt(i);
            if ((x = (int) (hash & 0xF0000000L)) != 0) {
                hash ^= (x >> 24);
                hash &= ~x;
            }
        }
        return (hash & 0x7FFFFFFF);
    }

    /**
     * BKDR算法
     * @param str
     * @return
     */
    public static int BKDRHash(String str) {
        int seed = 131; // 31 131 1313 13131 131313 etc..
        int hash = 0;

        for (int i = 0; i < str.length(); i++) {
            hash = (hash * seed) + str.charAt(i);
        }
        return (hash & 0x7FFFFFFF);
    }

    public static BigInteger BKDRHash64(String str){
        BigInteger seed = new BigInteger("131313") ;
        BigInteger hash = new BigInteger("0");
        if (str == null || str.length() == 0) {
            return new BigInteger("0");
        } else {
            for (int i = 0; i < str.length(); i++) {
                hash = hash.add(hash.multiply(seed)).add(new BigInteger(str.charAt(i) + ""));
            }
            BigInteger mask = new BigInteger("2").pow(64).subtract(new BigInteger("1"));
            return hash.add(mask) ;
        }
    }

    public static BigInteger BKDRHashAndAPHash(String str){
        int n1 = BKDRHash(str);
        int n2 = APHash(str);
        StringBuilder builder = new StringBuilder();
        builder.append(Integer.toBinaryString(n1)).append(Integer.toBinaryString(n2));
        //BigInteger mask = new BigInteger("2").pow(64).subtract(new BigInteger("1"));
        //BigInteger bigInteger = new BigInteger(builder.toString());
        BigInteger bigInteger = new BigInteger(String.valueOf(Long.valueOf(builder.toString(),2)));
        //return mask.and(bigInteger) ;
        return bigInteger ;
    }

    /**
     *  SDBM算法
     * @param str
     * @return
     */
    public static int SDBMHash(String str) {
        int hash = 0;
        for (int i = 0; i < str.length(); i++) {
            hash = str.charAt(i) + (hash << 6) + (hash << 16) - hash;
        }
        return (hash & 0x7FFFFFFF);
    }

    /**
     * DJB算法
     */
    public static int DJBHash(String str) {
        int hash = 5381;
        for (int i = 0; i < str.length(); i++) {
            hash = ((hash << 5) + hash) + str.charAt(i);
        }
        return (hash & 0x7FFFFFFF);
    }


    /**
     * DEK算法
     */
    public static int DEKHash(String str) {
        int hash = str.length();
        for (int i = 0; i < str.length(); i++) {
            hash = ((hash << 5) ^ (hash >> 27)) ^ str.charAt(i);
        }
        return (hash & 0x7FFFFFFF);
    }

    /**
     * AP算法
     */
    public static int APHash(String str) {
        int hash = 0;
        for (int i = 0; i < str.length(); i++) {
            hash ^= ((i & 1) == 0) ? ((hash << 7) ^ str.charAt(i) ^ (hash >> 3))
                    : (~((hash << 11) ^ str.charAt(i) ^ (hash >> 5)));
        }
        return hash;
    }

    /**
     * JAVA自己带的算法
     */
    public static int java(String str) {
        int h = 0;
        int off = 0;
        int len = str.length();
        for (int i = 0; i < len; i++) {
            h = 31 * h + str.charAt(off++);
        }
        return h;
    }

    /**
     * 混合hash算法，输出64位的值
     */
    public static long mixHash(String str) {
        long hash = str.hashCode();
        hash <<= 32;
        hash |= FNVHash1(str.getBytes());
        return hash;
    }
}
