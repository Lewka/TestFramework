package auto.bo;

public enum ErrorMessage {

    WRONG_INPUT_ERROR("Поле неправильно заполнено"),
    WRONG_INPUT_ERROR_2("Поле заполнено некорректно"),
    WRONG_INPUT_ERROR_3("Поле заполнено неверно");

    String errorMessage;

    ErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}