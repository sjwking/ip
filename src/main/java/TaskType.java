public enum TaskType {
    TODO("T"),
    DEADLINE("D"),
    EVENT("E"),
    BLANK(" ");

    final String typeIcon;

    TaskType(String typeIcon) {
        this.typeIcon = typeIcon;
    }
}
