package ru.dest.library.obj;

public class FormatResult<RType> {
    private RType type;

    public FormatResult(RType type) {
        this.type = type;
    }

    public RType get() {
        return type;
    }
}
