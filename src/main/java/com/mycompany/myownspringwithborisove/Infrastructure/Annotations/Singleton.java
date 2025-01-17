/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/AnnotationType.java to edit this template
 */
package com.mycompany.myownspringwithborisove.Infrastructure.Annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.Exchanger;

/**
 *
 * @author Maksim
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Singleton {
    
    LoadingPolicy loadingPolicy() default LoadingPolicy.LAZY;
    
    public static enum LoadingPolicy{
        LAZY, EAGER;
    }
    
}
