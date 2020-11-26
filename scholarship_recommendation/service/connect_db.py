import mysql.connector

class ConnectDB:
    def __init__(self):
        self.create_connection()

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

    def getUserInfor(self, userId):
        print("OK")
        self.curr.execute("SELECT * FROM end_user WHERE id = %s", (userId,))
        user = self.curr.fetchone()
        print(user)