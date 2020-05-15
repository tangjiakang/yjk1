package com.tjk.apps.cms.comment;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//标注这个类它可以标注的位置
@Target({ElementType.METHOD, ElementType.TYPE})
//标注这个注解的注解保留时期
@Retention(RetentionPolicy.RUNTIME)
//是否生成注解文档
@Documented
public @interface Invocation {
 String value();
}

