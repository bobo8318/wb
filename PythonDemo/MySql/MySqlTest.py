import pymysql
import pandas as pd
import numpy as np
'''

s = pd.Series([i*2 for i in range(1,11)])
dates = pd.date_range("20170301",periods=8)
df = pd.DataFrame(np.random.randn(8,5),index=dates,columns=list("ABCDE"))
df.head(3)
df.tail(3)
df.index
df.values
df.T
df.sort_values(["C"],ascending = False)
df.sort_index(axis=1,ascending=False)
df.describe()
#select
df["A"]
df[:3]
df["20170301":"20170304"]
df.loc[dates[0]]#选择
df.loc["20170301":"20170304",["B","D"]]
df.at[dates[0],"B"]
df.iloc[2:3,1:3]#不包括3
df.iloc[2,3]
df[df.B>0][df.A<0]
df[df>0]#返回大于零的数 不大于零的为NaN
df[df["E"].isin([1,2])]
#set
s = pd.Series(list(range(10,18)),index=pd.date_range("20170301",periods=8))

df["F"] = s
df.loc[:,"D"]= np.array([4]*len(df))#[4 4 4 4 4 4 4 4]
df["E"] = np.array([5]*len(df))
df2 = df.copy()
df2[df2>0] = -df2#正数变复数

# missing value
df1 = df.reindex(index=dates[:4],columns=list("ABCD")+["G"])
df1.loc[dates[0]:dates[1],"G"] = 1 # df1.loc[0:2,"G"] = 1
df1.dropna()# 去除有空值的行
df1.loc[:,"G"].fillna(2)# 用2填充空值
#statistic
df.mean()# Series 每列均值
df.var()# Series 每列方差

s = pd.Series([1,2,3,4,np.nan,5,6,7],index=dates)
print(s)

xnums = np.arange(4)
ynums = np.arange(5)
x,y = np.meshgrid(xnums,ynums)

print(x)
print(y)
'''
#from scipy.integrate import quad,dblquad
#print(quad(lambda x:np.exp(-x),0,np.inf))
print(lambda x:np.exp(-x),0,np.inf)
def dataBaseTest():
    db = pymysql.connect( host='127.0.0.1',
                          port = 3307,
                          user='root',
                          passwd='usbw',
                          db ='openui',
                          charset='utf8')

    cursor = db.cursor()
    try:
        cursor.execute("select lotterydata from lottery limit 0,10")
        results = cursor.fetchall()
        #npdata = np.array();
        redballlist = [];
        for data in results:
            str = data[0]
            redball = str.split("|",1)[0]

            balllist = redball.split(",")
            #print(balllist)
            #list_to_int = list(map(lambda x:int(x), balllist))
            redballlist.append(balllist)

        a = np.array(redballlist,dtype=np.int)
        #print(a)

    except:
        print(f'database error')

    db.close()