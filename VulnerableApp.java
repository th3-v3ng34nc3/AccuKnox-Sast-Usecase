import sqlite3

def get_user_info(username):
    conn = sqlite3.connect('example.db')
    cursor = conn.cursor()
    # Vulnerable to SQL Injection
    query = f"SELECT * FROM users WHERE username = '{username}'"
    cursor.execute(query)
    result = cursor.fetchall()
    conn.close()
    return result

if __name__ == "__main__":
    import sys
    user_input = sys.argv[1]
    print(get_user_info(user_input))
