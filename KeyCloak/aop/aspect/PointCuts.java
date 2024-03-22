package com.keti.iam.idthub.aop.aspect;

import org.aspectj.lang.annotation.Pointcut;

/**
 * Aspect 포인트 컷
 */
public class PointCuts {

        @Pointcut("execution(* com.keti.iam.idthub.service.user..*(..))")
        public void userService(){}

        @Pointcut("userService() && @annotation(com.keti.iam.idthub.aop.annotations.Timer)")
        public void allUserService(){}

}
