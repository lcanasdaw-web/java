package domain.shared.validation;

public class Check {

public static void notBlank(String value) {
    if (value == null || value.trim().isEmpty()) {
        throw new IllegalArgumentException("El valor no puede ser nulo o vacío");
    }

}
public static void positive(Integer value) {
    if (value == null || value <= 0) {
        throw new IllegalArgumentException("El valor tiene que ser un entero positivo");
    }
}

public static void notNegative(Integer value) {
        if (value == null || value < 0 ){
            throw new IllegalArgumentException("El valor es negativo");
        }

}


}