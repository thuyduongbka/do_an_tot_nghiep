from typing import Optional

from fastapi import FastAPI
from pydantic import BaseModel
import subprocess

from service.connect_db import *
from service.recommend_system import *
app = FastAPI()


class Item(BaseModel):
    name: str
    price: float
    is_offer: Optional[bool] = None


@app.get("/")
def read_root(q: Optional[str] = None):
    return q


@app.get("/crawl")
async def read_crawl(url: Optional[str] = None):
    if url == "scholarship-positions.com":
        subprocess.call([r'F:\DO_AN\do_an_tot_nghiep\scholarship_crawling\scholarshipposition.bat'])
    elif url == "www.scholarshipsads.com":
        subprocess.call([r'F:\DO_AN\do_an_tot_nghiep\scholarship_crawling\scholarshipads.bat'])
    return True


@app.get("/get-recommend")
def get_recommend(userId: Optional[str] = None):
    service = ConnectDB()
    listScholarship = service.getScholarship()
    user = service.getUserInfor(userId)

    # print('country {}, school {} , major {}, user {} , money {}, time {}'.format(uers.country, user.school, user.major, user.level, user.money, user.time))

    list_scholarship_recommend =  recommend(user, listScholarship)
    print(list_scholarship_recommend)

    return list_scholarship_recommend

