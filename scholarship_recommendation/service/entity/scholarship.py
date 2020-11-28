import yaml

with open('config.yaml', 'r') as f:
    config = yaml.load(f, Loader=yaml.FullLoader)
    print(config)

class Scholarship:

    def __init__(self, id, country, school, major, level, money, time):
        #ountry 3, school 14, major {'12', '5', '8'}, level [('PHD',)], money [('$15,000',)], time 2020-11-29
        self.id = id

        self.country = [country]
        self.school = [school]
        self.major = [int(m) for m in major]

        level = level[0][0]
        if level:
            self.level = [config['list_level'].index(level)]
        else:
            self.level = []



        if money in config['list_money']:
            self.money = [config['list_money'].index(money[0][0])]
        else:
            self.money = [0]
        self.time = self.create_time(str(time))

        self.vector = None

    def create_money(self, money):
        print(money)
        m = money[0][0][1:].split(',')
        money = int(m[0])
        for m_ in m[1:]:
            money = money * 1000 + int(m_)

        return money

    def create_time(self, time_):
        t = time_
        y, m, d = [int(t_) for t_ in t.split('-')]
        
        return (y + m/12 + float(d)/365)

    def get_vector(self):
        # if self.vector:
        #     return self.vector
        
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