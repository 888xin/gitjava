package simhash;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 获得所有行数的文档名称和指纹，存到一个大集合里面
 * Created by xin on 14-4-13.
 */
public class DocSimHashCode {
    public static List<Map<String,String>> getSimHashCode(InputStream inputStream){
        List<Map<String,String>> list = new ArrayList<Map<String, String>>();
        Map<String,String> map = null ;
        try {
            InputStreamReader inreader = new InputStreamReader(inputStream,"UTF-8");
            BufferedReader br = new BufferedReader(inreader);
            String line = null ;
            while((line = br.readLine()) != null){
                int m = line.trim().length();
                map = new HashMap<String, String>();
                String[] words = line.split(" ");
                if (words.length == 2){
                    map.put("hashcode",words[0]);
                    map.put("textString",words[1]);
                    list.add(map);
                }
            }
            if (br != null){
                br.close();
            }
            if (inreader != null){
                inreader.close();
            }
            if (inputStream != null){
                inputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list ;
    }
}
