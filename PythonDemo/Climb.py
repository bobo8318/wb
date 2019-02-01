import urllib.request
class Spider(object):
    def __init__(self,url):
        """
            初始化url
        """
        self.url = url
        pass
    def Climb(self):
        request = urllib.Request(self.url)
        response = urllib.request.urlopen(request)
    pass
pass