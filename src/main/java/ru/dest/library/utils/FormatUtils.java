package ru.dest.library.utils;

import org.jetbrains.annotations.Nullable;
import ru.dest.library.obj.FormatResult;

public final class FormatUtils {

    @Nullable
    public static FormatResult<Integer> parseInt(String s){
        try {
           return new FormatResult<>(Integer.parseInt(s));
        }catch (Exception e){
            return null;
        }
    }

    @Nullable
    public static FormatResult<Double> parseDouble(String s){
        try {
            return new FormatResult<>(Double.parseDouble(s));
        }catch (Exception e){
            return null;
        }
    }
}
