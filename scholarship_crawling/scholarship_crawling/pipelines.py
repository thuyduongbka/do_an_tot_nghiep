# -*- coding: utf-8 -*-

# Define your item pipelines here
#
# Don't forget to add your pipeline to the ITEM_PIPELINES setting
# See: https://docs.scrapy.org/en/latest/topics/item-pipeline.html

import mysql.connector

class ScholarshipCrawlingPipeline(object):
    duplicate = False

    def __init__(self):
        self.create_connection()
        self.create_table_scholarship()
        self.create_table_scholarship_major()
        self.create_table_level()
        self.create_table_money()
        self.create_table_country()
        self.create_table_requirement()

    def create_connection(self):
        self.conn = mysql.connector.connect(
            host = 'localhost',
            user = 'root',
            passwd = '',
            database = 'scholarship_db'
        )
        self.curr = self.conn.cursor(buffered=True)

    def create_table_scholarship(self):
        self.curr.execute("""DROP TABLE IF EXISTS scholarship""")
        self.curr.execute("""CREATE TABLE IF NOT EXISTS scholarship(
            id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
            web_id INT NOT NULL,
            name text,
            time date CHECK (time >= CURDATE()),
            country text,
            school text,
            url varchar(200) UNIQUE,
            number_seen int,
            number_share int,
            number_comment int,
            is_expired boolean DEFAULT false
        )""")

    def create_table_scholarship_major(self):
        self.curr.execute("""DROP TABLE IF EXISTS scholarship_major""")
        self.curr.execute("""CREATE TABLE IF NOT EXISTS scholarship_major(
                    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                    scholarship_url varchar(200) NOT NULL,
                    major_id int          
        )""")

    def create_table_level(self):
        self.curr.execute("""DROP TABLE IF EXISTS level""")
        self.curr.execute("""CREATE TABLE IF NOT EXISTS level(
                    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                    scholarship_url varchar(200) NOT NULL,
                    name text                
        )""")

    def create_table_money(self):
        self.curr.execute("""DROP TABLE IF EXISTS money""")
        self.curr.execute("""CREATE TABLE IF NOT EXISTS money(
                    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                    scholarship_url varchar(200) NOT NULL,
                    name text                
        )""")

    def create_table_requirement(self):
        self.curr.execute("""DROP TABLE IF EXISTS requirement""")
        self.curr.execute("""CREATE TABLE IF NOT EXISTS requirement(
                    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                    scholarship_url varchar(200) NOT NULL,
                    name text,
                    value text              
        )""")

    def create_table_country(self):
        self.curr.execute("""DROP TABLE IF EXISTS country""")
        self.curr.execute("""CREATE TABLE IF NOT EXISTS country(
                    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                    scholarship_url varchar(200) NOT NULL,
                    name varchar(50),
                    area varchar(100)               
        )""")

    def store_db(self, item):
        if (item['newMajor'] == True):
            self.store_db_scholarship_major(item["url"],item["major"]);
            return
        self.curr.execute("""Insert into scholarship(name, time, country, url, web_id, school) values (%s, %s, %s, %s, %s, %s)""", (
            item['name'],
            item['time'],
            item['country'],
            item['url'],
            item['web'],
            item['school'],
        ))
        self.conn.commit()
        self.store_db_attribute(item)

    def store_db_scholarship_major(self, url, major):
        self.curr.execute("SELECT id FROM major WHERE name like %s", ('%' + major + '%',))
        major_id = self.curr.fetchone()
        if major_id is None: return
        self.curr.execute("""INSERT INTO scholarship_major(major_id,scholarship_url) VALUES (%s,%s)""", (
            major_id[0],
            url
        ))
        self.conn.commit()

    def store_db_attribute(self, item):
        if item['web'] == 2:
            self.store_db_scholarship_major(item['url'], item['major'])
        else:
            for major in item['major']:
                self.store_db_scholarship_major(item['url'], major)
        for level in item['level']:
            self.curr.execute("""INSERT INTO level(name,scholarship_url) VALUES (%s,%s)""", (
                level,
                item['url'],
            ))
            self.conn.commit()
        for money in item['money']:
            self.curr.execute("""INSERT INTO money(name,scholarship_url) VALUES (%s,%s)""", (
                money,
                item['url'],
            ))
            self.conn.commit()
        if "request" in item:
            for key in item['request']:
                self.curr.execute("""INSERT INTO requirement(name,value,scholarship_url) VALUES (%s,%s,%s)""", (
                    key,
                    item['request'][key],
                    item['url'],
                ))
                self.conn.commit()


    def process_item(self, item, spider):
        self.translate(item)
        self.store_db(item)
        return item
