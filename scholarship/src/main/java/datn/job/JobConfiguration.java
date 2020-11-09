package datn.job;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JobConfiguration {
    
    @Bean
    public JobDetail crawlerJobDetail() {
        return JobBuilder
                .newJob(CrawlerJob.class)
                .withIdentity(JobKey.jobKey("crawler"))
                .storeDurably()
                .build();
    }
    
    @Bean
    public Trigger crawlerJobTrigger() {
        return TriggerBuilder
                .newTrigger()
                .forJob(crawlerJobDetail())
                .withIdentity(TriggerKey.triggerKey("crawler"))
                .withSchedule(CronScheduleBuilder.cronSchedule("0 0/5 * * * ?")) //every 5minute
                .build();
    }
}