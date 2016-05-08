package theschmidtbros.programmerscalculator;

/**
 * Package:  ${Package_name}
 * Filename: ProgrammersCalculator
 * Created by: Colton on 07/05/2016
 * <p/>
 * Despciption:
 */
public class BinaryFormat extends DataFormater {


    @Override
    public String display(int value) {
        String temp = Integer.toBinaryString(value);
        return temp;
    }


}
