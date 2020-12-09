import scrapy
import json
from ..items import ScholarshipItem
import re
from datetime import datetime
from ..utils_custom import getSchool, transToEng, getListLevel, getListMajor, getCountry


class SqlScholarshipPositionSpider(scrapy.Spider):
    name = "sqlscholarshipposition"
    startUrls = [
        'https://scholarship-positions.com/blog/',
    ]
    listScholarship = {}
    listImage = {}

    def start_requests(self):
        for url in self.startUrls:
            yield scrapy.Request(url=url, callback=self.getPage)

    def getPage(self, response):
        numOfPages = 20
        linkPageDemo = "https://scholarship-positions.com/blog/"
        for index in range(numOfPages):
            if (index == 0):
                url = linkPageDemo
            else:
                url = linkPageDemo + "page/" + str(index + 1) + "/"
            yield scrapy.Request(url=url, callback=self.getScholarship, dont_filter=True)

    def getScholarship(self, response):
        listPosts = response.xpath("//article")

        # test
        #yield scrapy.Request(url="https://scholarship-positions.com/kings-college-london-hans-rausing-international-scholarships-in-uk/2020/11/10/", callback=self.getDetailScholarship)
        #return
        ##

        for post in listPosts:
            urlImage = post.xpath("./div[@class='entry-content']/figure/a/img/@src").get()
            urlPost = post.xpath("./header/h1/a/@href").get()
            self.listImage[urlPost] = urlImage;
            yield scrapy.Request(url=urlPost, callback=self.getDetailScholarship)

    def getDetailScholarship(self, response):
        item = ScholarshipItem()
        try:
            item["url"] = response.url;
            item["name"] = response.xpath("//h1[@class='entry-title']/text()").get()
            item["urlImage"] = response.xpath("//figure/img/@src").get()
            time = response.xpath("//p[contains(strong/text(),'Application Deadline')]/text()").get()
            item["time"] = datetime.strptime(time.replace(':', '').strip(), '%B %d, %Y').date()
            school = response.xpath("//li[contains(strong/text(),'University or Organization')]/a/text()").get()
            if school is None:
                school = response.xpath("//li[contains(strong/text(),'University or Organization')]/text()").get()
            item["school"] = school.replace(':', '').strip();

            major = response.xpath("//li[contains(strong/text(),'Departmen')]/text()").get()
            item["major"] = getListMajor(major.replace(':', '').strip())

            level = response.xpath("//li[contains(strong/text(),'Course Level')]/a/text()").get()
            if level is None:
                level = response.xpath("//li[contains(strong/text(),'Course Level')]/a/strong/text()").get()
            if level is None:
                level = response.xpath("//li[contains(strong/text(),'Course Level')]/text()").get()
            item["level"] = getListLevel(re.split(',| and ', level.replace(':', '').strip()))

            money = response.xpath("//li[contains(strong/text(),'Award')]/text()").get()
            item["money"] = money.replace(':', '').strip().split('+');

            country = response.xpath("//li[contains(strong/text(),'The award can be taken in')]/a/strong/text()").get()
            if country is None:
                country = response.xpath("//li[contains(strong/text(),'The award can be taken in')]/a/text()").get()
            if country is None:
                country = response.xpath("//li[contains(strong/text(),'The award can be taken in')]/text()").get()
            item["country"] = getCountry(country.replace(':', '').strip())

            content = response.xpath("//div[@class='entry-content']").get()
            item["content"] = content
            item["web"] = 5
            item["newMajor"] = False
            return item
        except:
            return




