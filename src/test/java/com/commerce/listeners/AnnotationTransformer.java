package com.commerce.listeners;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

 public class AnnotationTransformer implements IAnnotationTransformer {
    @Override       // Este método se ejecuta antes de ejecutar cada método de prueba
    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {        //Dentro del método transform, se llama al método setRetryAnalyzer
        annotation.setRetryAnalyzer(Retry.class);       // Establece la clase Retry como el analizador de reintentos (RetryAnalyzer) para la anotación de prueba
    }
}
