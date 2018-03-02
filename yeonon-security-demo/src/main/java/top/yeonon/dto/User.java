package top.yeonon.dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;
import org.hibernate.validator.constraints.NotBlank;
import top.yeonon.validator.MyConstraint;

import javax.validation.constraints.Past;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {

    public interface UserSampleView {}
    public interface UserDetailView extends UserSampleView {}

    @JsonView(UserSampleView.class)
    private long id;

    @JsonView(UserSampleView.class)
    @MyConstraint(message = "这是测试的校验器")
    private String username;

    @JsonView(UserDetailView.class)
    @NotBlank(message = "密码不能为空")
    private String password;

    @JsonView(UserSampleView.class)
    @Past(message = "生日不能是未来的时间")
    private Date birthday;
}
