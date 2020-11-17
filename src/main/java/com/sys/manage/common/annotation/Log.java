package com.sys.manage.common.annotation;


import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by LY on 2019/4/09.
 */
@Documented
@Retention(RUNTIME)
@Target(METHOD)
public @interface Log {
}
