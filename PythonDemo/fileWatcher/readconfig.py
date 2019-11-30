import configparser
import os

def getPath(): 
    cf = configparser.ConfigParser()
    cf.read("./config.ini")
    print(os.getcwd())
    #secs = cf.sections()
    #items = cf.items("Mysql-Database")
    path = cf.get("Path", "path")
    print("read config finish")
    return path.replace("\\", "/")

def getToken():
    print("config get token")
    cf = configparser.ConfigParser()
    cf.read("./config.ini")
    return cf.get("WebHook", "token")

def getPullLocalPath():
    cf = configparser.ConfigParser()
    cf.read("./config.ini")
    return cf.get("WebHook", "path")

def getPullAppPort():
    cf = configparser.ConfigParser()
    cf.read("./config.ini")
    return cf.get("WebHook", "port")
