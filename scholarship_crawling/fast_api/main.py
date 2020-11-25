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
        if url == "scholarship-positions.com":
            subprocess.call([r'F:\DO_AN\do_an_tot_nghiep\scholarship_crawling\sqlscholarshipposition.bat'])
        elif url == "www.scholarshipsads.com":
            subprocess.call([r'F:\DO_AN\do_an_tot_nghiep\scholarship_crawling\sqlscholarshipads.bat'])
    except (RuntimeError, TypeError, NameError, OSError, ValueError):
        return False
    else:
        return True
