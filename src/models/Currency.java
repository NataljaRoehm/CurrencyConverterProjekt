package models;

import java.util.Objects;

public class Currency {
    private int code;
    private String title;

    public Currency(int code, String title) {
        this.code = code;
        this.title = title;
    }

    public Currency(String title) {
        this.title = title;
    }


    public int getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Currency currency = (Currency) o;
        return code == currency.code && Objects.equals(title, currency.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, title);
    }

    @Override
    public String toString() {
        return title + " {" +
                "Код: " + code +
                ", Название: " + title +
                '}';
    }
}
