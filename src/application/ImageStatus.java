package application;

public enum ImageStatus {


    WASSER(0),
    SCHIFF_MITTE_HOR(10), SCHIFF_MITTE_VER(11),
    DESTR_MITTE_HOR(20), DESTR_MITTE_VER(22),
    CURR_MITTE_HOR(1), CURR_MITTE_VER(2),
    NOPE_HOR(100), NOPE_VER(200);
    private int value;
    ImageStatus(int value){
        this.value = value;
    }
    public int getValue(){
        return value;
    }
}
