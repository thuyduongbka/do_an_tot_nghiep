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
def read_root():
    return {"Hello": "World"}


@app.get("/items/{item_id}")
def read_item(item_id: int, q: Optional[str] = None):
    return {"item_id": item_id, "q": q}


@app.put("/items/{item_id}")
def update_item(item_id: int, item: Item):
    return {"item_name": item.name, "item_id": item_id}


@app.get("/crawl")
def read_crawl():
    try:
        subprocess.call([r'F:\DO_AN\do_an_tot_nghiep\scholarship_crawling\scholarshipplanet.bat'])
        return {"true"}
    except (RuntimeError, TypeError, NameError, OSError, ValueError):
        return {"false"}
