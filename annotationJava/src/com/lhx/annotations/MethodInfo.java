package com.lhx.annotations;

import java.lang.annotation.*;

/**
 * Created by lhx on 15-1-14 下午2:04
 *
 * @project gitjava
 * @package com.lhx.annotations
 * @Description @Documented – 表示使用该注解的元素应被javadoc或类似工具文档化，它应用于类型声明，类型声明的注解会影响客户端对注解元素的使用。如果一个类型声明添加了Documented注解，那么它的注解会成为被注解元素的公共API的一部分。
 * @blog http://blog.csdn.net/u011439289
 * @email 888xin@sina.com
 * @github https://github.com/888xin
 */
@Documented
@Target(ElementType.METHOD)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface MethodInfo {
    String author() default "Pankaj" ;
    String date();
    int revision() default 1 ;
    String comments();
}
