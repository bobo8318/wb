import os
import datetime

class GitTools():
    def __init__(self, gitdir):
        self.gitdir = gitdir
        os.chdir(self.gitdir)
        pass
    def test(self):
        print(os.getcwd())
        result = os.popen('git')
        #print("-----------------------------")
        #print(result.read())
        return result.read()
        
    def gitcommit(self):
        print("------git add  & commit--------")
        add_result = os.popen('git add .')
        if 'fatal:' in add_result.read():
            print(add_result)
            return 'add_faile'
        else:
            now_time = datetime.datetime.now().strftime('%Y-%m-%d')
            commit_result = os.popen('git commit -m %s' % now_time)
            return commit_result.read()
    def gitpush(self):
        print("------git push--------")
        result = os.popen('git push -u origin master')
        return result.read()

if __name__ == ("__main__"):
    tools = GitTools("G:/test/webapp")
    testgit = tools.test()
    if 'usage:' in testgit :
        result = tools.gitcommit()
        if result != 'add_faile':
            print(tools.gitpush())
    else:
        print(testgit)
