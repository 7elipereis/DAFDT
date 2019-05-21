package dafdt.wekaex;

import weka.core.NominalAttributeInfo;

import java.util.ArrayList;
import java.util.List;

public class NominalAttributeInfoEx extends NominalAttributeInfo {
    public NominalAttributeInfoEx(List<String> attributeValues, String attributeName) {
        super(attributeValues, attributeName);
    }
    public ArrayList<Object> getValues(){
        return m_Values;
    }
}
