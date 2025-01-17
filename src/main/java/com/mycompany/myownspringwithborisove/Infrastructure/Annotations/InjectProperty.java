package com.mycompany.myownspringwithborisove.Infrastructure.Annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 *
 * @author Maksim
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface InjectProperty {
    
    String value() default "";
    
}
