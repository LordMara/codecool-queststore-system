public interface DAOInterface<T> {
    public void loadObject();
    public ArrayList<T> getAllObjects();
    public void addObject(T object);
    public boolean removeObject(T object);
    public void saveObject();

}