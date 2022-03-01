import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
public class BeforeRequest {
public ClientConfig config(String userName, String password) {
//BASIC authenticate configuration
HttpAuthenticationFeature feature = HttpAuthenticationFeature.basicBuilder()
.credentials(userName, password)
.build();
ClientConfig clientConfig = new ClientConfig();
clientConfig.register(feature);
clientConfig.property(ClientProperties.FEATURE_AUTO_DISCOVERY_DISABLE, true);
clientConfig.register(JacksonJaxbJsonProvider.class);
return clientConfig;
}
}
public void create() {
BeforeRequest beforeRequest = new BeforeRequest();
clientConfig = beforeRequest.config("Username", "Password");
Map<String, String> map = new HashMap<>();
map .put("WONo", "RestTestWO1");
map .put("ReqLine.lLineNumber", "request line number");
a valid request line here
map .put("Technician.BarCode", "User barcode");
Response res = ClientBuilder.newClient(clientConfig)
.target("http://localhost:8080/AssetManagerWebService/rs/db
/amWorkOrder")
.request(MediaType.APPLICATION_JSON_TYPE)
.post(Entity.entity(map, MediaType.APPLICATION_JSON_TYPE));
String recordId = res.readEntity(String.class);
w record
}
public void read() {
BeforeRequest beforeRequest = new BeforeRequest();
clientConfig = beforeRequest.config("Username", "Password");
Map queryEntity = ClientBuilder.newClient(clientConfig).target("http://localhost:8080/AssetManagerWebService/rs/db/amWorkOrder")
.queryParam("fields", "lWorkOrderId,lReqLineId,ReqLine.lModelId," +"ReqLine.Model.Nature.seBasis,ReqLine.Model.Nature.OverflowTbl")
.queryParam("filter", "Technician.barCode = " + userName + "AND seStatus = 0" )
.request(MediaType.APPLICATION_JSON_TYPE)
.get(Map.class);
List<Map<String, Object>> workOrders = (List<Map<String, Object>>) quer
yEntity.get("entities");
}
public void read() {
BeforeRequest beforeRequest = new BeforeRequest();
clientConfig = beforeRequest.config("Username", "Password");
Map queryEntity = ClientBuilder.newClient(clientConfig)
.target("http://localhost:8080/AssetManagerWebService/rs/db/amWorkOrder")
.queryParam("fields", "lWorkOrderId,lReqLineId,ReqLine.lModelId," +"ReqLine.Model.Nature.seBasis,ReqLine.Model.Nature.OverflowTbl")
.queryParam("filter", "Technician.barCode = '" + userName + "'AND seStatus = 0" )
.request(MediaType.APPLICATION_JSON_TYPE)
.get(Map.class);
List<Map<String, Object>> workOrders = (List<Map<String, Object>>) quer
yEntity.get("entities");
}
public void update(String lWorkOrderId) {
BeforeRequest beforeRequest = new BeforeRequest();
clientConfig = beforeRequest.config("Username", "Password");
Map<String, String> map = new HashMap<>();
map.put("dtNotif", "1234566783"); 
nd
map.put("dtActualFixStart", "1334534566");
map.put("dtActualFixed", "3435665777");
Response res = ClientBuilder.newClient(clientConfig).target
("http://localhost:8080/AssetManagerWebService/rs/db/amWorkOrder/" + lWorkOrderId)
.request(MediaType.APPLICATION_JSON_TYPE)
.post(Entity.entity(map, MediaType.APPLICATION_JSON_TYPE));
}
public void delete(String lWorkOrderId) {
BeforeRequest beforeRequest = new BeforeRequest();
clientConfig = beforeRequest.config("Username", "Password");
Response res = ClientBuilder.newClient(clientConfig)
.target("http://localhost:8080/AssetManagerWebService/rs/db/amWorkOrder" )
.path(lWorkOrderId).request().delete();
}
