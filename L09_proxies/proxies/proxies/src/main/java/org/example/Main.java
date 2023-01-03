package org.example;

import org.example.proxies.CacheableInvocationHandler;
import org.example.proxies.StudentService;
import org.example.proxies.StudentServiceIfc;

import java.lang.reflect.Proxy;

public class Main {
    public static void main(String[] args) {
        StudentServiceIfc studentServiceIfc = getStudentService();
        System.out.println(studentServiceIfc.getAllStudents());
        System.out.println(studentServiceIfc.getAllStudents());
    }


    private static StudentServiceIfc getStudentService() {
        return (StudentServiceIfc) Proxy.newProxyInstance(
                Main.class.getClassLoader(),
                new Class[]{StudentServiceIfc.class},
                new CacheableInvocationHandler(new StudentService())
        );
    }
}