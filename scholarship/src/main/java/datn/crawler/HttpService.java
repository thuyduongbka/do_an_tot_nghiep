package datn.crawler;

import datn.custom.dto.ConversationDto;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class HttpService {
    @Value("${application.crawler-domain}")
    private String crawlerDomain;
    
    private static final String TEST_API = "/";
    private static final String CRAWLER_API = "/crawl";

    private static final String GET_RECOMMEND = "/get-recommend";
    private static final String GET_RECOMMEND_CONVERSATION = "/get-recommend-conversation";
    
    private String getUrl(String path) {
        return this.crawlerDomain + path;
    }
    
    public String testApi(){
        HttpRequest<String> request = new HttpRequest<>(getUrl(TEST_API), HttpMethod.GET, String.class);
        request.addParam("q","1");
        return request.send();
    }
    
    public String crawlerApi(String url){
        HttpRequest<String> request = new HttpRequest<>(getUrl(CRAWLER_API), HttpMethod.GET, String.class);
        request.addParam("url", url);
        return request.send();
    }

    public List<Integer> getRecommend(Long userId) {
        HttpRequest<List> request = new HttpRequest<>(getUrl(GET_RECOMMEND), HttpMethod.GET, List.class);
        request.addParam("userId", String.valueOf(userId));
        return request.send();
    }

    public List<Integer> getRecommend(Long userId, Long scholarshipId) {
        HttpRequest<List> request = new HttpRequest<>(getUrl(GET_RECOMMEND), HttpMethod.GET, List.class);
        request.addParam("userId", String.valueOf(userId));
        request.addParam("scholarshipId", String.valueOf(scholarshipId));
        return request.send();
    }

    public List<Integer> getRecommendConversation(Long userId, ConversationDto dto) {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        HttpRequest<List> request = new HttpRequest<>(getUrl(GET_RECOMMEND_CONVERSATION), HttpMethod.GET, List.class);
        request.addParam("userId", String.valueOf(userId));
        request.addParam("scholarshipId", String.valueOf(dto.getScholarshipId()));
        request.addParam("countryDislike", String.valueOf(dto.getCountryDislike()));
        request.addParam("countryLikeId", String.valueOf(dto.getCountryLikeId()));
        request.addParam("schoolDislike", String.valueOf(dto.getSchoolDislike()));
        request.addParam("schoolLikeId", String.valueOf(dto.getSchoolLikeId()));
        request.addParam("appropriateTime", simpleDateFormat.format(dto.getAppropriateTime()));
        request.addParam("listLevelLike", StringUtils.join(dto.getListLevelLike(), ','));
        request.addParam("listLevelDislike", StringUtils.join(dto.getListLevelDislike(), ','));
        request.addParam("listMajorLike", StringUtils.join(dto.getListMajorLike(), ','));
        request.addParam("listMajorDislike", StringUtils.join(dto.getListMajorDislike(), ','));
        return request.send();
    }


}
