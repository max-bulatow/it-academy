package by.itacademy.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*Данной аннтацией будем помечать поля*/
@Target({ElementType.FIELD})
/*Аннотация будет видна в процессе выполнения кода*/
@Retention(RetentionPolicy.RUNTIME)
public @interface Validation {
    /*Поле аннотации типа String в котором будет храниться шаблон для валидации модели транспорта*/
    String pattern () default "";
}
