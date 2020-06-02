
public class InvalidHealthValueException extends Exception{

	static void checkHealth(int Health) throws Exception {
		if (Health < 0) {
			throw new Exception("Health Value can not be negative.");
		}
		else {
			System.out.println("Mission Ready - You are Healthy!");
		}
	}
	
	public static void main(String[] args) {
		checkHealth(-1);
	}
					
}
