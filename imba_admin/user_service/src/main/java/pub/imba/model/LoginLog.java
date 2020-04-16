package pub.imba.model;

public class LoginLog {

    private int id;
    private String userid;
    private String logintime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

  public String getLogintime() {
      return logintime;
  }

    public void setLogintime(String logintime) {
        this.logintime = logintime;
    }
}
