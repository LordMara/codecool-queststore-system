package src.dao;

public interface DAOInterface<T> {
    public ArrayList<T> getAllObjects();
    public void addObject(T object);
    public boolean removeObject(T object);
}