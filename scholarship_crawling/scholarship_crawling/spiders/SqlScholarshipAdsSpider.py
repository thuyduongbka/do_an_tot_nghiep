import scrapy
import json
from ..items import ScholarshipItem
import re
from datetime import datetime
from ..utils_custom import getSchool, transToEng, getListLevel, getCountry, getListMajor

class SqlNguonHocBongSpider(scrapy.Spider):
    name = "sqlscholarshipads"
    startUrls = [
        'https://www.scholarshipsads.com/category/tags/vietnam/',
    ]
    listScholarship = {}
    listImage = {}

    def start_requests(self):
        for url in self.startUrls:
            yield scrapy.Request(url=url, callback=self.getPage)

    def getPage(self, response):
        numOfPages = 20
        linkPageDemo = "https://www.scholarshipsads.com/category/tags/vietnam/"
        for index in range(numOfPages):
            if (index == 0):
                url = linkPageDemo
            else:
                url = linkPageDemo + "page/" + str(index + 1) + "/"
            yield scrapy.Request(url=url, callback=self.getScholarship, dont_filter=True)

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
            yield scrapy.Request(url=urlPost, callback=self.getDetailScholarship)

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

                if major == "All Subjects" : major = "N/A"
                item["major"] = getListMajor(major)

        if 'time' not in locals() or 'money' not in locals() : return
        content = ' '.join(response.xpath("///div[@class='scholarship-item']").getall())
        url = response.url
        item["url"] = url.encode("utf-8")
        item["content"] = content
        item["web"] = 4
        item["newMajor"] = False
        item["urlImage"] = self.listImage[url]
        return item



