import scrapy
import json
import requests
from scrapy.http import FormRequest


class SanHocBongSpider(scrapy.Spider):
    name = "sanhocbong"
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
                    method = "POST",
                    body = formdata,
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
            if (url in self.listScholarship):
                self.listScholarship[url]["major"].append(self.listCategory[categoryUrl])
            else:
                self.listScholarship[url] = {
                    'name' : post.xpath("./h3/text()").get(),
                    "country" : post.xpath("./div/p[1]/text()").get().strip(),
                    "level" : post.xpath("./div/p[2]/text()").get().split(","),
                    "money" : post.xpath("./div/p[3]/text()").get(),
                    "time" : post.xpath("./div/p[4]/text()").get(),
                    "major" : [self.listCategory[categoryUrl]],
                    "url" : url
                }            
            self.saveJson(self.listScholarship)
    
    def getDetailScholarship(self, response):  
        return

    def saveJson(self, list):        
        with open('sanhocbong.json', 'w', encoding='utf-8') as jsonFile:
            jsonFile.write(json.dumps(list, ensure_ascii=False))    