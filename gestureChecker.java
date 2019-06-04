import java.util.regex.*;

public class gestureChecker {

    private String leftArrow = "^.{0,2}[DES]+[CWS]+.{0,2}$";
    private String rightArrow = "^.{0,2}[CWS]+[DES]+.{0,2}$";
    private String delete = "^.{0,2}[WBC]+[CSD]+[EAD]+[NAB]+[WBC]+[SCD]{15,}.{0,2}$";
    private String select = "^.{0,2}[WBC]+[CSD]+[EAD]+[NAB]+[WBC]+.{0,2}$";
    public int gChecker(String drawnPattern, int x) {
        if (Pattern.matches(leftArrow, drawnPattern) == true) {
            x = 1;
        } else if (Pattern.matches(rightArrow, drawnPattern) == true) {
            x = 2;
        } else if (Pattern.matches(delete, drawnPattern) == true) {
            x = 3;
        } else if (Pattern.matches(select, drawnPattern) == true) {
            x = 4;
        } else {
            x = 0;
        }
        return x;
    }
}
