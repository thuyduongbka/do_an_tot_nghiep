package datn.service;

import datn.crawler.HttpService;
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
    private HttpService httpService;
    
    @Autowired
    private WebService webService;

    public void crawler(){
        List<WebEntity> listWeb = webService.getAll();
        for (WebEntity web : listWeb){
            if (web.getActive() && checkTimeCrawlBeforeNow(web)){
                httpService.crawlerApi(web.getUrl());
            }
        }
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
