public interface DAOInterface<T> {
    public void loadObject();
    public void saveObject(T object);
}