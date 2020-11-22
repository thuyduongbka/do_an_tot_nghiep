import scrapy
import json
from ..items import ScholarshipItem
import re
from datetime import datetime
from ..utils_custom import getSchool


class SqlNguonHocBongSpider(scrapy.Spider):
    name = "sqlnguonhocbong"
    startUrls = [
        'https://nguonhocbong.com/scholarships/',
    ]
    listScholarship = {}

    def start_requests(self):
        for url in self.startUrls:
            yield scrapy.Request(url=url, callback=self.getPage)

    def getPage(self, response):
        numOfPages = int(response.xpath("//ul[@aria-label='Pagination']/li[last()-1]/a/text()").get())
        linkPageDemo = response.xpath("//ul[@aria-label='Pagination']/li[last()-1]/a/@href").get()[:-8]
        for index in range(numOfPages):
            if (index == 0):
                url = linkPageDemo
            else:
                url = linkPageDemo + "page/" + str(index + 1) + "/"
            yield scrapy.Request(url=url, callback=self.getScholarship, dont_filter=True)

    def getScholarship(self, response):
        listPosts = response.xpath("//article")
        for post in listPosts:
            urlPost = post.xpath("./div/h4/a/@href").get()
            self.listScholarship[urlPost] = {
                "name": post.xpath("./div/h4/a/text()").get(),
                "country": post.xpath("./div/div[1]/small/a/text()").get(),
                "major": post.xpath("./div/div[3]/small/a/text()").getall(),
                "level": post.xpath("./div/div[2]/small/a/text()").getall(),
                "time": post.xpath("./div/div[4]/small/time/text()").get(),
                "urlImage": post.xpath("./a/img/@src").get(),
                "url": urlPost,
            }
            yield scrapy.Request(url=urlPost, callback=self.getDetailScholarship)    #add content

    def getDetailScholarship(self, response):
        url = response.url
        scholarship = self.listScholarship[url]
        name = scholarship["name"]
        country = self.getCountry(scholarship["country"])
        if country is None: return

        money = self.getMoney(name)
        if len(money) == 0:
            content = ' '.join(response.xpath("//div[@class='entry-content']/descendant::*[position() < last()-5]/text()").getall())
            money = self.getMoney(content)
        if len(money) == 0: return

        time = self.getTime(scholarship["time"])
        if time is None: return
        print(time)

        school = self.getSchool(response)

        item = ScholarshipItem()
        item["name"] = name
        item["school"] = school
        item["country"] = country
        item["major"] = scholarship["major"]
        item["level"] = scholarship["level"]
        item["money"] = money
        item["time"] = time
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
            return None
        else:
            time = time.split("/")
            day = int(time[0])
            month = int(time[1])
            year = int(time[2])
            if (year < 2020): return None
            date = datetime(year,12,30);
            if date < datetime.now():
                return None
            return date

    def getSchool(self, response):
        return getSchool(response)


