import re
from googletrans import Translator

def transToEng(text):
    translator = Translator()
    return translator.translate(text, src='en', dest='vi').text

def getListLevel(listLevel):
    listNew = []
    for level in listLevel:
        listNew.append(getLevel(level))
    return listNew

def getLevel(level):
    levelLower = level.lower()
    if "master" in levelLower or "mba" in levelLower :
        return "Master";
    if "undergraduate" in levelLower or "bachelor" in levelLower \
            or "postsecondary" in levelLower or "freshman" in levelLower:
        return "Bachelor"
    if "graduate" in levelLower or "postgraduate" in levelLower :
        return "Postgraduate"
    if "mphil" in levelLower or "research" in levelLower:
        return "Research"
    if "postdoc" in levelLower or "post doctorate" in levelLower:
        return "Postdoctoral"
    if "doctoral" in levelLower or "phd" in levelLower:
        return "PHD"
    if "diploma" in levelLower or "training" in levelLower or "fellowship" in levelLower \
            or "honors" in levelLower or "honours" in levelLower or "program" in levelLower \
            or "essay" in levelLower:
        return "Fellowship"

    return level.strip()

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

def getListMajor(major):
    import re
    if major == "N/A" or major == "NA": return ["N/A"]
    list = re.split(', | and | or |And ', major)
    re = []
    for m in list:
        re.append(getMajor(m))
    return re

def getMajor(name):
    of = name.rfind("of ")
    if of == -1: return name.strip()
    return name[of + 3:].strip()

def getCountry(name):
    nameLower = name.lower()
    if nameLower == "the uk" or nameLower == "united kingdom":
        return "UK"
    if "united states" in nameLower or "usa" in nameLower:
        return "US"
    if "thailand" in nameLower:
        return "Thailand"
    return name.strip()
