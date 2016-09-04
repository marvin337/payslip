package net.marvinlee.project.payslip;

public class Launcher {
	
    public static void main(String[] args) throws Exception {
        if (args != null && args.length > 0) {
        	
            if(args[0].equals("test"))                 
                JUnitRunner.main(args);
            else               
            	PayslipGenerator.main(args);


        } else {
        	PayslipGenerator.main(args);
        }
    }
}
