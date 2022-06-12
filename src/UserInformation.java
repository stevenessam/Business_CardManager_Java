package src;

/**
 * {@link UserInformation}
 */
public class UserInformation {
    private final String name;
    private final String company;
    private final String email;
    private final String number;

    public static class Builder {
        private String name;
        private String company;
        private String email;
        private String number;

        public Builder() {
            this.name = null;
            this.company = null;
            this.email = null;
            this.number = null;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder company(String company) {
            this.company = company;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder number(String number) {
            this.number = number;
            return this;
        }

        public UserInformation build() {
            return new UserInformation(this);
        }
    }

    public String getName() {
        return this.name;
    }

    public UserInformation(Builder builder) {
        this.name = builder.name;
        this.company = builder.company;
        this.email = builder.email;
        this.number = builder.number;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String toString() {
        String result = "";
        if (!this.name.equals("")) {
            result += "Name :" + this.name + "\n";
        }
        if (!this.company.equals("")) {
            result += "Company : " + this.company + "\n";
        }
        if (!this.email.equals("")) {
            result += "Email : " + this.email + "\n";
        }
        if (!this.number.equals("")) {
            result += "Number : " + this.number + "\n";
        }
        return result;
    }

}
