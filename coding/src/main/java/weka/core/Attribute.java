//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package weka.core;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

public class Attribute implements Copyable, Serializable, RevisionHandler {
    static final long serialVersionUID = -742180568732916383L;
    public static final int NUMERIC = 0;
    public static final int NOMINAL = 1;
    public static final int STRING = 2;
    public static final int DATE = 3;
    public static final int RELATIONAL = 4;
    public static final int ORDERING_SYMBOLIC = 0;
    public static final int ORDERING_ORDERED = 1;
    public static final int ORDERING_MODULO = 2;
    public static final String ARFF_ATTRIBUTE = "@attribute";
    public static final String ARFF_ATTRIBUTE_INTEGER = "integer";
    public static final String ARFF_ATTRIBUTE_REAL = "real";
    public static final String ARFF_ATTRIBUTE_NUMERIC = "numeric";
    public static final String ARFF_ATTRIBUTE_STRING = "string";
    public static final String ARFF_ATTRIBUTE_DATE = "date";
    public static final String ARFF_ATTRIBUTE_RELATIONAL = "relational";
    public static final String ARFF_END_SUBRELATION = "@end";
    protected static final int STRING_COMPRESS_THRESHOLD = 200;
    protected final String m_Name;
    protected int m_Type;
    protected AttributeInfo m_AttributeInfo;
    protected int m_Index;
    protected double m_Weight;
    protected AttributeMetaInfo m_AttributeMetaInfo;

    public Attribute(String attributeName) {
        this(attributeName, (ProtectedProperties)null);
    }

    public Attribute(String attributeName, ProtectedProperties metadata) {
        this.m_Index = -1;
        this.m_Weight = 1.0D;
        this.m_Name = attributeName;
        if (metadata != null) {
            this.m_AttributeMetaInfo = new AttributeMetaInfo(metadata, this);
        }

    }

    public Attribute(String attributeName, boolean createStringAttribute) {
        this(attributeName, createStringAttribute, (ProtectedProperties)null);
    }

    public Attribute(String attributeName, boolean createStringAttribute, ProtectedProperties metadata) {
        this.m_Index = -1;
        this.m_Weight = 1.0D;
        this.m_Name = attributeName;
        if (createStringAttribute) {
            this.m_AttributeInfo = new NominalAttributeInfo((List)null, attributeName);
            this.m_Type = 2;
        }

        if (metadata != null) {
            this.m_AttributeMetaInfo = new AttributeMetaInfo(metadata, this);
        }

    }

    public Attribute(String attributeName, String dateFormat) {
        this(attributeName, dateFormat, (ProtectedProperties)null);
    }

    public Attribute(String attributeName, String dateFormat, ProtectedProperties metadata) {
        this.m_Index = -1;
        this.m_Weight = 1.0D;
        this.m_Name = attributeName;
        this.m_Type = 3;
        this.m_AttributeInfo = new DateAttributeInfo(dateFormat);
        if (metadata != null) {
            this.m_AttributeMetaInfo = new AttributeMetaInfo(metadata, this);
        }

    }

    public Attribute(String attributeName, List<String> attributeValues) {
        this(attributeName, attributeValues, (ProtectedProperties)null);
    }

    public Attribute(String attributeName, List<String> attributeValues, ProtectedProperties metadata) {
        this.m_Index = -1;
        this.m_Weight = 1.0D;
        this.m_Name = attributeName;
        this.m_AttributeInfo = new NominalAttributeInfo(attributeValues, attributeName);
        if (attributeValues == null) {
            this.m_Type = 2;
        } else {
            this.m_Type = 1;
        }

        if (metadata != null) {
            this.m_AttributeMetaInfo = new AttributeMetaInfo(metadata, this);
        }

    }

    public Attribute(String attributeName, Instances header) {
        this(attributeName, header, (ProtectedProperties)null);
    }

    public Attribute(String attributeName, Instances header, ProtectedProperties metadata) {
        this.m_Index = -1;
        this.m_Weight = 1.0D;
        if (header.numInstances() > 0) {
            throw new IllegalArgumentException("Header for relation-valued attribute should not contain any instances");
        } else {
            this.m_Name = attributeName;
            this.m_Type = 4;
            this.m_AttributeInfo = new RelationalAttributeInfo(header);
            if (metadata != null) {
                this.m_AttributeMetaInfo = new AttributeMetaInfo(metadata, this);
            }

        }
    }

