package dafdt.wekaex;

import weka.core.Attribute;
import weka.core.NominalAttributeInfo;

public class AttributeEx extends Attribute {
    public AttributeEx(String attributeName) {
        super(attributeName);


    }

    public NominalAttributeInfo getNominalAttributeInfo(){

        return (NominalAttributeInfo)this.m_AttributeInfo;
    }


}
