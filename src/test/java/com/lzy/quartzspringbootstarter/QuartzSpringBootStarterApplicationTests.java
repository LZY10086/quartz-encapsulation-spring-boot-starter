package com.lzy.quartzspringbootstarter;

import com.lzy.quartzspringbootstarter.example.job.HelloJob;
import org.junit.jupiter.api.Test;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

@SpringBootTest
class QuartzSpringBootStarterApplicationTests {

    @Test
    void contextLoads() throws SchedulerException {
        //从工厂创建scheduler实例
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        //开启scheduler
        scheduler.start();
        //定义一个job，并绑定我们的HelloJob
        JobDetail jobDetail = newJob(HelloJob.class)
                .withIdentity("job1", "group1")
                .build();
        //定义一个simple trigger,设置重复次数为10次，周期为2秒
        Trigger trigger = newTrigger()
                .withIdentity("job1", "group1")
                .startNow()
                .withSchedule(simpleSchedule().withIntervalInSeconds(1).withRepeatCount(10)).build();
        //使用scheduler进行job调度
        scheduler.scheduleJob(jobDetail,trigger);
    }

}