    public Object copy() {
        return this.copy(this.m_Name);
    }

    public final Enumeration<Object> enumerateValues() {
        if (!this.isNominal() && !this.isString()) {
            return null;
        } else {
            final Enumeration<Object> ee = new WekaEnumeration(((NominalAttributeInfo)this.m_AttributeInfo).m_Values);
            return new Enumeration<Object>() {
                public boolean hasMoreElements() {
                    return ee.hasMoreElements();
                }

                public Object nextElement() {
                    Object oo = ee.nextElement();
                    return oo instanceof SerializedObject ? ((SerializedObject)oo).getObject() : oo;
                }
            };
        }
    }

    public final boolean equals(Object other) {
        return this.equalsMsg(other) == null;
    }

    public final int hashCode() {
        return this.name().hashCode();
    }

    public final String equalsMsg(Object other) {
        if (other == null) {
            return "Comparing with null object";
        } else if (!other.getClass().equals(this.getClass())) {
            return "Object has wrong class";
        } else {
            Attribute att = (Attribute)other;
            if (!this.m_Name.equals(att.m_Name)) {
                return "Names differ: " + this.m_Name + " != " + att.m_Name;
            } else if (this.isNominal() && att.isNominal()) {
                if (((NominalAttributeInfo)this.m_AttributeInfo).m_Values.size() != ((NominalAttributeInfo)att.m_AttributeInfo).m_Values.size()) {
                    return "Different number of labels: " + ((NominalAttributeInfo)this.m_AttributeInfo).m_Values.size() + " != " + ((NominalAttributeInfo)att.m_AttributeInfo).m_Values.size();
                } else {
                    for(int i = 0; i < ((NominalAttributeInfo)this.m_AttributeInfo).m_Values.size(); ++i) {
                        if (!((NominalAttributeInfo)this.m_AttributeInfo).m_Values.get(i).equals(((NominalAttributeInfo)att.m_AttributeInfo).m_Values.get(i))) {
                            return "Labels differ at position " + (i + 1) + ": " + ((NominalAttributeInfo)this.m_AttributeInfo).m_Values.get(i) + " != " + ((NominalAttributeInfo)att.m_AttributeInfo).m_Values.get(i);
                        }
                    }

                    return null;
                }
            } else if (this.isRelationValued() && att.isRelationValued()) {
                return ((RelationalAttributeInfo)this.m_AttributeInfo).m_Header.equalHeadersMsg(((RelationalAttributeInfo)att.m_AttributeInfo).m_Header);
            } else {
                return this.type() != att.type() ? "Types differ: " + typeToString(this) + " != " + typeToString(att) : null;
            }
        }
    }

    public static String typeToString(Attribute att) {
        return typeToString(att.type());
    }

    public static String typeToString(int type) {
        String result;
        switch(type) {
            case 0:
                result = "numeric";
                break;
            case 1:
                result = "nominal";
                break;
            case 2:
                result = "string";
                break;
            case 3:
                result = "date";
                break;
            case 4:
                result = "relational";
                break;
            default:
                result = "unknown(" + type + ")";
        }

        return result;
    }

    public static String typeToStringShort(Attribute att) {
        return typeToStringShort(att.type());
    }

    public static String typeToStringShort(int type) {
        String result;
        switch(type) {
            case 0:
                result = "Num";
                break;
            case 1:
                result = "Nom";
                break;
            case 2:
                result = "Str";
                break;
            case 3:
                result = "Dat";
                break;
            case 4:
                result = "Rel";
                break;
            default:
                result = "???";
        }

        return result;
    }

    public final int index() {
        return this.m_Index;
    }

    public final int indexOfValue(String value) {
        if (!this.isNominal() && !this.isString()) {
            return -1;
        } else {
            Object store = value;
            if (value.length() > 200) {
                try {
                    store = new SerializedObject(value, true);
                } catch (Exception var4) {
                    System.err.println("Couldn't compress string attribute value - searching uncompressed.");
                }
            }

            Integer val = (Integer)((NominalAttributeInfo)this.m_AttributeInfo).m_Hashtable.get(store);
            return val == null ? -1 : val;
        }
    }

    public final boolean isNominal() {
        return this.m_Type == 1;
    }

    public final boolean isNumeric() {
        return this.m_Type == 0 || this.m_Type == 3;
    }

    public final boolean isRelationValued() {
        return this.m_Type == 4;
    }

