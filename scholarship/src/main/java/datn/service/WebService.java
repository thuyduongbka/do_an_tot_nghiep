package datn.service;

import datn.base.BaseService;
import datn.crawler.CrawlerService;
import datn.entity.WebEntity;
import datn.repository.WebRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WebService extends BaseService<WebEntity, WebRepository> {
    private static Logger logger = LoggerFactory.getLogger(WebService.class);
    
    @Autowired
    private CrawlerService crawlerService;
    
    public List<WebEntity> getAll(){
        return repository.findAll();
    }
    
    public void crawler(){
        crawlerService.crawlerApi("1");
    }
    
}
