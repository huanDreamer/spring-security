package top.sillyfan.security.constants;

public interface UserDef {

    enum UserStatus {

        Enabled(1, "有效"),
        Disabled(0, "无效")
        ;

        private Integer code;
        private String name;

        public Boolean match(final Integer code) {
            return this.code.equals(code);
        }

        UserStatus(Integer code, String name) {
            this.code = code;
            this.name = name;
        }
    }
}