    public final boolean isString() {
        return this.m_Type == 2;
    }

    public final boolean isDate() {
        return this.m_Type == 3;
    }

    public final String name() {
        return this.m_Name;
    }

    public final int numValues() {
        return !this.isNominal() && !this.isString() && !this.isRelationValued() ? 0 : ((NominalAttributeInfo)this.m_AttributeInfo).m_Values.size();
    }

    public final String toString() {
        StringBuffer text = new StringBuffer();
        text.append("@attribute").append(" ").append(Utils.quote(this.m_Name)).append(" ");
        switch(this.m_Type) {
            case 0:
                text.append("numeric");
                text.append(this.weight() != 1.0D ? " {" + this.weight() + "}" : "");
                break;
            case 1:
                text.append('{');
                Enumeration enu = this.enumerateValues();

                while(enu.hasMoreElements()) {
                    text.append(Utils.quote((String)enu.nextElement()));
                    if (enu.hasMoreElements()) {
                        text.append(',');
                    }
                }

                text.append('}');
                text.append(this.weight() != 1.0D ? " {" + this.weight() + "}" : "");
                break;
            case 2:
                text.append("string");
                text.append(this.weight() != 1.0D ? " {" + this.weight() + "}" : "");
                break;
            case 3:
                text.append("date").append(" ").append(Utils.quote(((DateAttributeInfo)this.m_AttributeInfo).m_DateFormat.toPattern()));
                text.append(this.weight() != 1.0D ? " {" + this.weight() + "}" : "");
                break;
            case 4:
                text.append("relational");
                text.append(this.weight() != 1.0D ? " {" + this.weight() + "}" : "");
                text.append("\n");
                Enumeration enm = ((RelationalAttributeInfo)this.m_AttributeInfo).m_Header.enumerateAttributes();

                while(enm.hasMoreElements()) {
                    text.append(enm.nextElement()).append("\n");
                }

                text.append("@end").append(" ").append(Utils.quote(this.m_Name));
                break;
            default:
                text.append("UNKNOWN");
        }

        return text.toString();
    }

    public final int type() {
        return this.m_Type;
    }

    public final String getDateFormat() {
        return this.isDate() ? ((DateAttributeInfo)this.m_AttributeInfo).m_DateFormat.toPattern() : "";
    }

    public final String value(int valIndex) {
        if (!this.isNominal() && !this.isString()) {
            return "";
        } else {
            Object val = ((NominalAttributeInfo)this.m_AttributeInfo).m_Values.get(valIndex);
            if (val instanceof SerializedObject) {
                val = ((SerializedObject)val).getObject();
            }

            return (String)val;
        }
    }

    public final Instances relation() {
        return !this.isRelationValued() ? null : ((RelationalAttributeInfo)this.m_AttributeInfo).m_Header;
    }

    public final Instances relation(int valIndex) {
        return !this.isRelationValued() ? null : (Instances)((RelationalAttributeInfo)this.m_AttributeInfo).m_Values.get(valIndex);
    }

    public Attribute(String attributeName, int index) {
        this(attributeName);
        this.m_Index = index;
    }

    public Attribute(String attributeName, String dateFormat, int index) {
        this(attributeName, dateFormat);
        this.m_Index = index;
    }

    public Attribute(String attributeName, List<String> attributeValues, int index) {
        this(attributeName, attributeValues);
        this.m_Index = index;
    }

    public Attribute(String attributeName, Instances header, int index) {
        this(attributeName, header);
        this.m_Index = index;
    }

    public int addStringValue(String value) {
        if (!this.isString()) {
            return -1;
        } else {
            Object store = value;
            if (value.length() > 200) {
                try {
                    store = new SerializedObject(value, true);
                } catch (Exception var5) {
                    System.err.println("Couldn't compress string attribute value - storing uncompressed.");
                }
            }

            Integer index = (Integer)((NominalAttributeInfo)this.m_AttributeInfo).m_Hashtable.get(store);
            if (index != null) {
                return index;
            } else {
                int intIndex = ((NominalAttributeInfo)this.m_AttributeInfo).m_Values.size();
                ((NominalAttributeInfo)this.m_AttributeInfo).m_Values.add(store);
                ((NominalAttributeInfo)this.m_AttributeInfo).m_Hashtable.put(store, new Integer(intIndex));
                return intIndex;
            }
        }
    }

