import re
from googletrans import Translator
translator = Translator()

def translateText(text):
    language = translator.detect(text)
    if language != 'vi':
        return translator.translate(text, src=language, dest='vi')
    return text

def getSchool(response):
    strongTags = response.xpath("//strong")
    for strong in strongTags:
        if "University or Organization" in strong.get():
            li = strong.xpath("./parent::*")
            return li.xpath("./text()").get().replace("-", " ").strip(":").strip()
    content = ' '.join(response.xpath("//div[@class='entry-content']/descendant::*[position() < last()-5]/text()").getall())
    listRegex = ["(University of(\s[A-Z]{1}[a-z]+)+)",
                 "(([A-Z]{1}[a-z]+\s)+University)",
                 "(Đại Học(\s[A-Z]{1}[a-z]+\-*)+)",
                 "(Đại học(\s[A-Z]{1}[a-z]+\-*)+)",
                 "(ĐH(\s[A-Z]{1}[a-z]+)+)",
                 "(DH(\s[A-Z]{1}[a-z]+)+)",
                 "(([A-Z]{1}[a-z]+\s)+School)",
                 "(([A-Z]{1}[a-z]+\s)+Institute of(\s[A-Z]{1}[a-z]+)+)",
                 "(([A-Z]{1}[a-z]+\s)+Institute for(\s[A-Z]{1}[a-z]+)+)",
                 "(School of(\s[A-Z]{1}[a-z]+)+)",
                 "(Agency for(\s[A-Z]{1}[a-z]+)+)"]
    for regex in listRegex:
        x = re.findall(regex, content)
        if len(x) != 0:
            if x == "The University" : continue
            return x[0][0].replace("-", " ").strip()