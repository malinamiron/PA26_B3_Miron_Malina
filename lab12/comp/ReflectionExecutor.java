package org.example;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

public class ReflectionExecutor {

    public static void main(String[] args) {
        String className = "org.example.ExClass";

        try {
            Class<?> clazz = Class.forName(className);

            Method runMethod = clazz.getMethod("run");

            Constructor<?> constructor = clazz.getConstructor();
            Object instance = constructor.newInstance();

            runMethod.invoke(instance);

        } catch (ClassNotFoundException e) {
            System.err.println("Error: The class " + className + " could not be found on the classpath.");
        } catch (NoSuchMethodException e) {
            System.err.println("Error: A no-argument 'run()' method does not exist in this class.");
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            System.err.println("Error failed to instantiate or invoke the method: " + e.getMessage());
            if (e.getCause() != null) {
                System.err.println("Caused by: " + e.getCause());
            }
        }
    }
}