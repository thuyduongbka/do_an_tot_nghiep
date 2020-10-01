import scrapy
import json
import codecs


class NguonHocBongSpider(scrapy.Spider):
    name = "nguonhocbong"
    startUrls = [
            'https://nguonhocbong.com/scholarships/',
    ]
    listScholarship = []
    result = []

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
            scholarship = {
                "name": post.xpath("./div/h4/a/text()").get(),            
                "country" : post.xpath("./div/div[1]/small/a/text()").get(),
                "major" : post.xpath("./div/div[3]/small/a/text()").getall(),
                "level" : post.xpath("./div/div[2]/small/a/text()").get(),
                "time" : post.xpath("./div/div[4]/small/time/text()").get(),
                "url": urlPost
            }        
            self.listScholarship.append(scholarship)  
            # yield scrapy.Request(url=urlPost, callback=self.getDetailScholarship)    #add content

    def getDetailScholarship(self, response):
        content = response.xpath("//div[@class='entry-content']/*[position() < last()-5]").getall()
        url = response.url
        for scholar in self.listScholarship:
            if (scholar['url'] == url):
                scholar['content'] = ''.join(content)
                self.result.append(scholar)
                self.saveJson(self.result)
                return
                   

    def saveJson(self, list):        
        with open('nguonhocbong.json', 'w', encoding='utf-8') as jsonFile:
            jsonFile.write(json.dumps(list, ensure_ascii=False))    