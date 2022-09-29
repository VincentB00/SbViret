package com.vincent.password_manager.bean;

import java.util.Objects;

public class SecretOption 
{
    private int length;
    private int lowerCase;
    private int upperCase;
    private int digitCase;
    private int specialCase;


    public SecretOption() {
    }

    public SecretOption(int length, int lowerCase, int upperCase, int digitCase, int specialCase) {
        this.length = length;
        this.lowerCase = lowerCase;
        this.upperCase = upperCase;
        this.digitCase = digitCase;
        this.specialCase = specialCase;
    }

    public int getLength() {
        return this.length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getLowerCase() {
        return this.lowerCase;
    }

    public void setLowerCase(int lowerCase) {
        this.lowerCase = lowerCase;
    }

    public int getUpperCase() {
        return this.upperCase;
    }

    public void setUpperCase(int upperCase) {
        this.upperCase = upperCase;
    }

    public int getDigitCase() {
        return this.digitCase;
    }

    public void setDigitCase(int digitCase) {
        this.digitCase = digitCase;
    }

    public int getSpecialCase() {
        return this.specialCase;
    }

    public void setSpecialCase(int specialCase) {
        this.specialCase = specialCase;
    }

    public SecretOption length(int length) {
        setLength(length);
        return this;
    }

    public SecretOption lowerCase(int lowerCase) {
        setLowerCase(lowerCase);
        return this;
    }

    public SecretOption upperCase(int upperCase) {
        setUpperCase(upperCase);
        return this;
    }

    public SecretOption digitCase(int digitCase) {
        setDigitCase(digitCase);
        return this;
    }

    public SecretOption specialCase(int specialCase) {
        setSpecialCase(specialCase);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof SecretOption)) {
            return false;
        }
        SecretOption secretOption = (SecretOption) o;
        return length == secretOption.length && lowerCase == secretOption.lowerCase && upperCase == secretOption.upperCase && digitCase == secretOption.digitCase && specialCase == secretOption.specialCase;
    }

    @Override
    public int hashCode() {
        return Objects.hash(length, lowerCase, upperCase, digitCase, specialCase);
    }

    @Override
    public String toString() {
        return "{" +
            " length='" + getLength() + "'" +
            ", lowerCase='" + getLowerCase() + "'" +
            ", upperCase='" + getUpperCase() + "'" +
            ", digitCase='" + getDigitCase() + "'" +
            ", specialCase='" + getSpecialCase() + "'" +
            "}";
    }

}
