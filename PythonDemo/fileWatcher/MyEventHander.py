import os, threading
from watchdog.events import FileSystemEventHandler#事件处理器的基类  
from watchdog.utils.dirsnapshot import DirectorySnapshot, DirectorySnapshotDiff #文件夹快照
from gittools import GitTools

class MyEventHandler(FileSystemEventHandler):
    def __init__(self, aim_path):
        FileSystemEventHandler.__init__(self)
        self.aim_path = aim_path #监控目录
        self.timer = None
        self.snapshot = DirectorySnapshot(self.aim_path) #初始化快照
        self.tools = GitTools(aim_path)
    def on_any_event(self, event):
        if self.timer:
            self.timer.cancel()
        self.timer = threading.Timer(0.2, self.checkSnapshot, (event,))# 定时调用函数
        self.timer.start()

    def checkSnapshot(self, event):
        snapshot = DirectorySnapshot(self.aim_path)
        diff = DirectorySnapshotDiff(self.snapshot, snapshot) #比对快照
        self.snapshot = snapshot #保存快照
        self.timer = None #关闭定时器
        #下面是应处理的各种事项
        #排除git文件
        if len(diff.files_modified)>0:
            print(diff.files_modified[0])  
            if '.git' in diff.files_modified[0]:
                print('gitfile jump')
            else:
                testgit = self.tools.test()
                if 'usage:' in testgit :
                    result = self.tools.gitcommit()
                    if result != 'add_faile':
                        print(self.tools.gitpush())
                else:
                    print(testgit)
                    pass
        '''
        print("files_created:", diff.files_created)
        print("files_deleted:", diff.files_deleted)
        print("files_moved:", diff.files_moved)
        print("dirs_modified:", diff.dirs_modified)
        print("files_modified:", diff.files_modified)
        print("dirs_moved:", diff.dirs_moved)
        print("dirs_deleted:", diff.dirs_deleted)
        print("dirs_created:", diff.dirs_created)
        '''
        
    '''
    def on_moved(self, event):
        super(MyEventHandler, self).on_moved(event)

        what = 'directory' if event.is_directory else 'file'
        print("Moved %s: from %s to %s", what, event.src_path,event.dest_path)

    def on_created(self, event):
        super(MyEventHandler, self).on_created(event)

        what = 'directory' if event.is_directory else 'file'
        print("Created %s: %s", what, event.src_path)

    def on_deleted(self, event):
        super(MyEventHandler, self).on_deleted(event)

        what = 'directory' if event.is_directory else 'file'
        print("Deleted %s: %s", what, event.src_path)

    def on_modified(self, event):
        super(MyEventHandler, self).on_modified(event)

        what = 'directory' if event.is_directory else 'file'
        print("Modified %s: %s", what, event.src_path)
    '''