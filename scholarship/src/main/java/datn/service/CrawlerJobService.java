package datn.service;

import datn.crawler.CrawlerService;
import datn.entity.WebEntity;
import datn.enums.TypeTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class CrawlerJobService {
    
    @Autowired
    private CrawlerService crawlerService;
    
    @Autowired
    private WebService webService;
    
    public void crawler(){
        List<WebEntity> listWeb = webService.getAll();
        for (WebEntity web : listWeb){
            if (checkTimeCrawlBeforeNow(web)){
                crawlerService.crawlerApi(web.getUrl());
            }
        }
    }
    
    public static void main(String[] args) {
        WebEntity web = new WebEntity();
        web.setTime(1);
        web.setTypeTime(TypeTime.DAY);
        web.setLastCrawled(new Date());
        System.out.println(checkTimeCrawlBeforeNow(web));
    }
    
    private static boolean checkTimeCrawlBeforeNow(WebEntity web){
        Date current = new Date();
        Date lastCrawled = web.getLastCrawled();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(lastCrawled);
        
        int time = web.getTime();
        TypeTime typeTime = web.getTypeTime();
        
        switch (typeTime){
            case DAY:
                calendar.add(Calendar.DATE,time);
                break;
            case MONTH:
                calendar.add(Calendar.MONTH,time);
                break;
        }
        Date timeCrawl = calendar.getTime();
        return timeCrawl.before(current);
    }
}
