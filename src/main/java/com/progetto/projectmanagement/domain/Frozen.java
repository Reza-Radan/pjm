package com.progetto.projectmanagement.domain;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(value=FIELD)
@Retention(value=RUNTIME)
public @interface Frozen {
}
