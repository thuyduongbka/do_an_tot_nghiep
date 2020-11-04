package datn.job;

import datn.service.CrawlerJobService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class CrawlerJob extends QuartzJobBean {
    
    @Autowired(required = true)
    private CrawlerJobService crawlerJobService;
    
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        crawlerJobService.crawler();
    }
}
