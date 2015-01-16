package com.lhx.annotations;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lhx on 15-1-14 下午2:12
 *
 * @project gitjava
 * @package com.lhx.annotations
 * @Description
 * @blog http://blog.csdn.net/u011439289
 * @email 888xin@sina.com
 * @github https://github.com/888xin
 */
public class AnnotationExample {

    @Override
    @MethodInfo(author = "Pankaj", comments = "Main method", date = "Nov 12 2012", revision = 1)
    public String toString() {
        return "Overriden toString method" ;
    }

    @Deprecated
    @MethodInfo(comments = "deprecated method", date = "Nov 12 2012")
    public static void oldMethod(){
        System.out.println("old method, don't use it");
    }

    @SuppressWarnings({"unchecked","deprecation"})
    @MethodInfo(author = "Pankaj", comments = "Main method", date = "Nov 17 2012", revision = 10)
    public static void genericsTest() throws FileNotFoundException{
        List l = new ArrayList();
        l.add("abc");
        oldMethod();
    }
}
