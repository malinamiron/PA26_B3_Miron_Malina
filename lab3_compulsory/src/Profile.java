public interface Profile {
    String getId();
    String getName();

    int compareTo(Profile other);
}
