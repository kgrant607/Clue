package clueGame;

public class BadConfigFormatException extends Exception{
	BadConfigFormatException(){
		super("The configuration file format was bad");
	}
	BadConfigFormatException(String e){
		super(e);
	}
		
}
