package theschmidtbros.programmerscalculator;

/**
 * Filename: Operator.java
 * Package: ProgrammersCalculator
 * Created by: Colton on 07/05/2016
 *
 * Despciption:
 *
 *
 */
public class Operator {


    //-------------Arithmetic Operations---------------------//

    public double mult( double x, double y ){
        return x*y;
    }

    public double div( double num, double dem ){
        return num/dem;
    }

    public double add( double x, double y ){
        return x+y;
    }

    public double sub( double x, double y ) {
        return x-y;
    }

    //--------------------------------------------------------//

    //------------Logical Operations--------------------------//

    public int BitNOT( int x ){
        return ~x;
    }

    public int BitAND( int x, int y){
        return x&y;
    }

    public int BitNAND( int x, int y){
        return ~BitAND(x,y);
    }

    public int BitOR( int x, int y){
        return x|y;
    }

    public int BitNOR( int x, int y){
        return ~BitOR(x, y);
    }

    public int BitXOR( int x, int y){
        return (~x&y)|(x&~y);
    }

    public int BitXNOR( int x, int y){
        return ~BitXOR( x, y);
    }

    //--------------------------------------------------------//

}
