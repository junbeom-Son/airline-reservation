package vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MemberVO {

	private int memberId;
	private String memberName;
	private String phone;
	private Date registrationDate;
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("이 름: ").append(memberName)
		.append("\n전화번호: ").append(phone);
		return builder.toString();
	}
	
	
}
