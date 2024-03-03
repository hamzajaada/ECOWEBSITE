package DAO;

public interface UserDao {
    public void addUser(User p);
    public boolean login(String username, String password);
}
