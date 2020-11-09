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
        self.create_table_major_scholarship()
        self.create_table_level()
        self.create_table_money()
        self.create_table_country()
        self.create_table_school()
        self.create_table_requirement()

    def create_connection(self):
        self.conn = mysql.connector.connect(
            host='localhost',
            user='root',
            passwd='',
            database='scholarship_db'
        )
        self.curr = self.conn.cursor(buffered=True)

    def restart(self):
        self.curr.close()
        self.curr = self.conn.cursor(buffered=True)

    def create_table_scholarship(self):
        self.curr.execute("""DROP TABLE IF EXISTS scholarship""")
        self.curr.execute("""CREATE TABLE IF NOT EXISTS scholarship(
            id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
            web_id INT NOT NULL,
            country_id INT,
            school_id INT,
            name text,
            time date CHECK (time >= CURDATE()),
            content text,
            url text,
            number_seen int DEFAULT 0,
            number_share int DEFAULT 0,
            number_comment int DEFAULT 0,
            is_expired boolean DEFAULT false,
            created_time timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                    updated_time timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                    updated_by_user_id int(11) DEFAULT NULL,
                    created_by_user_id int(11) DEFAULT NULL,
                    is_deleted bit(1) DEFAULT b'0'  
        )""")

    def create_table_major_scholarship(self):
        self.curr.execute("""DROP TABLE IF EXISTS major_scholarship""")
        self.curr.execute("""CREATE TABLE IF NOT EXISTS major_scholarship(
                    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                    scholarship_id varchar(200) NOT NULL,
                    major_id int ,
                    created_time timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                    updated_time timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                    updated_by_user_id int(11) DEFAULT NULL,
                    created_by_user_id int(11) DEFAULT NULL,
                    is_deleted bit(1) DEFAULT b'0'           
        )""")

    def create_table_level(self):
        self.curr.execute("""DROP TABLE IF EXISTS level""")
        self.curr.execute("""CREATE TABLE IF NOT EXISTS level(
                    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                    scholarship_id varchar(200) NOT NULL,
                    name text,
                    created_time timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                    updated_time timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                    updated_by_user_id int(11) DEFAULT NULL,
                    created_by_user_id int(11) DEFAULT NULL,
                    is_deleted bit(1) DEFAULT b'0'                  
        )""")

    def create_table_money(self):
        self.curr.execute("""DROP TABLE IF EXISTS money""")
        self.curr.execute("""CREATE TABLE IF NOT EXISTS money(
                    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                    scholarship_id varchar(200) NOT NULL,
                    value text,
                    created_time timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                    updated_time timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                    updated_by_user_id int(11) DEFAULT NULL,
                    created_by_user_id int(11) DEFAULT NULL,
                    is_deleted bit(1) DEFAULT b'0'                  
        )""")

    def create_table_requirement(self):
        self.curr.execute("""DROP TABLE IF EXISTS requirement""")
        self.curr.execute("""CREATE TABLE IF NOT EXISTS requirement(
                    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                    scholarship_id varchar(200) NOT NULL,
                    name text,
                    value text,
                    created_time timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                    updated_time timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                    updated_by_user_id int(11) DEFAULT NULL,
                    created_by_user_id int(11) DEFAULT NULL,
                    is_deleted bit(1) DEFAULT b'0'                
        )""")

    def create_table_country(self):
        self.curr.execute("""DROP TABLE IF EXISTS country""")
        self.curr.execute("""CREATE TABLE IF NOT EXISTS country(
                    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                    name varchar(50),
                    area varchar(100),
                    created_time timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                    updated_time timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                    updated_by_user_id int(11) DEFAULT NULL,
                    created_by_user_id int(11) DEFAULT NULL,
                    is_deleted bit(1) DEFAULT b'0'                 
        )""")

    def create_table_school(self):
        self.curr.execute("""DROP TABLE IF EXISTS school""")
        self.curr.execute("""CREATE TABLE IF NOT EXISTS school(
                    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                    name varchar(100),
                    created_time timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                    updated_time timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                    updated_by_user_id int(11) DEFAULT NULL,
                    created_by_user_id int(11) DEFAULT NULL,
                    is_deleted bit(1) DEFAULT b'0'             
        )""")

    def getCountryId(self, country):
        self.curr.execute("SELECT id FROM country WHERE name like %s", ('%' + country + '%',))
        country_id = self.curr.fetchone()
        if (country_id is None):
            self.curr.execute("""INSERT INTO country(name) VALUES (%s)""", (country,))
            self.conn.commit()
            self.restart()
            return self.getCountryId(country)
        return country_id[0]

    def getSchoolId(self, school):
        self.curr.execute("SELECT id FROM school WHERE name like %s", ('%' + school + '%',))
        school_id = self.curr.fetchone()
        if (school_id is None):
            self.curr.execute("""INSERT INTO school(name) VALUES (%s)""", (school,))
            self.conn.commit()
            self.restart()
            return self.getSchoolId(school)
        return school_id[0]

    def store_db(self, item):
        country_id = self.getCountryId(item["country"])
        school_id = None
        if item["school"] is not None:
            school_id = self.getSchoolId(item["school"])

        if (item['newMajor'] == True):
            self.store_db_scholarship_major(item["url"], item["major"]);
            return

        self.curr.execute(
            """Insert into scholarship(name, time, url, web_id, content, country_id, school_id) values (%s, %s, %s, %s, %s, %s, %s)""",
            (
                item['name'],
                item['time'],
                item['url'],
                item['web'],
                item['country'],
                country_id,
                school_id
            ))
        self.conn.commit()
        self.restart()
        self.store_db_attribute(item)

    def store_db_major_scholarship(self, scholarship_id, major):
        self.curr.execute("SELECT id FROM major WHERE name like %s", ('%' + major + '%',))
        major_id = self.curr.fetchone()
        if major_id is None: return
        self.curr.execute("""INSERT INTO major_scholarship(major_id,scholarship_id) VALUES (%s,%s)""", (
            major_id[0],
            scholarship_id
        ))
        self.conn.commit()

    def store_db_attribute(self, item):
        self.curr.execute("SELECT id FROM scholarship WHERE url = %s", (item['url'],))
        scholarship_id = self.curr.fetchone()[0]

        if item['web'] == 2:
            self.store_db_major_scholarship(scholarship_id, item['major'])
        else:
            for major in item['major']:
                self.store_db_major_scholarship(scholarship_id, major)

        for level in item['level']:
            self.curr.execute("""INSERT INTO level(name,scholarship_id) VALUES (%s,%s)""", (
                level,
                scholarship_id,
            ))
            self.conn.commit()
        for money in item['money']:
            self.curr.execute("""INSERT INTO money(value,scholarship_id) VALUES (%s,%s)""", (
                money,
                scholarship_id,
            ))
            self.conn.commit()
        if "request" in item:
            for key in item['request']:
                self.curr.execute("""INSERT INTO requirement(name,value,scholarship_id) VALUES (%s,%s,%s)""", (
                    key,
                    item['request'][key],
                    scholarship_id,
                ))
                self.conn.commit()

    def process_item(self, item, spider):
        # self.translate(item)
        self.store_db(item)
        return item
