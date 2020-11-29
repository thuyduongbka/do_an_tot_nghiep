
import yaml

with open('config.yaml', 'r', encoding="utf8") as f:
    config = yaml.load(f, Loader=yaml.FullLoader)
    #print(config)


# class RecommendSystem():
#     def __init__(self):
#         pass 

#     def recommend(user, list_scholarship):
        

#         list_recommand = []

#         try:
#             pass
#         except:
#             pass 

#         return list_recommand

from sklearn.metrics.pairwise import cosine_similarity
from sklearn.neighbors import KNeighborsClassifier
from collections import deque  

def recommend(user, list_scholarship):

    user_info = user.get_vector()

    num_scholarship_recommned = config['num_scholarship_recommned']
    result = deque([], num_scholarship_recommned)

    top_cosine_similarity = config['min_similarity']
    for scholarship in list_scholarship:

        scholarship_info = scholarship.get_vector()

        if sum(user_info[0]) == 0 : cosine_similarity_country = 0
        else:
            cosine_similarity_country = cosine_similarity([user_info[0]], [scholarship_info[0]]) [0][0]
        if sum(user_info[1]) == 0: cosine_similarity_school = 0
        else:
            cosine_similarity_school = cosine_similarity([user_info[1]], [scholarship_info[1]])[0][0]
        if sum(user_info[2]) == 0: cosine_similarity_major = 0
        else:
            cosine_similarity_major = cosine_similarity([user_info[2]], [scholarship_info[2]])[0][0]
        if sum(user_info[3]) == 0: cosine_similarity_level = 0
        else:
            cosine_similarity_level = cosine_similarity([user_info[3]], [scholarship_info[3]])[0][0]
        if sum(user_info[4]) == 0: cosine_similarity_money = 0
        else:
            cosine_similarity_money = cosine_similarity([user_info[4]], [scholarship_info[4]])[0][0]

        # user_infor[5] = time
        if 0<user_info[5][0] - scholarship_info[5][0] < 0.25:

            cosine_similarity_time = 1.0
        elif  0.25 < user_info[5][0] - scholarship_info[5][0] < 0.5:
            cosine_similarity_time = 1 - (user_info[5][0] - scholarship_info[5][0])
        else:
            cosine_similarity_time = 0
            
        

        cosine_similarity_ = cosine_similarity_country * config['weight_country'] + \
                            cosine_similarity_school * config['weight_school'] + \
                                cosine_similarity_major * config['weight_major'] + \
                                    cosine_similarity_level * config['weight_level'] + \
                                        cosine_similarity_money * config['weight_money'] + \
                                            cosine_similarity_time * config['weight_time'] 


        if cosine_similarity_ > top_cosine_similarity:
            # top_cosine_similarity = cosine_similarity_

            result.append([scholarship.id, cosine_similarity_, cosine_similarity_country, cosine_similarity_school,
                           cosine_similarity_major,  cosine_similarity_level, cosine_similarity_money, cosine_similarity_time])
            result = list(result)

            if len(result) == 10:
                top_cosine_similarity = min(result, key = lambda x: x[1])[1]


            result.sort(key = lambda x: x[1])
            result = deque(result, num_scholarship_recommned)

    return [x[0] for x in list(result)][::-1]
    # return list(result)

