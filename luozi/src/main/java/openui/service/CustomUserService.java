package openui.service;

import openui.bean.OpenAdmin;

import java.util.List;

public interface CustomUserService {

    public List<OpenAdmin> listUser();
    public void addUser(OpenAdmin user);
    public void updateUser(OpenAdmin user);
    public void removeUser(int id);

    OpenAdmin findUserById(int id);

    OpenAdmin findUserByName(String username);
}
