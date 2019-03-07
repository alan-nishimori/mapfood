package mapfood.model.maplinkTeste;

public class AuthorizationToken {

    private String accessToken;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder()//
                .append("AuthorizationToken [")//
                .append("accessToken=\"")//
                .append(accessToken).append("\"")//
                .append("]");
        return builder.toString();
    }
}
