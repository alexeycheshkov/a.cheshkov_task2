public enum Status {

    CREATED(201),
    NOT_FOUND(404),
    OK(200);

    private int code;

    Status(int code) {
        this.code = code;
    }

    public int getCode(){
        return code;
    }
}
