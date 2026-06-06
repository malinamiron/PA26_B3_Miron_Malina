package org.example;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

public class ReflectionExecutor2 {

    private static final int val = 67;

    public static void main(String[] args) {
        String folderPath = "C:/Users/Milika/Desktop/JAVA/lab12_hw/target/classes";

        File folder = new File(folderPath);
        if (!folder.exists() || !folder.isDirectory()) {
            System.err.println("Invalid directory path provided.");
            return;
        }

        try {
            URL[] urls = new URL[]{ folder.toURI().toURL() };

            try (URLClassLoader classLoader = new URLClassLoader(urls, ClassLoader.getSystemClassLoader())) {

                List<Class<?>> allLoadedClasses = new ArrayList<>();
                List<Class<? extends Annotation>> identifiedAnnotations = new ArrayList<>();

                scanAndLoadClasses(folder, "", classLoader, allLoadedClasses, identifiedAnnotations);

                for (Class<? extends Annotation> anno : identifiedAnnotations) {
                    System.out.println("@" + anno.getName());
                }

                for (Class<?> clazz : allLoadedClasses) {
                    if (Modifier.isPublic(clazz.getModifiers()) && !clazz.isAnnotation()) {

                        displayPrototype(clazz);

                        invokeAnnotatedMethods(clazz, identifiedAnnotations);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private static void scanAndLoadClasses(File folder, String currentPackage, URLClassLoader loader,
                                           List<Class<?>> classes, List<Class<? extends Annotation>> annotations) {
        File[] files = folder.listFiles();
        if (files == null) return;

        for (File file : files) {
            if (file.isDirectory()) {
                String subPackage = currentPackage.isEmpty() ? file.getName() : currentPackage + "." + file.getName();
                scanAndLoadClasses(file, subPackage, loader, classes, annotations);
            } else if (file.getName().endsWith(".class")) {
                String className = file.getName().substring(0, file.getName().length() - 6);
                String fullyQualifiedName = currentPackage.isEmpty() ? className : currentPackage + "." + className;

                try {
                    Class<?> clazz = loader.loadClass(fullyQualifiedName);

                    if (clazz.isAnnotation()) {
                        annotations.add((Class<? extends Annotation>) clazz);
                    } else {
                        classes.add(clazz);
                    }
                } catch (ClassNotFoundException e) {
                    System.err.println("Could not load class: " + fullyQualifiedName);
                }
            }
        }
    }

    private static void displayPrototype(Class<?> clazz) {
        System.out.println(Modifier.toString(clazz.getModifiers()) + " " + clazz.getName());

        for (Method method : clazz.getDeclaredMethods()) {
            StringBuilder paramTypes = new StringBuilder();
            for (Class<?> p : method.getParameterTypes()) {
                paramTypes.append(p.getSimpleName()).append(" ");
            }
            System.out.printf("  - %s %s %s(%s)\n",
                    Modifier.toString(method.getModifiers()),
                    method.getReturnType().getSimpleName(),
                    method.getName(),
                    paramTypes.toString().trim().replace(" ", ", "));
        }
    }

    private static void invokeAnnotatedMethods(Class<?> clazz, List<Class<? extends Annotation>> annotations) {
        Object instance = null;

        for (Method method : clazz.getDeclaredMethods()) {
            boolean hasTargetAnnotation = false;
            for (Class<? extends Annotation> anno : annotations) {
                if (method.isAnnotationPresent(anno)) {
                    hasTargetAnnotation = true;
                    break;
                }
            }

            if (hasTargetAnnotation) {
                Class<?>[] paramTypes = method.getParameterTypes();
                boolean isNoArgs = paramTypes.length == 0;
                boolean isSingleInt = paramTypes.length == 1 && (paramTypes[0] == int.class || paramTypes[0] == Integer.class);

                if (isNoArgs || isSingleInt) {
                    try {
                        if (instance == null && !Modifier.isStatic(method.getModifiers())) {
                            Constructor<?> constructor = clazz.getDeclaredConstructor();
                            constructor.setAccessible(true);
                            instance = constructor.newInstance();
                        }

                        method.setAccessible(true);

                        if (isNoArgs) {
                            method.invoke(instance);
                        } else {
                            method.invoke(instance, val);
                        }

                    } catch (NoSuchMethodException e) {
                        System.err.println(" No default constructor available to instantiate " + clazz.getSimpleName());
                    } catch (Exception e) {
                        System.err.println(" Failed to invoke " + method.getName() + ": " + e.getMessage());
                    }
                }
            }
        }
    }
}