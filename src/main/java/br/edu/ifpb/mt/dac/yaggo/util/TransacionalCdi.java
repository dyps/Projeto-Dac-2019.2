package br.edu.ifpb.mt.dac.yaggo.util;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.annotation.Priority;
import javax.interceptor.Interceptor;
import javax.interceptor.InterceptorBinding;

@InterceptorBinding
@Priority(Interceptor.Priority.APPLICATION) // https://stackoverflow.com/a/17570943/4023351
@Retention(RetentionPolicy.RUNTIME)
public @interface TransacionalCdi {

}
