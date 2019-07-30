package weka.core;

import java.util.ArrayList;

public class NominalAttributeInfoExposer {

    public static ArrayList<Object> get_m_Values(NominalAttributeInfo nominalAttributeInfo){
        return nominalAttributeInfo.m_Values;
    }
}
