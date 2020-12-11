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
        #self.dropTable()
        self.create_common()
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
            database='scholarship_english'
        )
        self.curr = self.conn.cursor(buffered=True)

    def restart(self):
        self.curr.close()
        self.curr = self.conn.cursor(buffered=True)

    def dropTable(self):
        self.curr.execute("""DROP TABLE IF EXISTS scholarship""")
        self.curr.execute("""DROP TABLE IF EXISTS major_scholarship""")
        self.curr.execute("""DROP TABLE IF EXISTS level""")
        self.curr.execute("""DROP TABLE IF EXISTS money""")
        self.curr.execute("""DROP TABLE IF EXISTS requirement""")
        self.curr.execute("""DROP TABLE IF EXISTS country""")
        self.curr.execute("""DROP TABLE IF EXISTS school""")
        self.curr.execute("""DROP TABLE IF EXISTS comment""")
        self.curr.execute("""DROP TABLE IF EXISTS country_favorite""")
        self.curr.execute("""DROP TABLE IF EXISTS school_favorite""")
        self.curr.execute("""DROP TABLE IF EXISTS major_favorite""")
        self.curr.execute("""DROP TABLE IF EXISTS scholarship_interactive""")
        self.curr.execute("""DROP TABLE IF EXISTS major""")

    def create_common(self):
        self.curr.execute("""CREATE TABLE IF NOT EXISTS comment(
                            id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                            scholarship_id int NOT NULL,
                            user_id int ,
                            message text,
                            created_time timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                            updated_time timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                            updated_by_user_id int(11) DEFAULT NULL,
                            created_by_user_id int(11) DEFAULT NULL,
                            is_deleted bit(1) DEFAULT b'0'           
                )""")
        self.curr.execute("""CREATE TABLE IF NOT EXISTS country_favorite(
                                    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                    user_id int NOT NULL,
                                    country_id int NOT NULL ,
                                    is_implicit bit(1) DEFAULT b'0',
                                    created_time timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                                    updated_time timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                                    updated_by_user_id int(11) DEFAULT NULL,
                                    created_by_user_id int(11) DEFAULT NULL,
                                    is_deleted bit(1) DEFAULT b'0'           
                        )""")
        self.curr.execute("""CREATE TABLE IF NOT EXISTS school_favorite(
                                            id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                            user_id int NOT NULL,
                                            school_id int NOT NULL ,
                                            is_implicit bit(1) DEFAULT b'0',
                                            created_time timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                                            updated_time timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                                            updated_by_user_id int(11) DEFAULT NULL,
                                            created_by_user_id int(11) DEFAULT NULL,
                                            is_deleted bit(1) DEFAULT b'0'           
                                )""")
        self.curr.execute("""CREATE TABLE IF NOT EXISTS major_favorite(
                                            id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                            user_id int NOT NULL,
                                            major_id int NOT NULL ,
                                            is_implicit bit(1) DEFAULT b'0',
                                            created_time timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                                            updated_time timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                                            updated_by_user_id int(11) DEFAULT NULL,
                                            created_by_user_id int(11) DEFAULT NULL,
                                            is_deleted bit(1) DEFAULT b'0'           
                                )""")
        self.curr.execute("""CREATE TABLE IF NOT EXISTS scholarship_interactive(
                                            id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                            scholarship_id int NOT NULL,
                                            user_id int NOT NULL ,
                                            is_liked bit(1) DEFAULT b'0',     
                                            rating float(5) NOT NULL DEFAULT '0',
                                            is_in_list_favorite bit(1) DEFAULT b'0',      
                                            number_seen int(11) NOT NULL DEFAULT '0',
                                            number_share int(11) NOT NULL DEFAULT '0',
                                            number_comment int(11) NOT NULL DEFAULT '0',
                                            number_click_contact int(11) NOT NULL DEFAULT '0',
                                            number_compare int(11) NOT NULL DEFAULT '0',
                                            created_time timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                                            updated_time timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                                            updated_by_user_id int(11) DEFAULT NULL,
                                            created_by_user_id int(11) DEFAULT NULL,
                                            is_deleted bit(1) DEFAULT b'0'           
                                )""")
        self.curr.execute("""CREATE TABLE IF NOT EXISTS major(
                            id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                            parent_id int,
                            name varchar(200),
                            child varchar(200) DEFAULT '',
                            note varchar(200) DEFAULT '',
                            created_time timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                            updated_time timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                            updated_by_user_id int(11) DEFAULT NULL,
                            created_by_user_id int(11) DEFAULT NULL,
                            is_deleted bit(1) DEFAULT b'0'           
        )""")

    def create_table_scholarship(self):
        self.curr.execute("""CREATE TABLE IF NOT EXISTS scholarship(
            id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
            web_id INT NOT NULL,
            country_id INT,
            school_id INT,
            name text,
            time date CHECK (time >= CURDATE()),
            content text,
            url text,
            apply_link text,
            url_image text,
            rating float(5) NOT NULL DEFAULT '0',
            number_rating int DEFAULT 0,
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
        self.curr.execute("""CREATE TABLE IF NOT EXISTS major_scholarship(
                    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                    scholarship_id int NOT NULL,
                    major_id int ,
                    created_time timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                    updated_time timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                    updated_by_user_id int(11) DEFAULT NULL,
                    created_by_user_id int(11) DEFAULT NULL,
                    is_deleted bit(1) DEFAULT b'0'           
        )""")

    def create_table_level(self):
        self.curr.execute("""CREATE TABLE IF NOT EXISTS level(
                    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                    scholarship_id int NOT NULL,
                    name text,
                    created_time timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                    updated_time timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                    updated_by_user_id int(11) DEFAULT NULL,
                    created_by_user_id int(11) DEFAULT NULL,
                    is_deleted bit(1) DEFAULT b'0'                  
        )""")

    def create_table_money(self):
        self.curr.execute("""CREATE TABLE IF NOT EXISTS money(
                    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                    scholarship_id int NOT NULL,
                    value text,
                    created_time timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                    updated_time timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                    updated_by_user_id int(11) DEFAULT NULL,
                    created_by_user_id int(11) DEFAULT NULL,
                    is_deleted bit(1) DEFAULT b'0'                  
        )""")

    def create_table_requirement(self):
        self.curr.execute("""CREATE TABLE IF NOT EXISTS requirement(
                    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                    scholarship_id int NOT NULL,
                    name text,
                    value text,
                    created_time timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                    updated_time timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                    updated_by_user_id int(11) DEFAULT NULL,
                    created_by_user_id int(11) DEFAULT NULL,
                    is_deleted bit(1) DEFAULT b'0'                
        )""")

    def create_table_country(self):
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
        self.curr.execute("""CREATE TABLE IF NOT EXISTS school(
                    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                    name varchar(100),
                    country_id int,
                    created_time timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                    updated_time timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                    updated_by_user_id int(11) DEFAULT NULL,
                    created_by_user_id int(11) DEFAULT NULL,
                    is_deleted bit(1) DEFAULT b'0'             
        )""")

    def getCountryId(self, country):
        self.curr.execute("SELECT id FROM country WHERE name = %s", (country,))
        country_id = self.curr.fetchone()
        if (country_id is None):
            self.curr.execute("""INSERT INTO country(name) VALUES (%s)""", (country,))
            self.conn.commit()
            self.restart()
            return self.getCountryId(country)
        return country_id[0]

    def checkUrl(self,url):
        self.curr.execute("SELECT id FROM scholarship WHERE url = %s", (url,))
        id = self.curr.fetchone()
        if id is not None: return True
        return False

    def getSchoolId(self, school, country_id):
        self.curr.execute("SELECT id FROM school WHERE name = %s", (school,))
        school_id = self.curr.fetchone()
        if (school_id is None):
            self.curr.execute("""INSERT INTO school(name, country_id) VALUES (%s,%s)""", (school,country_id))
            self.conn.commit()
            self.restart()
            return self.getSchoolId(school, country_id)
        return school_id[0]

    def store_db(self, item):
        #
        self.curr.execute("UPDATE web SET last_crawled=now() WHERE id = %s", (item["web"],))
        if self.checkUrl(item["url"]) == True: return
        #
        country_id = self.getCountryId(item["country"])
        school_id = None
        if item["school"] is not None:
            school_id = self.getSchoolId(item["school"], country_id)

        if (item['newMajor'] == True):
            self.store_db_scholarship_major(item["url"], item["major"]);
            return

        self.curr.execute(
            """Insert into scholarship(name, time, url, web_id, content, country_id, school_id, url_image,apply_link) values (%s, %s, %s, %s, %s, %s, %s, %s, %s)""",
            (
                item['name'],
                item['time'],
                item['url'],
                item['web'],
                item['content'],
                country_id,
                school_id,
                item['urlImage'],
                item['applyLink']
            ))
        self.curr.execute("UPDATE web SET last_crawled=now() WHERE id = %s", (item["web"],))
        self.conn.commit()
        self.restart()
        self.store_db_attribute(item)

    def store_new_major(self, major):
        self.curr.execute("""INSERT INTO major(name) VALUES (%s)""", (
            major,
        ))
        self.conn.commit()
        self.restart()

    def store_db_major_scholarship(self, scholarship_id, major):
        self.curr.execute("SELECT id FROM major WHERE name like %s", ('%' + major + '%',))
        major_id = self.curr.fetchone()
        if major_id is None:
            self.store_new_major(major)
            self.curr.execute("SELECT id FROM major WHERE name like %s", ('%' + major + '%',))
            major_id = self.curr.fetchone()

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
        self.store_db(item)
        return item
