package org.leafbook.api.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * 遗留问题
 */
@Target({ElementType.ANNOTATION_TYPE,ElementType.METHOD,ElementType.FIELD})
public @interface RemainingProblem {
}
