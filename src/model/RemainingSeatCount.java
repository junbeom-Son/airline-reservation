package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RemainingSeatCount {
	
	private int firstClass;
	private int businessClass;
	private int economyClass;
	
	public boolean isRemaining(char flightClass) {
		if (flightClass == 'F') {
			return firstClass > 0;
		} else if (flightClass == 'B') {
			return businessClass > 0;
		} else {
			return economyClass > 0;
		}
	}
}
