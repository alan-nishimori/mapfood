package mapfood.model.maplink;

/**
 * @author s2it_aaraujo
 * @version $Revision: $<br/>
 * $Id: $
 * @since 06/03/19 17:18
 */
public class ClientAuthorizationData {

    private String clientId;

    private String clientSecret;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }
}
