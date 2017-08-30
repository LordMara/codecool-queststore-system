public interface DAOInterface<T> {
    public ArrayList loadObject();
    public void saveObject(T object);
}