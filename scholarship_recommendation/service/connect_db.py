import mysql.connector
from service.entity.scholarship import Scholarship
from service.entity.user import User
import yaml

with open('config.yaml', 'r',  encoding="utf8") as f:
    config = yaml.load(f, Loader=yaml.FullLoader)

class ConnectDB:
    def __init__(self):
        self.create_connection()

    def create_connection(self):
        self.conn = mysql.connector.connect(
            host='192.168.1.3',
            user='root_son',
            passwd='empty258',
            database='scholarship_englis'
                     'h'
        )
        self.curr = self.conn.cursor(buffered=True)

    def updateWebSource(self, web):
        self.curr.execute("UPDATE web SET last_crawled=now() WHERE id = %s", (web,))
        self.conn.commit()
        self.restart()

    def restart(self):
        self.curr.close()
        self.curr = self.conn.cursor(buffered=True)

    def majorToArray(self, majorAndParentAndChildList):
        majorArray = set()
        for majorAndParentAndChild in majorAndParentAndChildList:
            majorId = str(majorAndParentAndChild[0])
            majorParentId = majorAndParentAndChild[1].split(",")
            majorChildId = majorAndParentAndChild[2].split(",")
            majorArray.add(majorId)
            majorArray.update(majorParentId[:-1],majorChildId[:-1])
        return majorArray

    def getUserInfor(self, userId, scholarshipId):
        self.curr.execute("SELECT id, level, graduation_date FROM end_user WHERE id = %s", (userId,))
        result = self.curr.fetchone()
        id = result[0]
        level = result[1]
        if level == '': level = []
        else: level = level.split(",")
        time = result[2]
        self.curr.execute(
            "SELECT c.id FROM country_favorite f join country c on f.country_id = c.id WHERE user_id = %s ", (userId,))
        countryFavorite = self.curr.fetchall()
        self.curr.execute("SELECT c.id FROM school_favorite f join school c on f.school_id = c.id WHERE user_id = %s",
                          (userId,))
        schoolFavorite = self.curr.fetchall()
        self.curr.execute(
            "SELECT c.id, c.note, c.child FROM major_favorite f join major c on f.major_id = c.id WHERE user_id = %s", (userId,))
        majorFavorite = self.majorToArray(self.curr.fetchall())

        scholarship = None
        if scholarshipId is not None:
            scholarship = self.getScholarship(scholarshipId)
        return User(id,countryFavorite, schoolFavorite, majorFavorite, level, [], time, scholarship)

    def getLevel(self, scholarshipId):
        self.curr.execute("SELECT name FROM level WHERE scholarship_id = %s", (scholarshipId,))
        return self.curr.fetchall()
    def getMoney(self, scholarshipId):
        self.curr.execute("SELECT value FROM money WHERE scholarship_id = %s", (scholarshipId,))
        return self.curr.fetchall()
    def getMajor(self, scholarshipId):
        self.curr.execute("SELECT m.id, note, child FROM major m JOIN major_scholarship s ON s.major_id = m.id WHERE scholarship_id = %s", (scholarshipId,))
        return self.curr.fetchall()

    def getScholarship(self, scholarshipId):
        print("Recommend Same")
        self.curr.execute(
            "SELECT id, country_id, school_id, time FROM `scholarship`  WHERE id = %s", (scholarshipId,))
        result = self.curr.fetchone();
        return self.toEntity(result)

    def getListScholarship(self, userId, scholarshipId):
        self.curr.execute(
            "SELECT id, country_id, school_id, time "
            "FROM `scholarship` "
            "WHERE time >= now() "
            "AND id NOT IN (SELECT si.scholarship_id FROM scholarship_interactive si WHERE si.user_id = %s ) "
            "ORDER BY `scholarship`.`time` ASC", (userId,))
        results = self.curr.fetchall();
        listScholarship = []
        for s in results:
            scholarship = self.toEntity(s)
            if (scholarshipId != scholarship.id):
                listScholarship.append(scholarship)
        return listScholarship

    def toEntity(self,s):
        id = s[0]
        country = s[1]
        school = s[2]
        time = s[3]
        level = self.getLevel(id)
        money = self.getMoney(id)
        major = self.majorToArray(self.getMajor(id))

        return Scholarship(id, country, school, major, level, money, time)

    def getUserInforFromConversation(self, userId, scholarshipId,countryDislike,countryLikeId,
                                     schoolDislike, schoolLikeId,appropriateTime,listLevelLike,
                                     listLevelDislike,listMajorLike,listMajorDislike):
        with open('config.yaml', 'r',  encoding="utf8") as f:
            config = yaml.load(f, Loader=yaml.FullLoader)

        scholarship = self.getScholarship(scholarshipId)
        # user = self.getUserInfor(userId, None)
        user = self.getScholarship(scholarshipId)

        print('country ', user.country)
        print('school ', user.school)
        print('major ', user.major)
        print('level ', user.level)
        print('time ', user.time)


        if countryDislike  == 'true':
            for country in scholarship.country:
                while country in user.country:
                    user.country.remove(country)

            if countryLikeId != 'null':
                countryLikeId = int(countryLikeId)
                user.country.append(countryLikeId)

        if schoolDislike == 'true':
            print('dadsdd ', scholarship.school)
            for school in scholarship.school:
                while school in user.school:
                    user.school.remove(school)

            if schoolLikeId != 'null':
                schoolLikeId = int(schoolLikeId)
                user.school.append(schoolLikeId)

        if listLevelLike:
            for level in listLevelLike.split(','):
                user.level.append(config['list_level'].index(level))

        if listLevelDislike:
            for level in listLevelDislike.split(','):
                index = config['list_level'].index(level)
                while index in user.level:
                    user.level.remove(index)

        if listMajorLike:
            for major in listMajorLike.split(','):
                major = int(major)
                user.major.append(major)
            
        if listMajorDislike: 
            for major in listMajorDislike.split(','):
                major = int(major)
                while major in user.major:
                    user.major.remove(major)

        if appropriateTime: 
            user.time = user.create_time(appropriateTime)
        
        #
        # print('after')
        # print('country ', user.country)
        # print('school ', user.school)
        # print('major ', user.major)
        # print('level ', user.level)
        # print('time ', user.time)
        return user
    
    def getListScholarshipFromConversation(self, userId, scholarshipId,
                                        countryDislike,
                                        schoolDislike, 
                                        listLevelDislike,
                                        listMajorDislike):

        listScholarship = self.getListScholarship(userId, None)
        scholarship_dislike = self.getScholarship(scholarshipId)

        black_list = []


        if countryDislike == 'true':
            scholarship_dislike.country.sort()

            for i, scholarship in enumerate(listScholarship):
                scholarship.country.sort()
                if scholarship.country == scholarship_dislike.country:
                    black_list.append(i)
            
        if schoolDislike == 'true':
            scholarship_dislike.school.sort()
            for i, scholarship in enumerate(listScholarship):
                if i not in black_list:
                    scholarship.school.sort()
                    if scholarship.school == scholarship_dislike.school:
                        black_list.append(i)

        if listLevelDislike:
            listLevelDislike = listLevelDislike.split(',')
            for i, scholarship in enumerate(listScholarship):
                if i not in black_list:
                    scholarship.level.sort()
                    if scholarship.level == listLevelDislike:
                        black_list.append(i)
                        
        if listMajorDislike:
            listMajorDislike = listMajorDislike.split(',')
            for i, scholarship in enumerate(listScholarship):
                if i not in black_list:
                    scholarship.major.sort()
                    if scholarship.major == listMajorDislike:
                        black_list.append(i)

        black_list.sort()
        black_list = black_list[::-1] # dao nguoc list

        for i in black_list:

            listScholarship.pop(i)
        return listScholarship

                                
    def stringToArr(self, str):
        if str == "": return []
        return str.split(",")


