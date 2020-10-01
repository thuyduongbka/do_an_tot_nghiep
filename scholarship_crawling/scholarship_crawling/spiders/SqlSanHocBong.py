import scrapy
import json
from ..items import ScholarshipItem
from datetime import datetime


class SqlSanHocBongSpider(scrapy.Spider):
    name = "sqlsanhocbong"
    startUrl = 'https://sanhocbong.net/'
    api = "https://sanhocbong.net/aj/News/GetScholarship"
    listScholarship = {}
    listCategory = {}

    def start_requests(self):
        yield scrapy.Request(url=self.startUrl, callback=self.getCategory)

    def getCategory(self, response):
        listCategory = response.xpath("//div[@class='majors']/a")
        for category in listCategory:
            link = response.url + category.xpath("./@href").get()
            self.listCategory[link] = category.xpath("./text()").get()
            yield scrapy.Request(url=link, callback=self.getPage)

    def getPage(self, response):
        group = int(response.url.split("-")[-1])
        numOfPages = response.xpath("//div[@class='paging']/a[last()]/@onclick").get()
        if numOfPages is not None:
            numOfPages = int(numOfPages.split("\"")[1])
            for page in range(numOfPages):
                formdata = json.dumps({'group': group, 'page': page + 1})
                yield scrapy.FormRequest(self.api,
                                         method="POST",
                                         body=formdata,
                                         headers={'content-type': 'application/json; charset=UTF-8'},
                                         callback=self.getScholarship,
                                         dont_filter=True)
        else:
            self.getScholarship(response)

    def getScholarship(self, response):
        if (response.url == self.api):
            categoryUrl = response.request.headers['Referer'].decode("utf-8")
        else:
            categoryUrl = response.url

        listPosts = response.xpath("//a[@class='news']")
        for post in listPosts:
            url = self.startUrl + post.xpath("./@href").get()
            name = post.xpath("./h3/text()").get()
            listFiled = post.xpath("./div/p")
            for p in listFiled:
                time = None
                pName = p.xpath("./i/text()").get()
                pValue = p.xpath("./text()").get().strip()
                if pName == "Quốc gia:":
                    country = pValue
                if pName == "Bậc học:":
                    level = pValue.split(",")
                if pName == "Học bổng:":
                    money = pValue
                if pName == "Thời hạn:":
                    time = pValue
            if money is None or 'level' not in locals(): return

            major = self.listCategory[categoryUrl]

            item = ScholarshipItem()
            item["name"] = name
            item["country"] = country
            item["major"] = major
            item["level"] = level
            item["money"] = [self.getMoney(money)]
            item["time"] = datetime.strptime(self.getTime(time), '%d/%m/%Y').date()
            item["url"] = url
            item["web"] = 2
            return item


    def getTime(self, time):
        if (time is None):
            time = "05/08/2021"
        else:
            time = time.strip().split("/")
            day = time[0]
            month = time[1]
            year = int(time[2]) + 5
            if year > 2023: year = 2021
            time = "/".join([day, month, str(year)])
        return time

    def getMoney(self, money):
        if money is None:
            return None
        money = money.replace("Học bổng", "")
        return money