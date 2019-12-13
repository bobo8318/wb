# coding=utf-8  #第一行添加，以下8种写法，默认ascii编码所以要重定义编码格式以支持中文
# coding= utf-8
# encoding=utf-8
# encoding= utf-8
# -*- coding: utf-8 -*-
# -*- coding:utf-8 -*-
# -*- encoding: utf-8 -*-
# -*- encoding:utf-8 -*-


from apscheduler.schedulers.blocking import BlockingScheduler
from datetime import datetime

# 输出时间
def job():
    print("hello running")
# BlockingScheduler
scheduler = BlockingScheduler()
scheduler.add_job(job, "cron", day_of_week="1-5", hour=16, minute=41)
scheduler.start()
print("server is running")