    public void setStringValue(String value) {
        if (this.isString()) {
            ((NominalAttributeInfo)this.m_AttributeInfo).m_Hashtable.clear();
            ((NominalAttributeInfo)this.m_AttributeInfo).m_Values.clear();
            if (value != null) {
                this.addStringValue(value);
            }

        }
    }

    public int addStringValue(Attribute src, int index) {
        if (!this.isString()) {
            return -1;
        } else {
            Object store = ((NominalAttributeInfo)src.m_AttributeInfo).m_Values.get(index);
            Integer oldIndex = (Integer)((NominalAttributeInfo)this.m_AttributeInfo).m_Hashtable.get(store);
            if (oldIndex != null) {
                return oldIndex;
            } else {
                int intIndex = ((NominalAttributeInfo)this.m_AttributeInfo).m_Values.size();
                ((NominalAttributeInfo)this.m_AttributeInfo).m_Values.add(store);
                ((NominalAttributeInfo)this.m_AttributeInfo).m_Hashtable.put(store, new Integer(intIndex));
                return intIndex;
            }
        }
    }

    public int addRelation(Instances value) {
        if (!this.isRelationValued()) {
            return -1;
        } else if (!((RelationalAttributeInfo)this.m_AttributeInfo).m_Header.equalHeaders(value)) {
            throw new IllegalArgumentException("Incompatible value for relation-valued attribute.\n" + ((RelationalAttributeInfo)this.m_AttributeInfo).m_Header.equalHeadersMsg(value));
        } else {
            Integer index = (Integer)((NominalAttributeInfo)this.m_AttributeInfo).m_Hashtable.get(value);
            if (index != null) {
                return index;
            } else {
                int intIndex = ((NominalAttributeInfo)this.m_AttributeInfo).m_Values.size();
                ((NominalAttributeInfo)this.m_AttributeInfo).m_Values.add(value);
                ((NominalAttributeInfo)this.m_AttributeInfo).m_Hashtable.put(value, new Integer(intIndex));
                return intIndex;
            }
        }
    }

    final void addValue(String value) {
        ((NominalAttributeInfo)this.m_AttributeInfo).m_Values = (ArrayList)Utils.cast(((NominalAttributeInfo)this.m_AttributeInfo).m_Values.clone());
        ((NominalAttributeInfo)this.m_AttributeInfo).m_Hashtable = (Hashtable)Utils.cast(((NominalAttributeInfo)this.m_AttributeInfo).m_Hashtable.clone());
        this.forceAddValue(value);
    }

    public final Attribute copy(String newName) {
        Attribute copy = new Attribute(newName);
        copy.m_Index = this.m_Index;
        copy.m_Type = this.m_Type;
        copy.m_AttributeInfo = this.m_AttributeInfo;
        copy.m_AttributeMetaInfo = this.m_AttributeMetaInfo;
        copy.m_Weight = this.m_Weight;
        return copy;
    }

    final void delete(int index) {
        if (!this.isNominal() && !this.isString() && !this.isRelationValued()) {
            throw new IllegalArgumentException("Can only remove value of nominal, string or relation- valued attribute!");
        } else {
            ((NominalAttributeInfo)this.m_AttributeInfo).m_Values = (ArrayList)Utils.cast(((NominalAttributeInfo)this.m_AttributeInfo).m_Values.clone());
            ((NominalAttributeInfo)this.m_AttributeInfo).m_Values.remove(index);
            if (!this.isRelationValued()) {
                Hashtable<Object, Integer> hash = new Hashtable(((NominalAttributeInfo)this.m_AttributeInfo).m_Hashtable.size());
                Enumeration enu = ((NominalAttributeInfo)this.m_AttributeInfo).m_Hashtable.keys();

                while(enu.hasMoreElements()) {
                    Object string = enu.nextElement();
                    Integer valIndexObject = (Integer)((NominalAttributeInfo)this.m_AttributeInfo).m_Hashtable.get(string);
                    int valIndex = valIndexObject;
                    if (valIndex > index) {
                        hash.put(string, new Integer(valIndex - 1));
                    } else if (valIndex < index) {
                        hash.put(string, valIndexObject);
                    }
                }

                ((NominalAttributeInfo)this.m_AttributeInfo).m_Hashtable = hash;
            }

        }
    }

