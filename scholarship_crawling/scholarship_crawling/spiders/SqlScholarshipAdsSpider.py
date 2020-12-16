import scrapy
import json
from ..items import ScholarshipItem
import re
from datetime import datetime
from ..utils_custom import getSchool, transToEng, getListLevel, getCountry, getListMajor

class SqlScholarshipAdsSpider(scrapy.Spider):
    name = "sqlscholarshipads"
    startUrls = [
        'https://www.scholarshipsads.com/category/tags/vietnam/',
    ]
    listScholarship = {}
    listImage = {}
    headers = {
        'Cookie' : 'SPSI=69f2d085a1870ed99d933f5665504c56; PHPSESSID=8kkholpr7veq3sm27qlt2i4qes; _fbp=fb.1.1607591880183.1505267378; 2z3u_unique_user=1; __gads=ID=514128484ab0cacc-226a408f21c50085:T=1607591879:RT=1607591879:S=ALNI_MZgEln4SBvnLjyep8rd-oxRy8ewYA; SPSE=YLZXmZ5jBMgtWA3g9K4djloZ+OIKCu50y3c1E6Swe20gKfnMIlHPK8bzeyF7weP82Qt+CGiGcj7hN+6r0jkrzw==; sp_lit=+ddKE0Z+R5VHXtvv1NE7pw==; spcsrf=b49698531cefc80b331aaf8cbf9d88aa; sbtsck=javE6WUH/QCukH4+OYIvxhKmBkQqFewr73dvJEHIHGb1Sk=; PRLST=NL; UTGv2=h4fc6b45bdbb3be6de3955e59d7dd06e8458; adOtr=dX9Y65fa881',
        'Accept' : '*/*',
        'Connection': 'keep-alive',
        'User-Agent' : 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36 Edg/87.0.664.57'
    }

    def start_requests(self):
        for url in self.startUrls:
            yield scrapy.Request(url=url,
                                 callback=self.getPage,
                                 headers={
                                    'Cookie': 'SPSI=69f2d085a1870ed99d933f5665504c56; PHPSESSID=8kkholpr7veq3sm27qlt2i4qes; _fbp=fb.1.1607591880183.1505267378; 2z3u_unique_user=1; __gads=ID=514128484ab0cacc-226a408f21c50085:T=1607591879:RT=1607591879:S=ALNI_MZgEln4SBvnLjyep8rd-oxRy8ewYA; SPSE=YLZXmZ5jBMgtWA3g9K4djloZ+OIKCu50y3c1E6Swe20gKfnMIlHPK8bzeyF7weP82Qt+CGiGcj7hN+6r0jkrzw==; sp_lit=+ddKE0Z+R5VHXtvv1NE7pw==; spcsrf=b49698531cefc80b331aaf8cbf9d88aa; sbtsck=javE6WUH/QCukH4+OYIvxhKmBkQqFewr73dvJEHIHGb1Sk=; PRLST=NL; UTGv2=h4fc6b45bdbb3be6de3955e59d7dd06e8458; adOtr=dX9Y65fa881',
                                    'User-Agent' : 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36 Edg/87.0.664.57'
                                 })

    def getPage(self, response):
        numOfPages = 20
        linkPageDemo = "https://www.scholarshipsads.com/category/tags/vietnam/"
        for index in range(numOfPages):
            if (index == 0):
                url = linkPageDemo
            else:
                url = linkPageDemo + "page/" + str(index + 1) + "/"
            yield scrapy.Request(url=url, callback=self.getScholarship, dont_filter=True, headers=self.headers)

    def getScholarship(self, response):
        listPosts = response.xpath("//div[@class='scholarship-card']")

        #test
        #yield scrapy.Request(url="https://www.scholarshipsads.com/uwa-winthrop-scholarship-australia/", callback=self.getDetailScholarship)
        #return
        ##

        for post in listPosts:
            urlImage = post.xpath("./div[@class='card-warp']/div[contains(@class, 'card-img')]/img/@src").get()
            urlPost = post.xpath("./div[@class='card-more']/a/@href").get()
            self.listImage[urlPost] = urlImage;
            yield scrapy.Request(url=urlPost, callback=self.getDetailScholarship, headers=self.headers)

    def getDetailScholarship(self, response):
        item = ScholarshipItem()
        item["name"] = response.xpath("//div[@class='title-heading']/h1/text()").get()
        listLi = response.xpath("//div[contains(@class, 'card-info')]/ul/li")
        for li in listLi:
            liName = li.xpath("./i/@class").get()
            liValue = li.xpath("./text()").getall()[1].strip()
            if liValue is None: return

            if (liName == 'icon-dollor'):
                if (liValue == 'Fully Funded' or liValue == 'Full tuition fee'):
                    money = 'Fully Funded'
                else:
                    money = 'Partial Funding'
                item["money"] = [money]

            if (liName == 'icon-place'):
                if (',' in liValue or liValue == '') : return
                item["school"] = liValue

            if (liName == 'icon-Bachelor2'):
                item["level"] = getListLevel(liValue.split(", "))

            if (liName == 'icon-map'):
                item["country"] = getCountry(liValue)

            if (liName == 'icon-calendar'):
                time = liValue
                item["time"] = datetime.strptime(time, '%m/%d/%Y').date()

            if (liName == 'icon-book'):
                liMore = li.xpath("./span/text()").get()
                if liMore is not None:
                    major = liValue + liMore
                else:
                    major = liValue;

                if "all subjects" in major.lower(): major = "All Subjects"
                item["major"] = getListMajor(major)

        if 'time' not in locals() or 'money' not in locals() : return
        content = ' '.join(response.xpath("///div[@class='scholarship-item']").getall())
        url = response.url
        item["url"] = url.encode("utf-8")
        item["applyLink"] = url.encode("utf-8")
        item["content"] = content
        item["web"] = 4
        item["newMajor"] = False
        item["urlImage"] = self.listImage[url]
        return item



