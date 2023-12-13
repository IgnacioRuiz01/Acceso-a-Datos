package com.example.proyecto1trimestre;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;

public class RowData {
    private List<String> values;

    @XmlElement
    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }
}