    final void forceAddValue(String value) {
        Object store = value;
        if (value.length() > 200) {
            try {
                store = new SerializedObject(value, true);
            } catch (Exception var4) {
                System.err.println("Couldn't compress string attribute value - storing uncompressed.");
            }
        }

        ((NominalAttributeInfo)this.m_AttributeInfo).m_Values.add(store);
        ((NominalAttributeInfo)this.m_AttributeInfo).m_Hashtable.put(store, new Integer(((NominalAttributeInfo)this.m_AttributeInfo).m_Values.size() - 1));
    }

    final void setIndex(int index) {
        this.m_Index = index;
    }

    final void setValue(int index, String string) {
        switch(this.m_Type) {
            case 1:
            case 2:
                ((NominalAttributeInfo)this.m_AttributeInfo).m_Values = (ArrayList)Utils.cast(((NominalAttributeInfo)this.m_AttributeInfo).m_Values.clone());
                ((NominalAttributeInfo)this.m_AttributeInfo).m_Hashtable = (Hashtable)Utils.cast(((NominalAttributeInfo)this.m_AttributeInfo).m_Hashtable.clone());
                Object store = string;
                if (string.length() > 200) {
                    try {
                        store = new SerializedObject(string, true);
                    } catch (Exception var5) {
                        System.err.println("Couldn't compress string attribute value - storing uncompressed.");
                    }
                }

                ((NominalAttributeInfo)this.m_AttributeInfo).m_Hashtable.remove(((NominalAttributeInfo)this.m_AttributeInfo).m_Values.get(index));
                ((NominalAttributeInfo)this.m_AttributeInfo).m_Values.set(index, store);
                ((NominalAttributeInfo)this.m_AttributeInfo).m_Hashtable.put(store, new Integer(index));
                return;
            default:
                throw new IllegalArgumentException("Can only set values for nominal or string attributes!");
        }
    }

    final void setValue(int index, Instances data) {
        if (this.isRelationValued()) {
            if (!data.equalHeaders(((RelationalAttributeInfo)this.m_AttributeInfo).m_Header)) {
                throw new IllegalArgumentException("Can't set relational value. Headers not compatible.\n" + data.equalHeadersMsg(((RelationalAttributeInfo)this.m_AttributeInfo).m_Header));
            } else {
                ((NominalAttributeInfo)this.m_AttributeInfo).m_Values = (ArrayList)Utils.cast(((NominalAttributeInfo)this.m_AttributeInfo).m_Values.clone());
                ((NominalAttributeInfo)this.m_AttributeInfo).m_Values.set(index, data);
            }
        } else {
            throw new IllegalArgumentException("Can only set value for relation-valued attributes!");
        }
    }

    public String formatDate(double date) {
        switch(this.m_Type) {
            case 3:
                return ((DateAttributeInfo)this.m_AttributeInfo).m_DateFormat.format(new Date((long)date));
            default:
                throw new IllegalArgumentException("Can only format date values for date attributes!");
        }
    }

    public double parseDate(String string) throws ParseException {
        switch(this.m_Type) {
            case 3:
                long time = ((DateAttributeInfo)this.m_AttributeInfo).m_DateFormat.parse(string).getTime();
                return (double)time;
            default:
                throw new IllegalArgumentException("Can only parse date values for date attributes!");
        }
    }

    public final ProtectedProperties getMetadata() {
        return this.m_AttributeMetaInfo == null ? null : this.m_AttributeMetaInfo.m_Metadata;
    }

    public final int ordering() {
        return this.m_AttributeMetaInfo == null ? 1 : this.m_AttributeMetaInfo.m_Ordering;
    }

    public final boolean isRegular() {
        return this.m_AttributeMetaInfo == null ? true : this.m_AttributeMetaInfo.m_IsRegular;
    }

    public final boolean isAveragable() {
        return this.m_AttributeMetaInfo == null ? true : this.m_AttributeMetaInfo.m_IsAveragable;
    }

    public final boolean hasZeropoint() {
        return this.m_AttributeMetaInfo == null ? true : this.m_AttributeMetaInfo.m_HasZeropoint;
    }

    public final double weight() {
        return this.m_Weight;
    }

    public void setWeight(double value) {
        this.m_Weight = value;
    }

    public final double getLowerNumericBound() {
        return this.m_AttributeMetaInfo == null ? -1.7976931348623157E308D : this.m_AttributeMetaInfo.m_LowerBound;
    }

