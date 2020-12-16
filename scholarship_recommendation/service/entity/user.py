import yaml

with open('config.yaml', 'r',  encoding="utf8") as f:
    config = yaml.load(f, Loader=yaml.FullLoader)


class User():

    def __init__(self, userId, country, school, major, level, money, time_, scholarship):


        # country [(4,), (5,)], school [(1,), (4,), (13,)], major {'4', '22', '14'}, level Bachelor, time 1970-01-01 07:00:00
        self.id = userId
        self.country = list(set([c[0] for c in country]))
        self.school = list(set([s[0] for s in  school]))
        self.major = list(set([int(m) for m in major]))
        self.level = list(set([config['list_level'].index(l) for l in level]))
        self.money = []

        self.time = self.create_time(time_)


        if scholarship is not None:
            self._add_scholarship(scholarship)
        self.vector = None

    def _add_scholarship(self, scholarship):
        self.country += scholarship.country
        self.school += scholarship.school
        self.major += scholarship.major
        self.level += scholarship.level
        self.money += scholarship.money

    def create_time(self, time_):
        try:
            t = time_.split(' ')[0]
            y, m, d = [int(t_) for t_ in t.split('-')]

            return (y + m/12 + float(d)/365)
        except:
            return -1


    def get_vector(self):
        # if self.vector:
        #     return vector
        
        country_vector =[]

        vector = []
        try:
            vector_country = self.get_vector_country()
            vector_school = self.get_vector_school() 
            vector_major = self.get_vector_major() 
            vector_level =self.get_vector_level()
            vector_money = self.get_vector_money() 
            vector_time = self.get_vector_time() 
            vector = [vector_country, vector_school, vector_major,vector_level, vector_money, vector_time]

        except Exception as e:
            print('Get Exception when create vector scholarship {} {}'.format(self.id, e))
        
        self.vector = vector
        return vector

    def get_vector_country(self):
        v = [0] * config['num_country']
        for index in self.country:
            v[index] = 1
        return v 
    
    def get_vector_school(self):
        v = [0] * config['num_school']
        for index in self.school:
            v[index] = 1
        return v 

    def get_vector_level(self):
        v = [0] * config['num_level']
        for index in self.level:
            v[index] = 1
        return v

    def get_vector_major(self):
        v = [0] * config['num_major']
        for index in self.major:
            v[index] = 1
        return v 

    def get_vector_money(self):
        v = [0] * len(config['list_money'])
        for index in self.money:
            v[index] = 1
        return v 

    def get_vector_time(self):
        return [self.time]