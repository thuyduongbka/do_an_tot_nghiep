from typing import Optional

from fastapi import FastAPI
from pydantic import BaseModel
import subprocess

app = FastAPI()


class Item(BaseModel):
    name: str
    price: float
    is_offer: Optional[bool] = None


@app.get("/")
def read_root(q: Optional[str] = None):
    return q

@app.get("/crawl")
def read_crawl(url: Optional[str] = None):
    try:
        if url == "scholarshipplanet.info":
            return url
            # subprocess.call([r'F:\DO_AN\do_an_tot_nghiep\scholarship_crawling\scholarshipplanet.bat'])
        elif url == "sanhocbong.net":
            return url
            # subprocess.call([r'F:\DO_AN\do_an_tot_nghiep\scholarship_crawling\sanhocbong.bat'])
        elif url == "nguonhocbong.com":
            return url
            # subprocess.call([r'F:\DO_AN\do_an_tot_nghiep\scholarship_crawling\nguonhocbong.bat'])
        return True
    except (RuntimeError, TypeError, NameError, OSError, ValueError):
        return False
