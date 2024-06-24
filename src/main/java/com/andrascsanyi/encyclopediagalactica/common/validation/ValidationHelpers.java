package com.andrascsanyi.encyclopediagalactica.common.validation;

public class ValidationHelpers {
    
    /**
     * Returns the provided annotation package and annotation name as {@link String}.
     * <p>
     * For example: In case of {@link LongValueMustBe} it will return
     * <b>com.andrascsanyi.encyclopediagalactica.common.validator.LongValueMustBe</b>
     *
     * @param annotation The annotation
     *
     * @return the annotation package and name as string separated by dots
     */
    public static String getPackageAndAnnotationName(Class<?> annotation) {
        return annotation.getPackage()
            + "."
            + annotation.getName();
    }
}
