package com.keti.iam.idthub.aop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.util.StopWatch;

@Aspect
@Slf4j
public class AspectJAdvisors {

    /**
     * 타이머 체크용
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("com.keti.iam.idthub.aop.aspect.PointCuts.allUserService()")
    public Object userTimerTransaction(ProceedingJoinPoint joinPoint) throws Throwable {
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            log.info(" Logic Start : [ {} ] " , joinPoint.getSignature().getName());
            Object proceed = joinPoint.proceed();
            stopWatch.stop();
            log.info(" Logic End : [ {} ]  " , joinPoint.getSignature().getName());
            log.info(" Logic End Time : [ {} ]  "  ,  stopWatch.getTotalTimeSeconds());
            return proceed;
    }

    @AfterThrowing(value = "execution(* *..*Service*.*(..))" ,throwing = "exception")
    public void throwsException(JoinPoint joinPoint , Exception exception) {
        log.info("Exception Location = [ {} ]" , joinPoint.getSignature() );
        log.info("Exception Name = [ {} ]" , exception.getClass().getSimpleName() );
        log.info("Exception Message = [ {} ]" , exception.getMessage() );
    }
}
