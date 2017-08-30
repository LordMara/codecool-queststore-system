public interface DAOInterface<T> {
    public <T> loadObject();
    public void saveObject(<T> object);
}