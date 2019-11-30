from flask import Flask
from flask import request, jsonify, abort
import subprocess
from readconfig import getToken, getPullLocalPath,getPullAppPort

app = Flask(__name__)

WEBHOOK_VERIFY_TOKEN = getToken()
localpath = getPullLocalPath()
port = getPullAppPort()

@app.route('/webhook', methods=['GET', 'POST'])
def webhook():
    if request.method == 'GET':
        verify_token = request.headers.get('X-Gitlab-Token')
        if verify_token == WEBHOOK_VERIFY_TOKEN:
            return jsonify({'status': 'success'}), 200
        else:
            return jsonify({'status': 'bad token','token':WEBHOOK_VERIFY_TOKEN}), 401

    elif request.method == 'POST':
        verify_token = request.headers.get('X-Gitlab-Token')
        if verify_token == WEBHOOK_VERIFY_TOKEN:
            # 进入到代码目录，拉取最新代码
            retcode = subprocess.call("cd %s && git pull" % localpath, shell=True)#创建子进程执行外部程序
            if retcode == 0:
                return jsonify({'status': 'success'}), 200
            else:
                return jsonify({'status': 'git pull error'}), 503
        else:
            return jsonify({'status': 'bad token'}), 401
    else:
        abort(400)#强制退出

@app.route("/")
def index():
    return "webhook index"

if __name__ == '__main__':
    app.run(host='0.0.0.0', port='5000')