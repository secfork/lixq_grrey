package com.sunwayland.core.testsupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * TestSupport : Spring测试支持,用于测试由Spring 管理的bean,编写测试类时,继承该类
 *
 * @author StarZou
 * @since 2014年5月18日 下午2:28:58
 */
 
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:application.xml" 
//									,"classpath*:application-shiro.xml"
		})
public class ContextTestSupport  {
    protected long startTime;
    protected long endTime;
    
    protected final Log logger = LogFactory.getLog(getClass());

    /**
     * 记录 开始运行时间
     *
     * @return
     */
    protected long start() {
        this.startTime = System.currentTimeMillis();
        return startTime;
    }

    /**
     * 记录 结束运行时间
     *
     * @return
     */
    protected long end() {
        this.endTime = System.currentTimeMillis();
        this.log();
        return endTime;
    }

    /**
     * 输出记录
     */
    protected void log() {
        String text = "\n开始时间 : " + this.startTime + "\n结束时间 : " + this.endTime + "\n执行时间 : " + (this.endTime - this.startTime);
        logger.info(text);
    }
}