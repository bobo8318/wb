import sys
import time
import logging
from watchdog.observers import Observer #监控服务
from MyEventHander import MyEventHandler
from readconfig import getPath

if __name__ == "__main__":
    #logging.basicConfig(level=logging.INFO,format='%(asctime)s - %(message)s',datefmt='%Y-%m-%d %H:%M:%S')
    #path = sys.argv[1] if len(sys.argv) > 1 else '.'
    path = getPath()
    event_handler = MyEventHandler(path)
    observer = Observer()
    observer.schedule(event_handler, path, recursive=True)# 添加handler
    observer.start()
    print("server is running...")
    try:
        while True:
            time.sleep(1)
    except KeyboardInterrupt:
        observer.stop()
    observer.join()