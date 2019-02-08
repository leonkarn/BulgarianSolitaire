package lab5;

public class Term {	 
		    private final double coeff;  // coefficient
		    private final int expon;     // exponent, must be >=0

		    /**
		       Create the term 0x^0  (coeff and exponent are zero)
		    */
		    public Term() {
			this(0,0);
		    }

		    /**
		       Create term with given coefficient and exponent.
		       PRE: anExpon >= 0
		     */
		    public Term(double aCoeff, int anExpon) {
			assert anExpon >= 0;  // only gets checked when assertions enabled

			coeff = aCoeff;
			expon = anExpon;
		    }

		    /**
		       Return the coefficent of the term
		    */
		    public double getCoeff() {
			return coeff;
		    }

		    /**
		       Return the exponent of the term
		    */
		    public int getExpon() {
			return expon;
		    }

		    /**
		       Return string version of object for debugging purposes.
		    */
		    public String toString() {
			return "Term[coeff=" + coeff + ",expon=" + expon + "]";
		    }

		
	}


