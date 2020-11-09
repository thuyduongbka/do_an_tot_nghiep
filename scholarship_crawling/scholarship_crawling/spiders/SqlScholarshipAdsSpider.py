import scrapy
import json
from ..items import ScholarshipItem
import re
from datetime import datetime
from ..utils_custom import getSchool


class SqlNguonHocBongSpider(scrapy.Spider):
    name = "sqlscholarshipads"
    startUrls = [
        'https://www.scholarshipsads.com/category/tags/vietnam/',
    ]
    listScholarship = {}

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
        listPosts = response.xpath("//div[@class='card-warp']")
        print(listPosts)
        for post in listPosts:
            urlPost = post.xpath("./div[@class='card-img']").get()
            print(urlPost)
            # yield scrapy.Request(url=urlPost, callback=self.getDetailScholarship)    #add content

    def getDetailScholarship(self, response):
        name = response.xpath("//div[@class='title-heading']/text()")
        print(name)
        return
        item = ScholarshipItem()
        item["name"] = name
        item["school"] = school
        item["country"] = country
        item["major"] = scholarship["major"]
        item["level"] = scholarship["level"]
        item["money"] = money
        item["time"] = datetime.strptime(time, '%d/%m/%Y').date()
        item["url"] = response.url.encode("utf-8")
        item["web"] = 3
        item["newMajor"] = False
        item["urlImage"] = scholarship["urlImage"]
        return item

    def getMoney(self,txt):
        result = []
        x1 = re.findall("[0-9]*\,*[0-9]+.?[\$|\£|\€|\¥]", txt)
        x4 = re.findall("[HK|US]*[\$|\£|\€|\¥|\₫][0-9]*\,*[0-9]+", txt)
        x2 = re.findall("toàn phần|bán phần", txt.lower())
        x3 = re.findall("[0-9]+\%", txt)
        x5 = re.findall("[0-9]*\,*[0-9]+.[bảng|eur]+", txt.lower())

        if len(x2) > 0:
            x2 = set(x2)
            result.extend(x2)
            return result
        if len(x3) > 0:
            x3 = set(x3)
            result.extend(x3)
            return result
        if len(x1) > 0 or len(x4) > 0 or len(x5) > 0:
            return ['bán phần']

        return result;

    def getCountry(self, country):
        if country is None or country.lower() == "khác":
            return None
        return country.replace("Học bổng du học", "").strip()
    def getTime(self, time):
        if (time is None):
            time = "05/08/2021"
        else:
            time = time.split("/")
            day = time[0]
            month = time[1]
            year = str(int(time[2]) + 3)
            time = "/".join([day, month, year])
        return time
    def getSchool(self, response):
        return getSchool(response)


