package theschmidtbros.programmerscalculator;

        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView displayBox;

    String displayValue;
    String operator;
    double storedValue;

    //Control Variables
    boolean changNum;
    boolean numEntered;
    boolean storeEn;
    boolean changeOp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        displayValue = "0";

        changNum = true;
        numEntered = false;
        changeOp = true;
        storeEn = true;
        operator = null;

        displayBox = (TextView)findViewById(R.id.Display);

        displayBox.setText(displayValue);

        //Set up numpad
        {
            Button one, two, three, four, five, six, seven, eight, nine, zero, dec;

            //Set numPad buttons
            one = (Button) findViewById(R.id.button1);
            two = (Button) findViewById(R.id.button2);
            three = (Button) findViewById(R.id.button3);
            four = (Button) findViewById(R.id.button4);
            five = (Button) findViewById(R.id.button5);
            six = (Button) findViewById(R.id.button6);
            seven = (Button) findViewById(R.id.button7);
            eight = (Button) findViewById(R.id.button8);
            nine = (Button) findViewById(R.id.button9);
            zero = (Button) findViewById(R.id.button0);
            dec = (Button) findViewById(R.id.buttonDec);

            //Set up listeners
            one.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    numClick("1");
                }
            });
            two.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    numClick("2");
                }
            });
            three.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    numClick("3");
                }
            });
            four.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    numClick("4");
                }
            });
            five.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    numClick("5");
                }
            });
            six.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    numClick("6");
                }
            });
            seven.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    numClick("7");
                }
            });
            eight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    numClick("8");
                }
            });
            nine.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    numClick("9");
                }
            });
            zero.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    numClick("0");
                }
            });
            dec.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    numClick(".");
                }
            });

        }

        //Set up operator buttons
        {
            Button add, sub, mult, div, eql, clear;
            //Set Operator buttons
            add = (Button) findViewById(R.id.buttonAdd);
            sub = (Button) findViewById(R.id.buttonSub);
            mult = (Button) findViewById(R.id.buttonMult);
            div = (Button) findViewById(R.id.buttonDiv);
            eql = (Button) findViewById(R.id.buttonEql);
            clear = (Button) findViewById(R.id.buttonClr);

            //Set up listeners
            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    opClick("+");
                }
            });
            sub.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    opClick("-");
                }
            });
            mult.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    opClick("*");
                }
            });
            div.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    opClick("/");
                }
            });
            eql.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    eqlClick();
                }
            });
            clear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clrClick();
                }
            });

        }
    }

    /**
     *  Adds the number to the display number
     * @param x The number chosen by the user
     */
    public void numClick( String x ) {

        //Once a number is pressed disable switching the operation
        changeOp = false;

        //If the number is editable add to the number
        if( changNum ) {

            numEntered = true;
            //The first digit case
            if (displayValue.contentEquals("0")) {
                displayValue = x;
                //If there is already a decimal don't add another one
            } else if (x.contentEquals(".") && displayValue.contains(".")) {
                return;
                //Concatenate the pressed number onto the string
            } else {
                displayValue = displayValue.concat(x);
            }
        }

        //Update screen display
        displayBox.setText(displayValue);
    }

    /**
     * Set the operator according to the button pushed and executes operations
     * if in a chain of operations. Can switch between operators freely before
     * entering the second number.
     * @param x The operator that was chosen by user
     */
    public void opClick( String x ){

        //Support from chaining equations with out hitting
        //the equal button
        if( !changeOp && operator != null && numEntered ){
            execute();
            changeOp = true;
        }

        operator = x;

        //Prevent from storing multiple times while changing Operations
        if( storeEn ) {
            storedValue = Double.parseDouble(displayValue);
            storeEn =false;
        }

        //Once operator is set allow editing of the next number and
        //reset the display value back to zero
        changNum = true;
        numEntered = false;
        displayValue = "0";
    }

    /**
     * Executes if an operator and the second value has been entered.
     * Disables the editing of the result.
     */
    public void eqlClick(){

        //If there is a second number the operation executes
        if( !changeOp && operator != null && numEntered ) {
            execute();
            changeOp = true;

            //Disables editing the result
            changNum = false;
        }

    }

    /**
     * Clears everything and brings the calculator back into the
     * initial state
     */
    public void clrClick(){
        //Resets everything to the starting state
        operator = null;
        displayValue = "0";
        storedValue = 0;
        changeOp = true;
        changNum = true;
        storeEn = true;
        displayBox.setText(displayValue);
    }

    /**
     * Executes the the operation stored in the operator variable.
     * Does nothing if operator == null
     */
    public void execute(){
        Double temp;
        if( operator.contentEquals("+")){
            temp = storedValue + Double.parseDouble(displayValue);
        }
        else if( operator.contentEquals("-") ){
            temp = storedValue - Double.parseDouble(displayValue);
        }
        else if( operator.contentEquals("*") ){
            temp = storedValue * Double.parseDouble(displayValue);
        }
        else if( operator.contentEquals("/") ){
            temp = storedValue / Double.parseDouble(displayValue);
        }
        else{
            //Do nothing
            return;
        }

        //Clears the operator so the operation can not be repeated
        operator = null;

        //Enables storing for the next operation
        storeEn = true;

        //Displays Result to screen
        displayValue = temp.toString();
        displayBox.setText(displayValue);
    }



}
