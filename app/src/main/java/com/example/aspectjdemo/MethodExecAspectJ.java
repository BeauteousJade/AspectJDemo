package com.example.aspectjdemo;

import android.util.Log;

import androidx.core.os.TraceCompat;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class MethodExecAspectJ {

    @Pointcut("execution (* com.example.aspectjdemo.MainActivity.on**(..))")
    public void pointCut() {

    }

    public void onMethodExec(ProceedingJoinPoint joinPoint) {
        long time = -System.currentTimeMillis();
        try {
            joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        time += System.currentTimeMillis();
        String s = joinPoint.getSignature().toString();
        Log.i("pby123", "time = " + time + " string = " + s);
    }

    @Before("pointCut()")
    public void before(JoinPoint joinPoint) {
        TraceCompat.beginSection(joinPoint.getSignature().toString());
    }

    @After("pointCut()")
    public void after() {
        TraceCompat.endSection();
    }
}