# -*- coding: utf-8 -*-

# Define here the models for your scraped items
#
# See documentation in:
# https://docs.scrapy.org/en/latest/topics/items.html

import scrapy


class ScholarshipItem(scrapy.Item):
    name = scrapy.Field()
    country = scrapy.Field()
    major = scrapy.Field()
    level = scrapy.Field()
    time = scrapy.Field()
    request = scrapy.Field()
    money = scrapy.Field()
    url = scrapy.Field()
    web = scrapy.Field()
    pass
