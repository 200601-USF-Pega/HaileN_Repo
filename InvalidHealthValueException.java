
public class InvalidHealthValueException {

	static void checkHealth(int Health) {
		if (Health < 0) {
			throw new ArithmeticException("Health Value can not be negative.");
		}
		else {
			System.out.println("Mission Ready - You are Healthy!");
		}
	}
	
	public static void main(String[] args) {
		checkHealth(-1);
	}
					
}
