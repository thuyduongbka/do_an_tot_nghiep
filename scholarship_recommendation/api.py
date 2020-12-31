from typing import Optional

from fastapi import FastAPI
from pydantic import BaseModel
import subprocess

from service.connect_db import *
from service.recommend_system import *

app = FastAPI()


@app.get("/crawl")
async def read_crawl(url: Optional[str] = None):
    service = ConnectDB()

    if url == "scholarship-positions.com":
        subprocess.call([r'F:\DO_AN\do_an_tot_nghiep\scholarship_crawling\scholarshipposition.bat'])
    elif url == "www.scholarshipsads.com":
        service.updateWebSource(4)
        subprocess.call([r'F:\DO_AN\do_an_tot_nghiep\scholarship_crawling\scholarshipads.bat'])
    return True


@app.get("/get-recommend")
def get_recommend(userId: Optional[str] = None, scholarshipId: Optional[str] = None):
    service = ConnectDB()
    listScholarship = service.getListScholarship(userId, scholarshipId)
    user = service.getUserInfor(userId, scholarshipId)

    # print('country {}, school {} , major {}, user {} , money {}, time {}'.format(uers.country, user.school, user.major, user.level, user.money, user.time))

    list_scholarship_recommend = recommend(user, listScholarship)
    # print(list_scholarship_recommend)
    if scholarshipId in list_scholarship_recommend:
        list_scholarship_recommend.remove(scholarshipId)

    return list_scholarship_recommend


@app.get("/get-recommend-conversation")
def get_recommend_conversation(userId: Optional[str] = None,
                                scholarshipId: Optional[str] = None,
                               countryDislike: Optional[str] = None,
                               listCountryLike: Optional[str] = None,
                               schoolDislike: Optional[str] = None,
                               listSchoolLike: Optional[str] = None,
                               appropriateTime: Optional[str] = None,
                               listLevelLike: Optional[str] = None,
                               listLevelDislike: Optional[str] = None,
                               listMajorLike: Optional[str] = None,
                               listMajorDislike: Optional[str] = None):
    service = ConnectDB()
    print("scholarshipId ", scholarshipId)
    print("countryDislike: ",countryDislike)
    print("listCountryLike ", listCountryLike)
    print("schoolDislike:",schoolDislike)
    print("listSchoolLike: ", listSchoolLike)
    print("appropriateTime: ", appropriateTime)
    print("listLevelLike: ", listLevelLike)
    print("listLevelDislike: ", listLevelDislike)
    print("listMajorLike: ", listMajorLike)
    print("listMajorDislike: ", listMajorDislike)
    user = service.getUserInforFromConversation(userId,
                                        scholarshipId,
                                        countryDislike,
                                        listCountryLike,
                                        schoolDislike, 
                                        listSchoolLike,
                                        appropriateTime,
                                        listLevelLike,
                                        listLevelDislike,
                                        listMajorLike,
                                        listMajorDislike)

    listScholarship = service.getListScholarshipFromConversation(userId, 
                                                                scholarshipId,
                                                                countryDislike,
                                                                schoolDislike, 
                                                                listLevelDislike,
                                                                listMajorDislike)
    list_scholarship_recommend = recommend(user, listScholarship)
    # print(list_scholarship_recommend)
    if scholarshipId in list_scholarship_recommend:
        list_scholarship_recommend.remove(scholarshipId)

    return list_scholarship_recommend
