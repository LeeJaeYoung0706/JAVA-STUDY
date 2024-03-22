package com.keti.iam.idthub.aop.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.util.StopWatch;


/**
 * SQL 디버깅 용 AOP
 */
@Intercepts({
        // xml 파일 sql 실행 클래스 ,
        // method = query -> select , update -> insert,update,delete ,
        // args = 파라미터
        @Signature(type = Executor.class , method = "query" , args = {
                MappedStatement.class , Object.class , RowBounds.class , ResultHandler.class
        }),
        @Signature(type = Executor.class , method = "update" , args = {
           MappedStatement.class , Object.class
        })
})
@Slf4j
public class MyBatisInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();

        if (args != null && args.length != 0){
            MappedStatement statement = (MappedStatement) args[0];
            String mapperId = statement.getId();
            Object arg = args[1];
            StopWatch stopWatch = new StopWatch();
            log.info(" ======  <  SQL Start  >  ======");
            log.info(" SQL ID : [ {} ] " , mapperId);
            log.info(" SQL Param : [ {} ] " , arg);
            stopWatch.start();
            String sql = statement.getBoundSql(arg)
                            .getSql();
            stopWatch.stop();
            log.info(" SQL query : [ {} ] " , sql);
            log.info(" SQL End Time : [ {} ] " , stopWatch.getTotalTimeSeconds());
            log.info(" ======  <  SQL End  >  ======");
        }
        return invocation.proceed();
    }
}
