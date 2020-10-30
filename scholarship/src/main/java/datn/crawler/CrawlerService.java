package datn.crawler;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

@Service
public class CrawlerService {
    @Value("${application.crawler-domain}")
    private String crawlerDomain;
    
    private static final String TEST_API = "/";
    private static final String CRAWLER_API = "/crawl";
    
    private String getUrl(String path) {
        return this.crawlerDomain + path;
    }
    
    public String testApi(){
        CrawlerRequest<String> request = new CrawlerRequest<>(getUrl(TEST_API), HttpMethod.GET, String.class);
        request.addParam("q","1");
        return request.send();
    }
    
    public String crawlerApi(String url){
        CrawlerRequest<String> request = new CrawlerRequest<>(getUrl(CRAWLER_API), HttpMethod.GET, String.class);
        request.addParam("url",url);
        return request.send();
    }
    
    
    
    
}