    public final boolean lowerNumericBoundIsOpen() {
        return this.m_AttributeMetaInfo == null ? true : this.m_AttributeMetaInfo.m_LowerBoundIsOpen;
    }

    public final double getUpperNumericBound() {
        return this.m_AttributeMetaInfo == null ? 1.7976931348623157E308D : this.m_AttributeMetaInfo.m_UpperBound;
    }

    public final boolean upperNumericBoundIsOpen() {
        return this.m_AttributeMetaInfo == null ? true : this.m_AttributeMetaInfo.m_UpperBoundIsOpen;
    }

    public final boolean isInRange(double value) {
        if (this.m_Type != 3 && !Utils.isMissingValue(value)) {
            if (this.m_Type != 0) {
                int intVal = (int)value;
                if (intVal < 0 || intVal >= ((NominalAttributeInfo)this.m_AttributeInfo).m_Hashtable.size()) {
                    return false;
                }
            } else {
                if (this.m_AttributeMetaInfo == null) {
                    return true;
                }

                if (this.m_AttributeMetaInfo.m_LowerBoundIsOpen) {
                    if (value <= this.m_AttributeMetaInfo.m_LowerBound) {
                        return false;
                    }
                } else if (value < this.m_AttributeMetaInfo.m_LowerBound) {
                    return false;
                }

                if (this.m_AttributeMetaInfo.m_UpperBoundIsOpen) {
                    if (value >= this.m_AttributeMetaInfo.m_UpperBound) {
                        return false;
                    }
                } else if (value > this.m_AttributeMetaInfo.m_UpperBound) {
                    return false;
                }
            }

            return true;
        } else {
            return true;
        }
    }

    public String getRevision() {
        return RevisionUtils.extract("$Revision: 14509 $");
    }

    public static void main(String[] ops) {
        try {
            new Attribute("length");
            Attribute weight = new Attribute("weight");
            Attribute date = new Attribute("date", "yyyy-MM-dd HH:mm:ss");
            System.out.println(date);
            double dd = date.parseDate("2001-04-04 14:13:55");
            System.out.println("Test date = " + dd);
            System.out.println(date.formatDate(dd));
            dd = (double)(new Date()).getTime();
            System.out.println("Date now = " + dd);
            System.out.println(date.formatDate(dd));
            List<String> my_nominal_values = new ArrayList(3);
            my_nominal_values.add("first");
            my_nominal_values.add("second");
            my_nominal_values.add("third");
            Attribute position = new Attribute("position", my_nominal_values);
            System.out.println("Name of \"position\": " + position.name());
            Enumeration attValues = position.enumerateValues();

            while(attValues.hasMoreElements()) {
                String string = (String)attValues.nextElement();
                System.out.println("Value of \"position\": " + string);
            }

            Attribute copy = (Attribute)position.copy();
            System.out.println("Copy is the same as original: " + copy.equals(position));
            System.out.println("Index of attribute \"weight\" (should be -1): " + weight.index());
            System.out.println("Index of value \"first\" of \"position\" (should be 0): " + position.indexOfValue("first"));
            System.out.println("\"position\" is numeric: " + position.isNumeric());
            System.out.println("\"position\" is nominal: " + position.isNominal());
            System.out.println("\"position\" is string: " + position.isString());
            System.out.println("Name of \"position\": " + position.name());
            System.out.println("Number of values for \"position\": " + position.numValues());

            for(int i = 0; i < position.numValues(); ++i) {
                System.out.println("Value " + i + ": " + position.value(i));
            }

            System.out.println(position);
            switch(position.type()) {
                case 0:
                    System.out.println("\"position\" is numeric");
                    break;
                case 1:
                    System.out.println("\"position\" is nominal");
                    break;
                case 2:
                    System.out.println("\"position\" is string");
                    break;
                case 3:
                    System.out.println("\"position\" is date");
                    break;
                case 4:
                    System.out.println("\"position\" is relation-valued");
                    break;
                default:
                    System.out.println("\"position\" has unknown type");
            }

            ArrayList<Attribute> atts = new ArrayList(1);
            atts.add(position);
            Instances relation = new Instances("Test", atts, 0);
            Attribute relationValuedAtt = new Attribute("test", relation);
            System.out.println(relationValuedAtt);
        } catch (Exception var12) {
            var12.printStackTrace();
        }

    }

    public NominalAttributeInfo getNominalAttributeInfo(){

        return (NominalAttributeInfo)this.m_AttributeInfo;
    }
}
