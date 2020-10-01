import scrapy
import json
from ..items import ScholarshipItem
from datetime import datetime

class SqlScholarshipPlanetSpider(scrapy.Spider):
    name = "sqlscholarshipplanet"
    startUrls = [
        'https://scholarshipplanet.info/vi/financial-aid/hoc-bong-toan-phan/',
        'https://scholarshipplanet.info/vi/financial-aid/ban-phan/',
    ]
    listScholarship = []

    def start_requests(self):
        for url in self.startUrls:
            yield scrapy.Request(url=url, callback=self.getPage)

    def getPage(self, response):
        numOfPages = int(response.xpath("//a[@class='page-numbers'][last()]/text()").get())
        linkPageDemo = response.xpath("//a[@class='page-numbers'][last()]/@href").get()[:-8]
        for index in range(numOfPages):
            if (index == 0):
                url = linkPageDemo
            else:
                url = linkPageDemo + "/page/" + str(index + 1) + "/"
            yield scrapy.Request(url=url, callback=self.getScholarship, dont_filter=True)

    def getScholarship(self, response):
        linkPosts = response.xpath("//div[@class='grid-post-item']/div[@class='content']/h2/a/@href").extract()
        for urlPost in linkPosts:
            yield scrapy.Request(url=urlPost, callback=self.getDetailScholarship)

    def getDetailScholarship(self, response):
        name = ", ".join(response.xpath("//h1[@class='page-title']/text()").get().split(",")[:-1])
        major = None
        time = None

        divs = response.xpath("//div[@class='scholar-info']/div")
        for div in divs :
            h4 = div.xpath("./h4/text()").get()
            value = div.xpath("./p/a/text()")
            if h4 == "Quốc gia":
                country = value.get()
                if country is None:
                    country = response.xpath("//h1[@class='page-title']/text()").get().split(",")[-2:-1][0]
            elif h4 == "Ngành":
                major = value.getall()
                if self.checkMajorError(major): return
            elif h4 == "Chuyên Ngành":
                if (major is None):
                    major = value.getall()
                else:
                    major[0] = major[0] + value.get()
            elif h4 == "Yêu cầu IELTS":
                ielts = div.xpath("./p/text()").get()
            elif h4 == "Bậc học":
                level = value.getall();
            elif h4 == "Giá trị học bổng":
                money = value.getall()
            elif h4 == "Deadline":
                time = div.xpath("./p/text()").get()

        if (name is None or major is None): return
        if (time is None):
            time = "05/08/2021"
        else:
            time = time.split("/")
            day = time[0]
            month = time[1]
            year = str(int(time[2]) + 3)
            time = "/".join([day, month, year])

        item = ScholarshipItem()
        item["name"] =  name
        item["country"] = country
        item["major"] = major
        item["level"] = level
        if ielts is not None :
            item["request"] = {
                "ielts" : ielts
            }
        item["money"] = money
        item["time"] = datetime.strptime(time, '%d/%m/%Y').date()
        item["url"] = response.url.encode("utf-8")
        item["web"] = 1

        yield item

    def checkMajorError(self, majors):
        for major in majors:
            if (major == "Các ngành học được liệt kê trong bài" or
                    major == "Các ngành học được đào tạo tại trường" or
                    major == "Các ngành tương ứng có chương trình đạo tạo tại trường" or
                    major == "Các ngành được đào tạo tại các Khoa được tài trợ học bổng" or
                    major == "Các ngành được đào tạo tại tổ chức được tài trợ học bổng" or
                    major == "Các ngành được đào tạo tại trường" or
                    major == "Những chương trình học được hỗ trợ học bổng (xem danh sách của trường)" or
                    major == "Các ngành được đề cập trong bài"):
                return True
        return False



