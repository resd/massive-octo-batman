package MViGmodules;

/**
 * @author Admin
 * @since 19.12.14
 */
@SuppressWarnings("all")
public class Struct {

    // Fields

    private StructHWout structHWout;
    private StructHW structHW;
    private GeneralStruct generalStruct;

    // Constructors

    private Struct() {
    }

    public Struct(boolean isNull) {
        generalStruct = new GeneralStruct(-1, -1, -1);
    }

    public Struct(StructHW structHW) {
        this.structHW = structHW;
    }

    public Struct(StructHWout structHWout) {
        this.structHWout = structHWout;
    }

    public Struct(GeneralStruct generalStruct) {
        this.generalStruct = generalStruct;
    }

    public Struct(StructHW structHW, StructHWout structHWout) {
        this.structHW = structHW;
        this.structHWout = structHWout;
    }

    public Struct(GeneralStruct generalStruct, StructHWout structHWout) {
        this.generalStruct = generalStruct;
        this.structHWout = structHWout;
    }

    public Struct(GeneralStruct generalStruct, StructHW structHW) {
        this.generalStruct = generalStruct;
        this.structHW = structHW;
    }

    public Struct(GeneralStruct generalStruct, StructHWout structHWout, StructHW structHW) {
        this.generalStruct = generalStruct;
        this.structHWout = structHWout;
        this.structHW = structHW;
    }

    // Methods

    public boolean hasStructHW() {
        return structHW != null;
    }

    public boolean hasStructHWout() {
        return structHWout != null;
    }
    public boolean hasGeneralStruct() {
        return generalStruct != null;
    }



    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        if (generalStruct.isLowerBound()) {
            builder.append("H = ");
            builder.append(generalStruct.getH());
            builder.append(" LB");
        }
//        if (structHWout != null) {
//            builder.append(", HWout = ");
//            builder.append(structHWout.get);
//        }
        if (hasStructHWout()) {
            builder.append(builder.length() != 0 ? ", " : "");
            builder.append("HWout = ");
            builder.append(generalStruct.getHWithoutSum());
        }
        if (hasStructHW()) {
            builder.append(builder.length() != 0 ? ", " : "");
            builder.append("HW = ");
            builder.append(generalStruct.getHWithSum());
        }
//        return  + (activatehwo == true ? " +" : "") + ", HW = " + HWithSum + (activatehw == true ? " +" : "");
        return builder.toString();
    }

    // Getters & Setters

       public StructHW getStructHW() {
        return structHW;
    }

    public void setStructHW(StructHW structHW) {
        this.structHW = structHW;
    }

    public StructHWout getStructHWout() {
        return structHWout;
    }

    public void setStructHWout(StructHWout structHWout) {
        this.structHWout = structHWout;
    }

    public GeneralStruct getGeneralStruct() {
        return generalStruct;
    }

    public void setGeneralStruct(GeneralStruct generalStruct) {
        this.generalStruct = generalStruct;
    }
}
